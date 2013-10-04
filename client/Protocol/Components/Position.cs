using System.Runtime.Serialization;
using Protocol.Types;

namespace Protocol.Components
{
        [DataContract]
        public class Position : Component
        {
                [DataMember] private Coord2D pos;


                public Position(Coord2D position)
                {
                        Value = position;
                }

                public Coord2D Value
                {
                        get { return pos; }
                        private set { pos = value; }
                }
        }
}