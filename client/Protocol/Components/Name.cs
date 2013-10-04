using System.Runtime.Serialization;

namespace Protocol.Components
{
        [DataContract]
        public class Name : Component
        {
                [DataMember] private string name;


                public Name(string name)
                {
                        Value = name;
                }

                public string Value
                {
                        get { return name; }
                        private set { name = value; }
                }
        }
}