/**
 * For copyright information see the LICENSE document.
 */

package entice.protocol

import play.api.libs.json._
import info.akshaal.json.jsonmacro._


/**
 * Resembles a 2d vector or point (depending on the context)
 */
case class Coord2D(x: Float, y: Float) extends Typeable


/**
 * Wraps a java.util.UUID.
 */
case class UUID(mostSigBytes: Long, leastSigBytes: Long) extends Typeable

object UUID {
    def apply(id: java.util.UUID): UUID = new UUID(id.getMostSignificantBits, id.getLeastSignificantBits)
    def apply(): UUID = apply(java.util.UUID.randomUUID)

    val Invalid = UUID(0, 0)
}


/**
 * State of a moving entity
 */
object MoveState extends Enumeration {
   val NotMoving    = Value("NotMoving")
   val Moving       = Value("Moving")
}


object Utils {

    // serialization
    implicit def uuidFields     = allFields[UUID]   ('jsonate)
    implicit def coord2dFields  = allFields[Coord2D]('jsonate)

    // deserialization
    implicit def uuidFactory    = factory[UUID]     ('fromJson)
    implicit def coord2dFactory = factory[Coord2D]  ('fromJson)
}