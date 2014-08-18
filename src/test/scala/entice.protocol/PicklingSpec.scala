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
      unpickleMsg(raw) must be(msg)
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
      unpickleMsg(raw) must be(msg)
    }


    "pickle and unpickle: LoginSuccess" in {
      val msg: Message = LoginSuccess(Array(EntityAppearance(321, Appearance())))
      val raw = """
        |{
        |  "tpe": "entice.protocol.LoginSuccess",
        |  "chars": {
        |    "elems": [
        |      {
        |      "tpe": "entice.protocol.EntityAppearance",
        |      "entityId": 321,
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
      unpickleMsg(raw).asInstanceOf[LoginSuccess].chars must be(msg.asInstanceOf[LoginSuccess].chars)
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
      unpickleMsg(raw) must be(msg)
    }


    "pickle and unpickle: CharDelete" in {
      val msg: Message = CharDelete(321)
      val raw = """
        |{
        |  "tpe": "entice.protocol.CharDelete",
        |  "chara": 321
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg)
    }


    "pickle and unpickle: CharCreateSuccess" in {
      val msg: Message = CharCreateSuccess(321)
      val raw = """
        |{
        |  "tpe": "entice.protocol.CharCreateSuccess",
        |  "chara": 321
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg)
    }


    "pickle and unpickle: PlayRequest" in {
      val msg: Message = PlayRequest(321)
      val raw = """
        |{
        |  "tpe": "entice.protocol.PlayRequest",
        |  "chara": 321
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg)
    }


    "pickle and unpickle: PlayChangeMap" in {
      val msg: Message = PlayChangeMap("HeroesAscent")
      val raw = """
        |{
        |  "tpe": "entice.protocol.PlayChangeMap",
        |  "map": "HeroesAscent"
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg)
    }


    "pickle and unpickle: PlayQuit" in {
      val msg: Message = PlayQuit()
      val raw = """
        |{
        |  "tpe": "entice.protocol.PlayQuit"
        |}""".stripMargin.trim
      pickleMsg(msg) must be(raw)
      unpickleMsg(raw) must be(msg)
    }


    "pickle and unpickle: PlaySuccess" in {
      val msg: Message = PlaySuccess(
        "HeroesAscent",
        321)
        // Array((Name()), (NetPosition()), (NetAppearance())),
        // Array(123, 321))
      val raw = """
        |{
        |  "tpe": "entice.protocol.PlayQuit"
        |}""".stripMargin.trim
      println(pickleMsg(msg)) // must be(raw)
      unpickleMsg(raw) must be(msg)
      true
    }
  }
}