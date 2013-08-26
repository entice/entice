/**
 * For copyright information see the LICENSE document.
 */

package entice.protocol

import info.akshaal.json.jsonmacro._


/**
 * Wraps a java.util.UUID.
 */
case class UUID(mostSigBytes: Long, leastSigBytes: Long) {
    
    def unapply(): java.util.UUID = {
        new java.util.UUID(mostSigBytes, leastSigBytes)
    }
}


object UUID {

    // serialization
    implicit def uuidFields     = allFields[UUID]('jsonate)

    // deserialization
    implicit def uuidFactory    = factory[UUID]('fromJson)

    def apply(id: java.util.UUID) = {
        new UUID(id.getMostSignificantBits, id.getLeastSignificantBits)
    }

    def apply() = {
        val id = java.util.UUID.randomUUID
        new UUID(id.getMostSignificantBits, id.getLeastSignificantBits)
    }
}