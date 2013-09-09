/**
 * For copyright information see the LICENSE document.
 */

package entice.protocol

import play.api.libs.json._
import info.akshaal.json.jsonmacro._


// Entity related classes
case class Entity(uuid: UUID)

case class EntityView(entity: Entity, components: Set[Component] = Set.empty)

object EntityView {
    def apply(m: Map[Entity, Set[Component]]): List[EntityView] = {
        (for ((e, c) <- m) yield { EntityView(e, c) }).toList
    }
}

// CES utils
case class Coord2D(x: Float, y: Float)


// Components
sealed trait Component {
    def productPrefix: String       // implemented by all case classes, contains class name
    val `type` = productPrefix
}

case class Name     (name: String)                                                                  extends Component
case class Position (pos: Coord2D = Coord2D(0, 0))                                                  extends Component
case class Movement (dir: Coord2D = Coord2D(1, 1), speed: Float = 288, state: String = "NotMoving") extends Component { def moveState = MoveState.withName(state) }


// Enums
object MoveState extends Enumeration {
   val NotMoving    = Value("NotMoving")
   val Moving       = Value("Moving")
}


/**
 * Static serialization init
 */
object EntitySystem {

    import UUID._

    // serialization
    implicit def componentWrites = matchingWrites[Component] {
        case c: Name        => nameFields       .toWrites.writes(c)
        case c: Position    => positionFields   .toWrites.writes(c)
        case c: Movement    => movementFields   .toWrites.writes(c)
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
            jsHas('type -> 'Name)       -> nameFactory,
            jsHas('type -> 'Position)   -> positionFactory,
            jsHas('type -> 'Movement)   -> movementFactory
        )
}