/**
 * For copyright information see the LICENSE document.
 */

package entice.protocol.utils

import entice.protocol._
import akka.io._
import akka.event.LoggingAdapter
import play.api.libs.json._
import info.akshaal.json.jsonmacro._
import java.nio.ByteOrder


/**
 * Defines the network de/serialization stack
 */
object PipelineFactory {

    def getWithLog(log: LoggingAdapter) = {
        TcpPipelineHandler.withLogger(log,

        new MessageStage >>
        new StringByteStringAdapter("utf-8") >>
        new LengthFieldFrame(
            maxSize = Int.MaxValue,
            lengthIncludesHeader = false) >>
        new TcpReadWriteAdapter)
    }
}


/**
 * De/serializes a json object to/from a message case-class
 */
class MessageStage extends SymmetricPipelineStage[PipelineContext, Message, String] {
 
    override def apply(ctx: PipelineContext) = new SymmetricPipePair[Message, String] {

        override val commandPipeline = { msg: Message =>
            ctx.singleCommand(Json.toJson(msg).toString)
        }
 
        override val eventPipeline = { js: String =>
            ctx.singleEvent(Json.fromJson[Message](Json.parse(js)).get)
        }
    }
}