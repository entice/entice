using System.Runtime.Serialization;
using Protocol.Messages;
using Protocol.Types;

namespace Protocol.Components
{
        [DataContract]
        public class Position : Component
        {
                [DataMember] private Coord2D pos;
                [DataMember] private string type;


                public Position(Coord2D position)
                {
                        Type = GetType().Name;
                        Value = position;
                }

                public Position(IncomingComponent incomingComponent)
                {
                        Type = GetType().Name;
                        Value = incomingComponent.Position;
                }

                public string Type
                {
                        get { return type; }
                        private set { type = value; }
                }

                public Coord2D Value
                {
                        get { return pos; }
                        private set { pos = value; }
                }

                public IncomingComponent ToIncomingComponent()
                {
                        return new IncomingComponent {Type = Type, Position = Value};
                }
        }
}