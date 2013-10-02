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

case class LoginRequest         (email: String, 
                                password: String)                               extends Message
case class LoginSuccess         (chars: List[EntityView])                       extends Message // convention: this will only contain CharacterViews
case class LoginFail            (error: String = "An unkown error occured.")    extends Message


case class CharCreateRequest    (chara: CharacterView)                          extends Message
case class CharCreateSuccess    (chara: Entity)                                 extends Message
case class CharCreateFail       (error: String = "An unkown error occured.")    extends Message


case class PlayRequest          (chara: Entity)                                 extends Message
case class PlaySuccess          (world: List[EntityView])                       extends Message
case class PlayFail             (error: String = "An unkown error occured.")    extends Message


case class ChatMessage          (sender: Entity,
                                message: String)                                extends Message // bidirectional
case class ServerMessage        (message: String)                               extends Message // from server
case class ChatCommand          (command: String, 
                                args: List[String])                             extends Message // from client
case class PerformEmote         (entity: Entity,
                                emote: String)                                  extends Message // bidirectional


case class UpdateRequest        (entityView: EntityView)                        extends Message // entity will be ignored depending on the view and client permissions
case class UpdateCommand        (timeDelta: Int,
                                entityViews: List[EntityView],
                                added: List[Entity],
                                removed: List[Entity])                          extends Message


object Message {

    import EntitySystem._

    // serialization
    implicit def loginRequestFields             = allFields[LoginRequest]       ('jsonate)
    implicit def loginSuccessFields             = allFields[LoginSuccess]       ('jsonate)
    implicit def loginFailFields                = allFields[LoginFail]          ('jsonate)

    implicit def charCreateRequestFields        = allFields[CharCreateRequest]  ('jsonate)
    implicit def charCreateSuccessFields        = allFields[CharCreateSuccess]  ('jsonate)
    implicit def charCreateFailFields           = allFields[CharCreateFail]     ('jsonate)

    implicit def playRequestFields              = allFields[PlayRequest]        ('jsonate)
    implicit def playSuccessFields              = allFields[PlaySuccess]        ('jsonate)
    implicit def playFailFields                 = allFields[PlayFail]           ('jsonate)

    implicit def chatMessageFields              = allFields[ChatMessage]        ('jsonate)
    implicit def serverMessageFields            = allFields[ServerMessage]      ('jsonate)
    implicit def chatCommandFields              = allFields[ChatCommand]        ('jsonate)
    implicit def performEmoteFields             = allFields[PerformEmote]       ('jsonate)

    implicit def updateRequestFields            = allFields[UpdateRequest]      ('jsonate) 
    implicit def updateCommandFields            = allFields[UpdateCommand]      ('jsonate)


    implicit def messageWrites = matchingWrites[Message] {
        case c: LoginRequest                    => loginRequestFields           .toWrites.writes(c)
        case c: LoginSuccess                    => loginSuccessFields           .toWrites.writes(c)
        case c: LoginFail                       => loginFailFields              .toWrites.writes(c)

        case c: CharCreateRequest               => charCreateRequestFields      .toWrites.writes(c)
        case c: CharCreateSuccess               => charCreateSuccessFields      .toWrites.writes(c)
        case c: CharCreateFail                  => charCreateFailFields         .toWrites.writes(c)

        case c: PlayRequest                     => playRequestFields            .toWrites.writes(c)
        case c: PlaySuccess                     => playSuccessFields            .toWrites.writes(c)
        case c: PlayFail                        => playFailFields               .toWrites.writes(c)

        case c: ChatMessage                     => chatMessageFields            .toWrites.writes(c)
        case c: ServerMessage                   => serverMessageFields          .toWrites.writes(c)
        case c: ChatCommand                     => chatCommandFields            .toWrites.writes(c)
        case c: PerformEmote                    => performEmoteFields           .toWrites.writes(c)

        case c: UpdateRequest                   => updateRequestFields          .toWrites.writes(c)
        case c: UpdateCommand                   => updateCommandFields          .toWrites.writes(c)
    }


    // deserialization
    implicit def loginRequestFactory            = factory[LoginRequest]         ('fromJson)
    implicit def loginSuccessFactory            = factory[LoginSuccess]         ('fromJson)
    implicit def loginFailFactory               = factory[LoginFail]            ('fromJson)

    implicit def charCreateRequestFactory       = factory[CharCreateRequest]    ('fromJson)
    implicit def charCreateSuccessFactory       = factory[CharCreateSuccess]    ('fromJson)
    implicit def charCreateFailFactory          = factory[CharCreateFail]       ('fromJson)

    implicit def playRequestFactory             = factory[PlayRequest]          ('fromJson)
    implicit def playSuccessFactory             = factory[PlaySuccess]          ('fromJson)
    implicit def playFailFactory                = factory[PlayFail]             ('fromJson)

    implicit def chatMessageFactory             = factory[ChatMessage]          ('fromJson)
    implicit def serverMessageFactory           = factory[ServerMessage]        ('fromJson)
    implicit def chatCommandFactory             = factory[ChatCommand]          ('fromJson)
    implicit def performEmoteFactory            = factory[PerformEmote]         ('fromJson)

    implicit def updateRequestFactory           = factory[UpdateRequest]        ('fromJson) 
    implicit def updatecommandFactory           = factory[UpdateCommand]        ('fromJson)


    implicit def messageReads: Reads[Message] =
        predicatedReads[Message](
            jsHas('type                         -> 'LoginRequest)               -> loginRequestFactory,
            jsHas('type                         -> 'LoginSuccess)               -> loginSuccessFactory,
            jsHas('type                         -> 'LoginFail)                  -> loginFailFactory,

            jsHas('type                         -> 'CharCreateRequest)          -> charCreateRequestFactory,
            jsHas('type                         -> 'CharCreateSuccess)          -> charCreateSuccessFactory,
            jsHas('type                         -> 'CharCreateFail)             -> charCreateFailFactory,

            jsHas('type                         -> 'PlayRequest)                -> playRequestFactory,
            jsHas('type                         -> 'PlaySuccess)                -> playSuccessFactory,
            jsHas('type                         -> 'PlayFail)                   -> playFailFactory,

            jsHas('type                         -> 'ChatMessage)                -> chatMessageFactory,
            jsHas('type                         -> 'ServerMessage)              -> serverMessageFactory,
            jsHas('type                         -> 'ChatCommand)                -> chatCommandFactory,
            jsHas('type                         -> 'PerformEmote)               -> performEmoteFactory,
            
            jsHas('type                         -> 'UpdateRequest)              -> updateRequestFactory,
            jsHas('type                         -> 'UpdateCommand)              -> updatecommandFactory
        )
}