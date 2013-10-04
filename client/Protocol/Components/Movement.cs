using System.Runtime.Serialization;
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


                public Movement(Coord2D direction, float speedModifier, MovementState state)
                {
                        Direction = direction;
                        SpeedModifier = speedModifier;
                        State = state;
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
        }
}