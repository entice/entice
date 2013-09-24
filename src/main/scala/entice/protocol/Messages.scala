/**
 * For copyright information see the LICENSE document.
 */

package entice.protocol


/**
 * Supertype of all network messages.
 * Each network message carries its own class name in a value called "type"
 * to be able to dispatch it to a handler later on.
 */
trait Message


case class LoginRequest         (email: String, 
                                password: String)                               extends Message
case class LoginSuccess         (chars: Array[CharacterView])                   extends Message
case class LoginFail            (error: String = "An unkown error occured.")    extends Message


case class CharCreateRequest    (char: CharacterView)                           extends Message
case class CharCreateSuccess    (char: Entity)                                  extends Message
case class CharCreateFail       (error: String = "An unkown error occured.")    extends Message


case class PlayRequest          (char: Entity)                                  extends Message
case class PlaySuccess          (world: Array[AllCompsView])                    extends Message
case class PlayFail             (error: String = "An unkown error occured.")    extends Message


case class ChatMessage          (message: String)                               extends Message
case class ServerMessage        (message: String)                               extends Message
case class ServerCommand        (command: String, 
                                args: Array[String])                            extends Message

case class UpdateRequest        (view: EntityView)                              extends Message
case class UpdateCommand        (timeDelta: Int,
                                entities: Array[EntityView],
                                added: Array[Entity],
                                removed: Array[Entity])                         extends Message

