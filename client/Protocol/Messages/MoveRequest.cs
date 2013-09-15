using System.Runtime.Serialization;
using Protocol.Components;

namespace Protocol.Messages
{
        [DataContract]
        public class MoveRequest : TypedMessage
        {
                [DataMember] private Movement move;
                [DataMember] private Position pos;


                public MoveRequest(Position position, Movement movement)
                {
                        Position = position;
                        Movement = movement;
                }

                public Position Position
                {
                        get { return pos; }
                        private set { pos = value; }
                }

                public Movement Movement
                {
                        get { return move; }
                        private set { move = value; }
                }
        }
}