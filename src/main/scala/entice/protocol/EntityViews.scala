/**
 * For copyright information see the LICENSE document.
 */

package entice.protocol


/**
 * A view provides a certain state of a game object.
 */
sealed trait EntityView {
    type T
    def self = this.asInstanceOf[T]

    def components: Array[Component] 
    var entity: Option[Entity] = None

    def apply(e: Entity) = {
        this.entity = Some(e)
        self
    }
}




case class AllCompsView (components: Array[Component])  extends EntityView
case class CharacterView(name: Name, 
                        appearance: Appearance)         extends EntityView { def components = Array(name, appearance)}
case class MovementView (position: Position, 
                        movement: Movement)             extends EntityView { def components = Array(position, movement)}