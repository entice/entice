using System.Runtime.Serialization;
using Protocol.Messages;
using Protocol.Types;

namespace Protocol.Components
{
        [DataContract]
        public class Name : Component
        {
                [DataMember] private string name;
                [DataMember] private string type;


                public Name(string name)
                {
                        Type = GetType().Name;
                        Value = name;
                }

                public Name(IncomingComponent incomingComponent)
                {
                        Type = GetType().Name;
                        Value = incomingComponent.Name;
                }

                public string Type
                {
                        get { return type; }
                        private set { type = value; }
                }

                public string Value
                {
                        get { return name; }
                        private set { name = value; }
                }

                public IncomingComponent ToIncomingComponent()
                {
                        return new IncomingComponent {Type = Type, Name = Value};
                }
        }
}