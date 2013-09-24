/**
 * For copyright information see the LICENSE document.
 */

package entice.protocol


/**
 * General hint:
 * Any classes used inside the components are simple case classes,
 * defined in the Utils.scala file.
 */


/**
 * Simple UUID wrapper. (Exists so we attach )
 */
case class Entity(uuid: UUID)


/**
 * Provides all components that are used over the network.
 */
sealed trait Component extends Cloneable

case class Name         (name: String)                      extends Component
case class Position     (pos: Coord2D = Coord2D(0, 0))      extends Component
case class Movement     (dir: Coord2D = Coord2D(1, 1),
                        state: String = "NotMoving")        extends Component { def moveState = MoveState.withName(state) }
case class Appearance   (profession: Int,
                        campaign: Int,
                        sex: Int,
                        height: Int,
                        skinColor: Int,
                        hairColor: Int,
                        hairstyle: Int,
                        face: Int)                          extends Component
