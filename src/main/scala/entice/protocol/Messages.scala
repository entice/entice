/**
 * For copyright information see the LICENSE document.
 */

package entice.protocol


/**
 * Supertype of all network messages.
 * Each network message carries its own class name in a value called "type"
 * to be able to dispatch it to a handler later on.
 */
sealed trait Message

// lobby/management messages:
// s->c
case class Failure              (error: String = "An unkown error occured.")    extends Message // send if requests fail, or an error occured generally


// c->s
case class LoginRequest         (email: String, 
                                password: String)                               extends Message
// s->c
// case class LoginSuccess         (chars: List[EntityView])                       extends Message // convention: this will only contain CharacterViews


// // c->s
// case class CharCreateRequest    (name: Name,
//                                 appearance: Appearance)                         extends Message
// case class CharDelete           (chara: Entity)                                 extends Message
// // s->c
// case class CharCreateSuccess    (chara: Entity)                                 extends Message


// // c->s
// case class PlayRequest          (chara: Entity)                                 extends Message
// case class PlayChangeMap        (map: String)                                   extends Message { def mapData = Maps.withMapName(map) }
// case class PlayQuit             ()                                              extends Message
// // s->c
// case class PlaySuccess          (map: String,
//                                 world: List[EntityView])                        extends Message { def mapData = Maps.withMapName(map) }


// // ingame/gameplay messages:
// case class ChatMessage          (sender: Entity,
//                                 message: String,                                                // bidirectional
//                                 channel: String)                                extends Message { def chatChannel = ChatChannels.withName(channel) }
// case class ServerMessage        (message: String)                               extends Message // from server
// case class ChatCommand          (command: String, 
//                                 args: List[String])                             extends Message // from client

// // updates on the CES, from client
// case class MoveRequest          (direction: Coord2D)                            extends Message // request move in this dir
// case class GroupMergeRequest    (target: Entity)                                extends Message
// case class GroupKickRequest     (target: Entity)                                extends Message // can be own entity to leave group

// // updates on the CES, from server
// case class UpdateCommand        (timeDelta: Int,
//                                 entityViews: List[EntityView],
//                                 added: List[Entity],
//                                 removed: List[Entity])                          extends Message