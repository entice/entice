using System.Runtime.Serialization;
using Protocol.Messages;
using Protocol.Types;

namespace Protocol.Components
{
        [DataContract]
        public class Movement : Component
        {
                public enum MovementState
                {
                        Moving,
                        NotMoving
                }

                [DataMember] private Coord2D dir;
                [DataMember] private float speed;
                [DataMember] private string state;
                [DataMember] private string type;

                public Movement(Coord2D direction, float speedModifier, MovementState state)
                {
                        type = GetType().Name;
                        dir = direction;
                        speed = speedModifier;
                        State = state;
                }

                public Movement(IncomingComponent incomingComponent)
                {
                        Type = GetType().Name;
                        Direction = incomingComponent.Direction;
                        SpeedModifier = incomingComponent.Speed;
                        state = incomingComponent.State;
                }

                public string Type
                {
                        get { return type; }
                        set { type = value; }
                }

                public Coord2D Direction
                {
                        get { return dir; }
                        set { dir = value; }
                }

                public float SpeedModifier
                {
                        get { return speed; }
                        set { speed = value; }
                }

                public MovementState State
                {
                        get { return state.Equals("Moving") ? MovementState.Moving : MovementState.NotMoving; }
                        set { state = value == MovementState.Moving ? "Moving" : "NotMoving"; }
                }

                public IncomingComponent ToIncomingComponent()
                {
                        return new IncomingComponent {Type = Type, Direction = dir, State = state};
                }
        }
}