/**
 * For copyright information see the LICENSE document.
 */

package entice.protocol.utils

import entice.protocol._, TypeAliases._
import akka.io._
import akka.event.LoggingAdapter
import scala.pickling._, json._
import java.nio.ByteOrder


/**
 * Defines the network de/serialization stack
 */
object PipelineFactory {

    def getWithLog(log: LoggingAdapter) = {
        TcpPipelineHandler.withLogger(log,

        new MessageStage(log) >>
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
class MessageStage(val log: LoggingAdapter) extends SymmetricPipelineStage[PipelineContext, Message, String] {
 
    override def apply(ctx: PipelineContext) = new SymmetricPipePair[Message, String] {

        override val commandPipeline = { msg: Message =>
            try {   
                ctx.singleCommand(msg.pickle.value)
            } catch {
                case exc: Throwable => 
                    log.error(exc, "Failed to serialize a json object. The object was: {}", msg)
                    ctx.nothing
            }
        }
 
        override val eventPipeline = { js: String =>
            try {   
                ctx.singleEvent(toJSONPickle(js).unpickle[Message])
            } catch {
                case exc: Throwable => 
                    log.error(exc, "Failed to deserialize a json object. The string was: {}", js)
                    ctx.nothing
            }
        }
    }
}