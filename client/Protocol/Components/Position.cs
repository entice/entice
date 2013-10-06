using System.Runtime.Serialization;
using Protocol.Types;

namespace Protocol.Components
{
        [DataContract]
        public class Position : Component
        {
                [DataMember] public Coord2D pos;

                public Position()
                {
                }

                public Position(Coord2D pos)
                {
                        this.pos = pos;
                }
        }
}