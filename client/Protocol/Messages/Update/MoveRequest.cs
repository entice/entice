using System.Runtime.Serialization;
using Protocol.Components;

namespace Protocol.Messages.Update
{
        public class MoveRequest : Message
        {
                [DataMember] public Movement movement;
                [DataMember] public Position position;

                public MoveRequest()
                {
                }

                public MoveRequest(Position position, Movement movement)
                {
                        this.position = position;
                        this.movement = movement;
                }
        }
}