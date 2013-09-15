using System.Runtime.Serialization;

namespace Protocol.Messages
{
        [DataContract]
        public class LoginRequest : TypedMessage
        {
                [DataMember] private string email;
                [DataMember] private string password;

                public LoginRequest(string email, string password)
                {
                        EMail = email;
                        Password = password;
                }


                public string EMail
                {
                        get { return email; }
                        private set { email = value; }
                }

                public string Password
                {
                        get { return password; }
                        private set { password = value; }
                }
        }
}