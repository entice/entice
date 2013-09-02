/**
 * For copyright information see the LICENSE document.
 */

package entice.protocol

import akka.actor.ActorRef

import play.api.libs.json._
import info.akshaal.json.jsonmacro._


/**
 * Supertype of all network messages.
 * Each network message carries its own class name in a value called "type"
 * to be able to deserialize it later on.
 */
sealed trait Message {
    def productPrefix: String       // implemented by all case classes, contains class name
    val `type` = productPrefix
}

// Login specific
case class LoginRequest     (email: String, password: String)       extends Message
case class LoginResponse    (error: String = "")                    extends Message

// Dispatch to GS specific
case class DispatchRequest  ()                                      extends Message
case class DispatchResponse (host: String, port: Int, key: Long)    extends Message

// GS worldupdate specific
case class GameUpdate       (entityDiffs: List[EntityView])         extends Message


/**
 * Static serialization init
 */
object Messages {

    import EntitySystem._

    // serialization
    implicit def loginRequestFields         = allFields[LoginRequest]       ('jsonate)
    implicit def loginResponseFields        = allFields[LoginResponse]      ('jsonate)
    implicit def dispatchRequestFields      = allFields[DispatchRequest]    ('jsonate)
    implicit def dispatchResponseFields     = allFields[DispatchResponse]   ('jsonate)
    implicit def gameUpdateFields           = allFields[GameUpdate]         ('jsonate)

    implicit def messageWrites = matchingWrites[Message] {
        case c: LoginRequest        => loginRequestFields       .toWrites.writes(c)
        case c: LoginResponse       => loginResponseFields      .toWrites.writes(c)
        case c: DispatchRequest     => dispatchRequestFields    .toWrites.writes(c)
        case c: DispatchResponse    => dispatchResponseFields   .toWrites.writes(c)
        case c: GameUpdate          => gameUpdateFields         .toWrites.writes(c)
    }

    // deserialization
    implicit def loginRequestFactory        = factory[LoginRequest]       ('fromJson)
    implicit def loginResponseFactory       = factory[LoginResponse]      ('fromJson)
    implicit def dispatchRequestFactory     = factory[DispatchRequest]    ('fromJson)
    implicit def dispatchResponseFactory    = factory[DispatchResponse]   ('fromJson)
    implicit def gameUpdateFactory          = factory[GameUpdate]         ('fromJson)

    implicit def messageReads: Reads[Message] =
        predicatedReads[Message](
            jsHas('type -> 'LoginRequest)       -> loginRequestFactory,
            jsHas('type -> 'LoginResponse)      -> loginResponseFactory,
            jsHas('type -> 'DispatchRequest)    -> dispatchRequestFactory,
            jsHas('type -> 'DispatchResponse)   -> dispatchResponseFactory,
            jsHas('type -> 'GameUpdate)         -> gameUpdateFactory
        )
}