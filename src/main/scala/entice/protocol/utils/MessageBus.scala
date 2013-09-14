/**
 * For copyright information see the LICENSE document.
 * Adapted from: https://gist.github.com/DeLongey/3757237
 */

package entice.protocol.utils

import akka.event.ActorEventBus
import akka.event.LookupClassification
import akka.actor.ActorRef

import entice.protocol._
 

object MessageBus {
    case class Sender(uuid: UUID = UUID.Invalid, actor: ActorRef)
    case class MessageEvent(sender: Sender, message: Message)
}


/**
 * Message bus to route messages to their appropriate handler actors.
 * This is an implementation of the Reactor design pattern.
 *
 * Details:
 * When subscribing to a message, you actually subscribe to the classname of it.
 * This is because messages provide their classname as a 'type' field anyway, so we
 * can classify them that way easily. The class name does not need be read out by 
 * the usage of reflection at runtime when a message gets published.
 *
 * Usage:
 * This might be used with appropriate message handler actors. The message event carries
 * an additional field just for the purpose of giving the handler some kind of information
 * about the sender.
 * The sender actor is additionally wrapped to make it possible to identify it.
 * (Note that it needs to pass its identifier itself, so the sender is responsible for
 * any conflicts that might occur.)
 */
case class MessageBus extends ActorEventBus with LookupClassification {
 
    import MessageBus._

    type Event = MessageEvent
    type Classifier = String


    protected val mapSize = 10


    protected def classify(event: Event): Classifier = event.message.`type`


    def subscribe(subscriber: Subscriber, msgClazz: Class[_ <: Message]) {
        super.subscribe(subscriber, msgClazz.getSimpleName)
    }


    protected def publish(event: Event, subscriber: Subscriber) {
        subscriber ! event
    }
}