/**
 * For copyright information see the LICENSE document.
 */

package entice.protocol

import play.api.libs.json._
import info.akshaal.json.jsonmacro._


// Entity related classes
case class Entity(uuid: UUID)
case class EntityView(entity: Entity, components: List[Component] = List.empty)


// CES utils
case class Coord2D(x: Float, y: Float)


// Components
sealed trait Component
case class Name(name: String) extends Component
case class Position(pos: Coord2D = Coord2D(0, 0)) extends Component
case class Movement(dir: Coord2D = Coord2D(1, 1), speed: Float = 288) extends Component


/**
 * Static serialization init
 */
object EntitySystem {

    import UUID._

    // serialization
    implicit def componentWrites = matchingWrites[Component] {
        case c: Name        => nameFields       .extra('type -> 'name).toWrites.writes(c)
        case c: Position    => positionFields   .extra('type -> 'pos).toWrites.writes(c)
        case c: Movement    => movementFields   .extra('type -> 'move).toWrites.writes(c)
    }

    implicit def entityFields       = allFields[Entity]     ('jsonate)
    implicit def coord2dFields      = allFields[Coord2D]    ('jsonate)
    implicit def nameFields         = allFields[Name]       ('jsonate)
    implicit def positionFields     = allFields[Position]   ('jsonate)
    implicit def movementFields     = allFields[Movement]   ('jsonate)

    implicit def entityViewFields   = allFields[EntityView] ('jsonate) // implicits need to be in order of dep

    // deserialization
    implicit def entityFactory      = factory[Entity]       ('fromJson)
    implicit def entityViewFactory  = factory[EntityView]   ('fromJson)
    implicit def coord2dFactory     = factory[Coord2D]      ('fromJson)
    implicit def nameFactory        = factory[Name]         ('fromJson)
    implicit def positionFactory    = factory[Position]     ('fromJson)
    implicit def movementFactory    = factory[Movement]     ('fromJson)

    implicit def componentReads: Reads[Component] =
        predicatedReads[Component](
            jsHas('type -> 'name)   -> nameFactory,
            jsHas('type -> 'pos)    -> positionFactory,
            jsHas('type -> 'move)   -> movementFactory
        )
}