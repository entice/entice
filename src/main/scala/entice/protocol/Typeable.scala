/**
 * For copyright information see the LICENSE document.
 */

 package entice.protocol


/**
 * Extracts the type of the `Product` inheriting this,
 * and adds it as an accessible value.
 */
trait Typeable {
    def productPrefix: String
    val `type` = productPrefix
}