using System.Runtime.Serialization;

namespace Protocol.Messages
{
        [DataContract]
        public class LoginFail : TypedMessage
        {
                [DataMember] private string error;


                public string Error
                {
                        get { return error; }
                        private set { error = value; }
                }
        }
}