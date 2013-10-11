/**
 * For copyright information see the LICENSE document.
 */

package entice.protocol

import play.api.libs.json._
import info.akshaal.json.jsonmacro._


/**
 * Supertype of all network messages.
 * Each network message carries its own class name in a value called "type"
 * to be able to dispatch it to a handler later on.
 */
sealed trait Message extends Typeable

case class Failure              (error: String = "An unkown error occured.")    extends Message // send if requests fail, or an error occured generally

case class LoginRequest         (email: String, 
                                password: String)                               extends Message
case class LoginSuccess         (chars: List[EntityView])                       extends Message // convention: this will only contain CharacterViews


case class CharCreateRequest    (chara: CharacterView)                          extends Message
case class CharCreateSuccess    (chara: Entity)                                 extends Message


case class PlayRequest          (chara: Entity)                                 extends Message
case class PlayChangeMap        (map: String)                                   extends Message { def mapData = Maps.withMapName(map) }
case class PlayQuit             ()                                              extends Message
case class PlaySuccess          (map: String,
                                world: List[EntityView])                        extends Message { def mapData = Maps.withMapName(map) }


case class ChatMessage          (sender: Entity,
                                message: String)                                extends Message // bidirectional
case class ServerMessage        (message: String)                               extends Message // from server
case class ChatCommand          (command: String, 
                                args: List[String])                             extends Message // from client

case class UpdateRequest        (entityView: EntityView)                        extends Message // entity will be ignored depending on the view and client permissions
case class UpdateCommand        (timeDelta: Int,
                                entityViews: List[EntityView],
                                added: List[Entity],
                                removed: List[Entity])                          extends Message


object Message {

    import EntitySystem._

    // serialization
    implicit def failureFields                  = allFields[Failure]            ('jsonate)

    implicit def loginRequestFields             = allFields[LoginRequest]       ('jsonate)
    implicit def loginSuccessFields             = allFields[LoginSuccess]       ('jsonate)

    implicit def charCreateRequestFields        = allFields[CharCreateRequest]  ('jsonate)
    implicit def charCreateSuccessFields        = allFields[CharCreateSuccess]  ('jsonate)

    implicit def playRequestFields              = allFields[PlayRequest]        ('jsonate)
    implicit def playChangeMapFields            = allFields[PlayChangeMap]      ('jsonate)
    implicit def playQuitFields                 = allFields[PlayQuit]           ('jsonate)
    implicit def playSuccessFields              = allFields[PlaySuccess]        ('jsonate)

    implicit def chatMessageFields              = allFields[ChatMessage]        ('jsonate)
    implicit def serverMessageFields            = allFields[ServerMessage]      ('jsonate)
    implicit def chatCommandFields              = allFields[ChatCommand]        ('jsonate)

    implicit def updateRequestFields            = allFields[UpdateRequest]      ('jsonate) 
    implicit def updateCommandFields            = allFields[UpdateCommand]      ('jsonate)


    implicit def messageWrites = matchingWrites[Message] {
        case c: Failure                         => failureFields                .toWrites.writes(c)

        case c: LoginRequest                    => loginRequestFields           .toWrites.writes(c)
        case c: LoginSuccess                    => loginSuccessFields           .toWrites.writes(c)

        case c: CharCreateRequest               => charCreateRequestFields      .toWrites.writes(c)
        case c: CharCreateSuccess               => charCreateSuccessFields      .toWrites.writes(c)

        case c: PlayRequest                     => playRequestFields            .toWrites.writes(c)
        case c: PlayChangeMap                   => playChangeMapFields          .toWrites.writes(c)
        case c: PlayQuit                        => playQuitFields               .toWrites.writes(c)
        case c: PlaySuccess                     => playSuccessFields            .toWrites.writes(c)

        case c: ChatMessage                     => chatMessageFields            .toWrites.writes(c)
        case c: ServerMessage                   => serverMessageFields          .toWrites.writes(c)
        case c: ChatCommand                     => chatCommandFields            .toWrites.writes(c)

        case c: UpdateRequest                   => updateRequestFields          .toWrites.writes(c)
        case c: UpdateCommand                   => updateCommandFields          .toWrites.writes(c)
    }


    // deserialization
    implicit def failureFactory                 = factory[Failure]              ('fromJson)

    implicit def loginRequestFactory            = factory[LoginRequest]         ('fromJson)
    implicit def loginSuccessFactory            = factory[LoginSuccess]         ('fromJson)

    implicit def charCreateRequestFactory       = factory[CharCreateRequest]    ('fromJson)
    implicit def charCreateSuccessFactory       = factory[CharCreateSuccess]    ('fromJson)

    implicit def playRequestFactory             = factory[PlayRequest]          ('fromJson)
    implicit def playChangeMapFactory           = factory[PlayChangeMap]        ('fromJson)
    implicit def playQuitFactory                = factory[PlayQuit]             ('fromJson)
    implicit def playSuccessFactory             = factory[PlaySuccess]          ('fromJson)

    implicit def chatMessageFactory             = factory[ChatMessage]          ('fromJson)
    implicit def serverMessageFactory           = factory[ServerMessage]        ('fromJson)
    implicit def chatCommandFactory             = factory[ChatCommand]          ('fromJson)

    implicit def updateRequestFactory           = factory[UpdateRequest]        ('fromJson) 
    implicit def updatecommandFactory           = factory[UpdateCommand]        ('fromJson)


    implicit def messageReads: Reads[Message] =
        predicatedReads[Message](
            jsHas('type                         -> 'Failure)                    -> failureFactory,

            jsHas('type                         -> 'LoginRequest)               -> loginRequestFactory,
            jsHas('type                         -> 'LoginSuccess)               -> loginSuccessFactory,

            jsHas('type                         -> 'CharCreateRequest)          -> charCreateRequestFactory,
            jsHas('type                         -> 'CharCreateSuccess)          -> charCreateSuccessFactory,

            jsHas('type                         -> 'PlayRequest)                -> playRequestFactory,
            jsHas('type                         -> 'PlayChangeMap)              -> playChangeMapFactory,
            jsHas('type                         -> 'PlayQuit)                   -> playQuitFactory,
            jsHas('type                         -> 'PlaySuccess)                -> playSuccessFactory,

            jsHas('type                         -> 'ChatMessage)                -> chatMessageFactory,
            jsHas('type                         -> 'ServerMessage)              -> serverMessageFactory,
            jsHas('type                         -> 'ChatCommand)                -> chatCommandFactory,
            
            jsHas('type                         -> 'UpdateRequest)              -> updateRequestFactory,
            jsHas('type                         -> 'UpdateCommand)              -> updatecommandFactory
        )
}