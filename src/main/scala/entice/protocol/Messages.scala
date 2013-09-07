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

// Login & dispatch to GS specific
case class LoginRequest     (email: String, password: String)                   extends Message
case class LoginSuccess     ()                                                  extends Message
case class LoginFail        (error: String = "An unkown error occured.")        extends Message

case class DispatchRequest  ()                                                  extends Message
case class DispatchResponse (host: String, port: Int, key: Long)                extends Message

// GS handshake & worldupdate specific
case class PlayRequest      (key: Long)                                         extends Message
case class PlaySuccess      (worldState: List[EntityView])                      extends Message
case class PlayFail         (error: String = "An unkown error occured.")        extends Message

case class GameUpdate       (timeDelta: Int, entityDiffs: List[EntityView])     extends Message
case class MoveRequest      (pos: Position, move: Movement)                     extends Message { def toView(entity: Entity) = EntityView(entity, pos :: move :: Nil) }


/**
 * Static serialization init
 */
object Messages {

    import EntitySystem._

    // serialization
    implicit def loginRequestFields         = allFields[LoginRequest]       ('jsonate)
    implicit def loginSuccessFields         = allFields[LoginSuccess]       ('jsonate)
    implicit def loginFailFields            = allFields[LoginFail]          ('jsonate)

    implicit def dispatchRequestFields      = allFields[DispatchRequest]    ('jsonate)
    implicit def dispatchResponseFields     = allFields[DispatchResponse]   ('jsonate)

    implicit def playRequestFields          = allFields[PlayRequest]        ('jsonate)
    implicit def playSuccessFields          = allFields[PlaySuccess]        ('jsonate)
    implicit def playFailFields             = allFields[PlayFail]           ('jsonate)

    implicit def gameUpdateFields           = allFields[GameUpdate]         ('jsonate)
    implicit def moveRequestFields          = allFields[MoveRequest]        ('jsonate)


    implicit def messageWrites = matchingWrites[Message] {
        case c: LoginRequest        => loginRequestFields       .toWrites.writes(c)
        case c: LoginSuccess        => loginSuccessFields       .toWrites.writes(c)
        case c: LoginFail           => loginFailFields          .toWrites.writes(c)

        case c: DispatchRequest     => dispatchRequestFields    .toWrites.writes(c)
        case c: DispatchResponse    => dispatchResponseFields   .toWrites.writes(c)

        case c: PlayRequest         => playRequestFields        .toWrites.writes(c)
        case c: PlaySuccess         => playSuccessFields        .toWrites.writes(c)
        case c: PlayFail            => playFailFields           .toWrites.writes(c)

        case c: GameUpdate          => gameUpdateFields         .toWrites.writes(c)
        case c: MoveRequest         => moveRequestFields        .toWrites.writes(c)
    }


    // deserialization
    implicit def loginRequestFactory        = factory[LoginRequest]         ('fromJson)
    implicit def loginSuccessFactory        = factory[LoginSuccess]         ('fromJson)
    implicit def loginFailFactory           = factory[LoginFail]            ('fromJson)

    implicit def dispatchRequestFactory     = factory[DispatchRequest]      ('fromJson)
    implicit def dispatchResponseFactory    = factory[DispatchResponse]     ('fromJson)

    implicit def playRequestFactory        = factory[PlayRequest]          ('fromJson)
    implicit def playSuccessFactory        = factory[PlaySuccess]          ('fromJson)
    implicit def playFailFactory           = factory[PlayFail]             ('fromJson)

    implicit def gameUpdateFactory         = factory[GameUpdate]           ('fromJson)
    implicit def moveRequestFactory        = factory[MoveRequest]          ('fromJson)


    implicit def messageReads: Reads[Message] =
        predicatedReads[Message](
            jsHas('type -> 'LoginRequest)       -> loginRequestFactory,
            jsHas('type -> 'LoginSuccess)       -> loginSuccessFactory,
            jsHas('type -> 'LoginFail)          -> loginFailFactory,

            jsHas('type -> 'DispatchRequest)    -> dispatchRequestFactory,
            jsHas('type -> 'DispatchResponse)   -> dispatchResponseFactory,

            jsHas('type -> 'PlayRequest)        -> playRequestFactory,
            jsHas('type -> 'PlaySuccess)        -> playSuccessFactory,
            jsHas('type -> 'PlayFail)           -> playFailFactory,
            
            jsHas('type -> 'GameUpdate)         -> gameUpdateFactory,
            jsHas('type -> 'MoveRequest)        -> moveRequestFactory
        )
}