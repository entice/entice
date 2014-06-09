/**
 * For copyright information see the LICENSE document.
 */

package entice.protocol


/**
 * Resembles a 2d vector or point (depending on the context)
 */
case class Coord2D(x: Float, y: Float)


/**
 * Wraps a java.util.UUID.
 */
case class UUID(mostSigBytes: Long, leastSigBytes: Long)

object UUID {
    def apply(id: java.util.UUID): UUID = new UUID(id.getMostSignificantBits, id.getLeastSignificantBits)
    def apply(): UUID = apply(java.util.UUID.randomUUID)

    val Invalid = UUID(0, 0)
}