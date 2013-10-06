using System.Runtime.Serialization;

namespace Protocol.Components
{
        [DataContract]
        public class Name : Component
        {
                [DataMember] public string name;


                public Name()
                {
                }

                public Name(string name)
                {
                        this.name = name;
                }
        }
}