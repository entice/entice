using System.Runtime.Serialization;
using Protocol.Types;

namespace Protocol.Components
{
        [DataContract]
        public class Movement : Component
        {
                [DataMember] public Coord2D goal;
                [DataMember] public string state;

                public Movement()
                {
                }

                public Movement(Coord2D goal, string state)
                {
                        this.goal = goal;
                        this.state = state;
                }
        }
}