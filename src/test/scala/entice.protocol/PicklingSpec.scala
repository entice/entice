/**
 * For copyright information see the LICENSE document.
 */

package entice.protocol

import NetworkAttribute._

import scala.pickling._, json._

import org.scalatest._


class NetworkMessageSpec extends WordSpec with MustMatchers {

  // compile time speed up :P
  def pickleMsg(msg: Message): String = msg.pickle.value
  def unpickleMsg(raw: String): Message = toJSONPickle(raw).unpickle[Message]

  "An entice protocol" must {

    "pickle and unpickle: Failure" in {
      val msg: Message = Failure("test")
      val raw = """
        |{
        |  "tpe": "entice.protocol.Failure",
        |  "error": "test"
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg) // raw is minimal version
    }


    "pickle and unpickle: LoginRequest" in {
      val msg: Message = LoginRequest("me@home.net", "somepass")
      val raw = """
        |{
        |  "tpe": "entice.protocol.LoginRequest",
        |  "email": "me@home.net",
        |  "password": "somepass"
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg) // raw is minimal version
    }


    "pickle and unpickle: LoginSuccess" in {
      val msg: Message = LoginSuccess(List(CharacterView(321, Name(), Appearance())))
      val raw = """
        |{
        |  "tpe": "entice.protocol.LoginSuccess",
        |  "chars": {
        |    "tpe": "scala.collection.immutable.$colon$colon[entice.protocol.CharacterView]",
        |    "elems": [
        |      {
        |      "tpe": "entice.protocol.CharacterView",
        |      "entityId": 321,
        |      "name": {
        |        "name": "John Wayne"
        |      },
        |      "appearance": {
        |        "profession": 1,
        |        "campaign": 0,
        |        "sex": 1,
        |        "height": 0,
        |        "skinColor": 3,
        |        "hairColor": 0,
        |        "hairstyle": 7,
        |        "face": 31
        |      }
        |    }
        |    ]
        |  }
        |}""".stripMargin.trim
      val minimal = """
        |{
        |  "tpe": "entice.protocol.LoginSuccess",
        |  "chars": {
        |    "tpe": "scala.collection.immutable.$colon$colon[entice.protocol.CharacterView]",
        |    "elems": [
        |      {
        |      "entityId": 321,
        |      "name": {
        |        "name": "John Wayne"
        |      },
        |      "appearance": {
        |        "profession": 1,
        |        "campaign": 0,
        |        "sex": 1,
        |        "height": 0,
        |        "skinColor": 3,
        |        "hairColor": 0,
        |        "hairstyle": 7,
        |        "face": 31
        |      }
        |    }
        |    ]
        |  }
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw).asInstanceOf[LoginSuccess].chars must be(msg.asInstanceOf[LoginSuccess].chars)
      unpickleMsg(minimal).asInstanceOf[LoginSuccess].chars must be(msg.asInstanceOf[LoginSuccess].chars)
    }


    "pickle and unpickle: CharCreateRequest" in {
      val msg: Message = CharCreateRequest(Name(), Appearance())
      val raw = """
        |{
        |  "tpe": "entice.protocol.CharCreateRequest",
        |  "name": {
        |    "name": "John Wayne"
        |  },
        |  "appearance": {
        |    "profession": 1,
        |    "campaign": 0,
        |    "sex": 1,
        |    "height": 0,
        |    "skinColor": 3,
        |    "hairColor": 0,
        |    "hairstyle": 7,
        |    "face": 31
        |  }
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg) // raw is minimal version
    }


    "pickle and unpickle: CharDelete" in {
      val msg: Message = CharDelete(321)
      val raw = """
        |{
        |  "tpe": "entice.protocol.CharDelete",
        |  "chara": 321
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg) // raw is minimal version
    }


    "pickle and unpickle: CharCreateSuccess" in {
      val msg: Message = CharCreateSuccess(321)
      val raw = """
        |{
        |  "tpe": "entice.protocol.CharCreateSuccess",
        |  "chara": 321
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg) // raw is minimal version
    }


    "pickle and unpickle: PlayRequest" in {
      val msg: Message = PlayRequest(321)
      val raw = """
        |{
        |  "tpe": "entice.protocol.PlayRequest",
        |  "chara": 321
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg) // raw is minimal version
    }


    "pickle and unpickle: PlayChangeMap" in {
      val msg: Message = PlayChangeMap("HeroesAscent")
      val raw = """
        |{
        |  "tpe": "entice.protocol.PlayChangeMap",
        |  "map": "HeroesAscent"
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg) // raw is minimal version
    }


    "pickle and unpickle: PlayQuit" in {
      val msg: Message = PlayQuit()
      val raw = """
        |{
        |  "tpe": "entice.protocol.PlayQuit"
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg) // raw is minimal version
    }


    "pickle and unpickle: PlaySuccess" in {
      val msg: Message = PlaySuccess("HeroesAscent", 321)
      val raw = """
        |{
        |  "tpe": "entice.protocol.PlaySuccess",
        |  "map": "HeroesAscent",
        |  "chara": 321
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg) // raw is minimal version
    }


    "pickle and unpickle: ChatMessage" in {
      val msg: Message = ChatMessage(321, "Hi there!", ChatChannels.All.toString)
      val raw = """
        |{
        |  "tpe": "entice.protocol.ChatMessage",
        |  "sender": 321,
        |  "message": "Hi there!",
        |  "channel": "All"
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg) // raw is minimal version
    }


    "pickle and unpickle: IngameCommand" in {
      val msg: Message = IngameCommand("buddy", List("grouping", "greeting"))
      val raw = """
        |{
        |  "tpe": "entice.protocol.IngameCommand",
        |  "command": "buddy",
        |  "args": {
        |    "tpe": "scala.collection.immutable.$colon$colon[java.lang.String]",
        |    "elems": [
        |      "grouping",
        |      "greeting"
        |    ]
        |  }
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg) // raw is minimal version
    }


    "pickle and unpickle: ServerMessage" in {
      val msg: Message = ServerMessage("Server is shutting down.")
      val raw = """
        |{
        |  "tpe": "entice.protocol.ServerMessage",
        |  "message": "Server is shutting down."
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg) // raw is minimal version
    }


    "pickle and unpickle: MoveRequest" in {
      val msg: Message = MoveRequest(Coord2D(5,3))
      val raw = """
        |{
        |  "tpe": "entice.protocol.MoveRequest",
        |  "direction": {
        |    "x": 5.0,
        |    "y": 3.0
        |  }
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg)
    }


    "pickle and unpickle: GroupMergeRequest" in {
      val msg: Message = GroupMergeRequest(321)
      val raw = """
        |{
        |  "tpe": "entice.protocol.GroupMergeRequest",
        |  "target": 321
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg)
    }


    "pickle and unpickle: GroupKickRequest" in {
      val msg: Message = GroupKickRequest(321)
      val raw = """
        |{
        |  "tpe": "entice.protocol.GroupKickRequest",
        |  "target": 321
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg)
    }


    "pickle and unpickle: EventInterval" in {
      val msg: Message = EventInterval(250)
      val raw = """
        |{
        |  "tpe": "entice.protocol.EventInterval",
        |  "timeDelta": 250
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg)
    }


    "pickle and unpickle: AddEntity" in {
      val msg: Message = AddEntity(321)
      val raw = """
        |{
        |  "tpe": "entice.protocol.AddEntity",
        |  "entity": 321
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg)
    }


    "pickle and unpickle: RemoveEntity" in {
      val msg: Message = RemoveEntity(321)
      val raw = """
        |{
        |  "tpe": "entice.protocol.RemoveEntity",
        |  "entity": 321
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg)
    }


    "pickle and unpickle: AddAttribute" in {
      val msg: Message = AddAttribute(321, Name("Wurst Hans"))
      val raw = """
        |{
        |  "tpe": "entice.protocol.AddAttribute",
        |  "entity": 321,
        |  "attribute": {
        |    "tpe": "entice.protocol.NetworkAttribute.Name",
        |    "name": "Wurst Hans"
        |  }
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg)
    }


    "pickle and unpickle: RemoveAttribute" in {
      val msg: Message = RemoveAttribute(321, "Name")
      val raw = """
        |{
        |  "tpe": "entice.protocol.RemoveAttribute",
        |  "entity": 321,
        |  "attribute": "Name"
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg)
    }


    "pickle and unpickle: ChangeAttribute" in {
      val msg: Message = ChangeAttribute(321, Name("Hans Wurst"), Name("Wurst Hans"))
      val raw = """
        |{
        |  "tpe": "entice.protocol.ChangeAttribute",
        |  "entity": 321,
        |  "older": {
        |    "tpe": "entice.protocol.NetworkAttribute.Name",
        |    "name": "Hans Wurst"
        |  },
        |  "newer": {
        |    "tpe": "entice.protocol.NetworkAttribute.Name",
        |    "name": "Wurst Hans"
        |  }
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg)
    }
  }
}
