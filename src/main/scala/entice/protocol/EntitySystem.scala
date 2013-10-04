/**
 * For copyright information see the LICENSE document.
 */

package entice.protocol

import play.api.libs.json._
import info.akshaal.json.jsonmacro._
import scala.collection._


/**
 * General hint:
 * Any classes used inside the components are simple case classes,
 * defined in the Utils.scala file.
 */


/**
 * Simple UUID wrapper.
 */
case class Entity(uuid: UUID) extends Typeable
case class EntityView(entity: Entity, view: View) extends Typeable


/**
 * Provides all components that are used over the network.
 */
sealed trait Component extends mutable.Cloneable[Component] with Typeable

case class Name         (name: String = "John Wayne")       extends Component
case class Position     (pos: Coord2D = Coord2D(0, 0))      extends Component
case class Movement     (dir: Coord2D = Coord2D(1, 1),
                        state: String = "NotMoving")        extends Component { def moveState = MoveState.withName(state) }
case class Appearance   (profession: Int = 1,
                        campaign: Int = 0,
                        sex: Int = 1,
                        height: Int = 0,
                        skinColor: Int = 3,
                        hairColor: Int = 0,
                        hairstyle: Int = 7,
                        face: Int = 31)                     extends Component
case class Animation    (id: String = "none")               extends Component { def animationId = Animations.withName(id) }


/**
 * A state of certain components (or all, depending on the view) of an entity
 */
sealed trait View extends Typeable {
    def components: List[Component]
}

case class AllCompsView         (components: List[Component]) extends View
case class CharacterView        (name: Name, 
                                appearance: Appearance)       extends View { def components = List(name, appearance) }
case class MovementView         (position: Position, 
                                movement: Movement)           extends View { def components = List(position, movement) }
case class VisualsView          (animation: Animation)        extends View { def components = List(animation) }


/**
 * Static serialization init
 */
object EntitySystem {

    import Utils._

    // serialization
    implicit def entityFields           = allFields[Entity]         ('jsonate)
    
    implicit def nameFields             = allFields[Name]           ('jsonate)
    implicit def positionFields         = allFields[Position]       ('jsonate)
    implicit def movementFields         = allFields[Movement]       ('jsonate)
    implicit def appearanceFields       = allFields[Appearance]     ('jsonate)
    implicit def animationFields        = allFields[Animation]      ('jsonate)

    implicit def componentWrites = matchingWrites[Component] {
        case c: Name                    => nameFields               .toWrites.writes(c)
        case c: Position                => positionFields           .toWrites.writes(c)
        case c: Movement                => movementFields           .toWrites.writes(c)
        case c: Appearance              => appearanceFields         .toWrites.writes(c)
        case c: Animation               => animationFields          .toWrites.writes(c)
    }

    // views
    implicit def allCompsViewFields     = allFields[AllCompsView]   ('jsonate)
    implicit def characterViewFields    = allFields[CharacterView]  ('jsonate)
    implicit def movementViewFields     = allFields[MovementView]   ('jsonate)
    implicit def visualsViewFields      = allFields[VisualsView]    ('jsonate)

    implicit def viewWrites = matchingWrites[View] {
        case c: AllCompsView            => allCompsViewFields       .toWrites.writes(c)
        case c: CharacterView           => characterViewFields      .toWrites.writes(c)
        case c: MovementView            => movementViewFields       .toWrites.writes(c)
        case c: VisualsView             => visualsViewFields        .toWrites.writes(c)
    }

    implicit def entityViewFields       = allFields[EntityView]     ('jsonate)

    // deserialization
    implicit def entityFactory          = factory[Entity]           ('fromJson)
    
    implicit def nameFactory            = factory[Name]             ('fromJson)
    implicit def positionFactory        = factory[Position]         ('fromJson)
    implicit def movementFactory        = factory[Movement]         ('fromJson)
    implicit def appearanceFactory      = factory[Appearance]       ('fromJson)
    implicit def animationFactory       = factory[Animation]        ('fromJson)

    implicit def componentReads: Reads[Component] =
        predicatedReads[Component](
            jsHas('type                 -> 'Name)                   -> nameFactory,
            jsHas('type                 -> 'Position)               -> positionFactory,
            jsHas('type                 -> 'Movement)               -> movementFactory,
            jsHas('type                 -> 'Appearance)             -> appearanceFactory,
            jsHas('type                 -> 'Animation)              -> animationFactory
        )

    // views
    implicit def allCompsViewFactory    = factory[AllCompsView]     ('fromJson)
    implicit def characterViewFactory   = factory[CharacterView]    ('fromJson)
    implicit def movementViewFactory    = factory[MovementView]     ('fromJson)
    implicit def visualsViewFactory     = factory[VisualsView]      ('fromJson)

    implicit def viewReads: Reads[View] = 
        predicatedReads[View](
            jsHas('type                 -> 'AllCompsView)           -> allCompsViewFactory,
            jsHas('type                 -> 'CharacterView)          -> characterViewFactory,
            jsHas('type                 -> 'MovementView)           -> movementViewFactory,
            jsHas('type                 -> 'VisualsView)            -> visualsViewFactory 
        )

    implicit def entityViewFactory      = factory[EntityView]       ('fromJson)
}
