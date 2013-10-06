using System.Runtime.Serialization;

namespace Protocol.Messages.Login
{
        public class LoginFail : Message
        {
                [DataMember] public string error;
        }
}