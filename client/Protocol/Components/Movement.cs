using System.Runtime.Serialization;
using Protocol.Types;

namespace Protocol.Components
{
        [DataContract]
        public class Movement : Component
        {
                [DataMember] public Coord2D dir;
                [DataMember] public float speed;
                [DataMember] public string state;

                public Movement()
                {
                }

                public Movement(Coord2D dir, float speed, string state)
                {
                        this.dir = dir;
                        this.speed = speed;
                        this.state = state;
                }
        }
}