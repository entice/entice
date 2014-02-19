using System.Runtime.Serialization;
using Protocol.Types;

namespace Protocol.Messages.Update
{
        public class MoveRequest : Message
        {
                [DataMember] public Coord2D direction;

                public MoveRequest()
                {
                }

                public MoveRequest(Coord2D direction)
                {
                        this.direction = direction;
                }
        }
}