using System.Collections.Generic;
using System.Runtime.Serialization;

namespace Protocol.Messages.Login
{
        public class LoginSuccess : Message
        {
                [DataMember] public List<EntityView> chars;


                public LoginSuccess()
                {
                }

                public LoginSuccess(List<EntityView> chars)
                {
                        this.chars = chars;
                }
        }
}