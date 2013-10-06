using System.Runtime.Serialization;

namespace Protocol.Messages.Login
{
        public class LoginRequest : Message
        {
                [DataMember] public string email;
                [DataMember] public string password;

                public LoginRequest()
                {
                }

                public LoginRequest(string email, string password)
                {
                        this.email = email;
                        this.password = password;
                }
        }
}