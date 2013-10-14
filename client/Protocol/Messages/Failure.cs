using System.Runtime.Serialization;

namespace Protocol.Messages
{
        public class Failure : Message
        {
                [DataMember] public string error;
        }
}