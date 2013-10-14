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
case class Entity       (uuid: UUID = UUID())               extends Typeable


/**
 * Holds the state or a state diff of an entity.
 * Remember that a state of an entity is defined by its components,
 * but for state transitions we also need to know which components were
 * removed or added.The lists are disjunct.
 *
 * Details:
 * - `changed` only holds components that were present (in the recipients CES) 
 *   before the last entity view of that entity got send, but changed since then.
 * - `added` only holds components that were added since the last entity update.
 * - `removed` only holds the type-strings (see Typeable) of the components that
 *   were removed since the last entity update.
 *
 * Some patterns:
 * - Entity spawn:   EntityView(_, Nil, List(???), Nil)
 * - Entity despawn: EntityView(_, Nil, Nil, List(???))
 * - World dump:     EntityView(_, List(???), Nil, Nil)
 * - State trans.:   EntityView(_, List(???), List(???), List(???))
 *
 * State transitions can include any other of the above mentioned patterns, so
 * one still needs to check if the entity has really been added or removed, which
 * is included in the UpdateCommand from the server.
 *
 * Note that because of the packet-design, only the server can spawn or despawn 
 * entities, which is intended.
 */
case class EntityView   (entity: Entity, 
                        changed: List[Component],
                        added: List[Component],
                        removed: List[String])              extends Typeable


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
case class GroupLeader  (members: List[Entity] = Nil,
                        invited: List[Entity] = Nil,
                        joinRequests: List[Entity] = Nil)   extends Component
case class GroupMember  (leader: Entity = Entity())         extends Component


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
    implicit def groupLeaderFields      = allFields[GroupLeader]    ('jsonate)
    implicit def groupMemberFields      = allFields[GroupMember]    ('jsonate)

    implicit def componentWrites = matchingWrites[Component] {
        case c: Name                    => nameFields               .toWrites.writes(c)
        case c: Position                => positionFields           .toWrites.writes(c)
        case c: Movement                => movementFields           .toWrites.writes(c)
        case c: Appearance              => appearanceFields         .toWrites.writes(c)
        case c: Animation               => animationFields          .toWrites.writes(c)
        case c: GroupLeader             => groupLeaderFields        .toWrites.writes(c)
        case c: GroupMember             => groupMemberFields        .toWrites.writes(c)  
    }

    implicit def entityViewFields       = allFields[EntityView]     ('jsonate)


    // deserialization
    implicit def entityFactory          = factory[Entity]           ('fromJson)
    
    implicit def nameFactory            = factory[Name]             ('fromJson)
    implicit def positionFactory        = factory[Position]         ('fromJson)
    implicit def movementFactory        = factory[Movement]         ('fromJson)
    implicit def appearanceFactory      = factory[Appearance]       ('fromJson)
    implicit def animationFactory       = factory[Animation]        ('fromJson)
    implicit def groupLeaderFactory     = factory[GroupLeader]      ('fromJson)
    implicit def groupMemberFactory     = factory[GroupMember]      ('fromJson)

    implicit def componentReads: Reads[Component] =
        predicatedReads[Component](
            jsHas('type                 -> 'Name)                   -> nameFactory,
            jsHas('type                 -> 'Position)               -> positionFactory,
            jsHas('type                 -> 'Movement)               -> movementFactory,
            jsHas('type                 -> 'Appearance)             -> appearanceFactory,
            jsHas('type                 -> 'Animation)              -> animationFactory,
            jsHas('type                 -> 'GroupLeader)            -> groupLeaderFactory,
            jsHas('type                 -> 'GroupMember)            -> groupMemberFactory
        )

    implicit def entityViewFactory      = factory[EntityView]       ('fromJson)
}
