/**
 * For copyright information see the LICENSE document.
 */

package entice.protocol


// Hint: Network messages are further down.

/** Simple type aliases. Does not affect serialization */
object TypeAliases {
  type EntityId = Int
  type AttributeName = String
}
import TypeAliases._



/** These are serializable copies of the internally used attributes */
sealed trait NetworkAttribute
/** Ev'ry day I'm namespacein' */
object NetworkAttribute {

  /** All animations an entity (player) can do */
  case class Animation(id: String = Animations.None.toString) extends NetworkAttribute

  /** Appearance of a player */
  case class Appearance(
      profession: Int = 1,
      campaign: Int = 0,
      sex: Int = 1,
      height: Int = 0,
      skinColor: Int = 3,
      hairColor: Int = 0,
      hairstyle: Int = 7,
      face: Int = 31) extends NetworkAttribute

  /** The state of a group entity (special entity, not rendered etc.) */
  case class GroupState(
      members: List[EntityId] = Nil,
      invited: List[EntityId] = Nil,
      joinRequests: List[EntityId] = Nil) extends NetworkAttribute

  /** A direction of movement and a state for if the entity is moving or not */
  case class Movement(
      goal: Coord2D = Coord2D(1, 1),
      state: String = MoveState.NotMoving.toString) extends NetworkAttribute

  /** Displayed name of entity, if any */
  case class Name(name: String = "John Wayne") extends NetworkAttribute

  /** Physical position in map coordinates */
  case class Position(pos: Coord2D = Coord2D(0, 0)) extends NetworkAttribute
}

import NetworkAttribute._



/**
 * A general network message.
 * This trait is split up into special subtraits to further refine
 * what kind of message is transferred trough the network.
 *
 * TODO: Due to current limitations in the serialization framework we try to
 * avoid using Arrays of Traits whereever possible
 *
 * Look into the messages for further documentation, and look for the implented traits
 * which stand as kind of self-docuemtnation for the messages.
 */
sealed trait Message
sealed trait IncomingMessage extends Message // marker for Client-to-Server messages
sealed trait OutgoingMessage extends Message // marker for Server-to-Client messages
sealed trait CanFail extends OutgoingMessage // marker for messages that can fail with Failure(...)

/** A general error occured. Avoids error codes - sends the message right away */
case class Failure(error: String = "An unkown error occured.")  extends Message with OutgoingMessage


/**
 * Login process only
 */
sealed trait LoginMessage extends Message
case class CharacterView(entityId: EntityId, name: Name, appearance: Appearance) // Helper only
/** Request with credentials */
case class LoginRequest(email: String, password: String) extends LoginMessage with IncomingMessage
/** Answer with this or fail the login */
case class LoginSuccess(chars: List[CharacterView]) extends LoginMessage with OutgoingMessage with CanFail


/**
 * Messages allowed in lobby only
 */
sealed trait LobbyMessage extends Message
/** Request to create a new character (no pvp/pve yet) on the server */
case class CharCreateRequest(name: Name, appearance: Appearance) extends LobbyMessage with IncomingMessage
/** Request to delete a specified character, will not be answered */
case class CharDelete(chara: EntityId) extends LobbyMessage with IncomingMessage
/** Answer to creation process, or failure */
case class CharCreateSuccess(chara: EntityId) extends LobbyMessage with OutgoingMessage with CanFail


/**
 * World enter, change and leave stuff
 */
sealed trait PlayMessage extends Message
/** Needs to be send from lobby. Request to enter the world the char is in. */
case class PlayRequest(chara: EntityId) extends PlayMessage with IncomingMessage
/** Needs to be send from world. Request to change to another world (map instance) */
case class PlayChangeMap(map: String) extends PlayMessage  with IncomingMessage { def mapData = Maps.withMapName(map) }
/** Needs to be send from world. Request to change back to Lobby */
case class PlayQuit() extends PlayMessage with IncomingMessage
/**
 * Initiates the map load process, or fails.
 * If this packet is send, it is garuanteed to be followed by:
 * 1. Updates to the attributes of the player.
 * 2. All entity-IDs of all entities on the map
 * 3. All attributes of the entities that are visible
 */
case class PlaySuccess(map: String, chara: EntityId) extends PlayMessage with OutgoingMessage with CanFail


/**
 * General ingame messages, for chat, maintenance etc.
 */
sealed trait IngameMessage
/** Bi-directional message for ingame chats of all kinds. Client-to-client communication */
case class ChatMessage(sender: EntityId, message: String, channel: String)
    extends IngameMessage with IncomingMessage with OutgoingMessage { def chatChannel = ChatChannels.withName(channel) }
/** Issues a server command. Use /helpme ingame to show available commands */
case class IngameCommand(command: String, args: List[String]) extends IngameMessage with IncomingMessage
/** General server messages (announcements etc.) */
case class ServerMessage(message: String) extends IngameMessage with OutgoingMessage


/**
 * Client-side update requests on the game state only
 */
sealed trait UpdateRequestMessage extends Message with IncomingMessage
/** Request to change direction and start moving in the given direction. Stop if dir is vec(0,0) */
case class MoveRequest(direction: Coord2D) extends UpdateRequestMessage
/** Request to fuse my group with another one (even if you do not yet have a group). Only as leader */
case class GroupMergeRequest(target: EntityId) extends UpdateRequestMessage
/** Request to kick a player from the group. Only as leader, only if play in your group */
case class GroupKickRequest(target: EntityId) extends UpdateRequestMessage


/**
 * Server-side update commands (generally: a push of updates on the game state)
 * Updates are in order and can be distinguished timewise by the event interval packet.
 */
sealed trait ServerCommandMessage extends Message with OutgoingMessage
/** Splits the event stream into distinct and discrete event interval time windows */
case class EventInterval(timeDelta: Int) extends ServerCommandMessage
/** World scope: Adds an entity to this world. Sent also if entity not in visible range. */
case class AddEntity(entity: EntityId) extends ServerCommandMessage
/** World scope: Removes an entity to this world. Sent also if entity not in visible range. */
case class RemoveEntity(entity: EntityId) extends ServerCommandMessage
/** Entity scope: Add a new attribute to an entity */
case class AddAttribute(entity: EntityId, attribute: NetworkAttribute) extends ServerCommandMessage
/** Entity scope: Remove an attribute from an entity */
case class RemoveAttribute(entity: EntityId, attribute: AttributeName) extends ServerCommandMessage
/** Entity scope: Change an attribute of an entity to a new state. TODO Really needed? */
case class ChangeAttribute(entity: EntityId, older: NetworkAttribute, newer: NetworkAttribute) extends ServerCommandMessage




