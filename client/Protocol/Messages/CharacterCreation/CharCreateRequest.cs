using System.Runtime.Serialization;
using Protocol.Views;

namespace Protocol.Messages.CharacterCreation
{
        public class CharCreateRequest : Message
        {
                [DataMember] public CharacterView chara;

                public CharCreateRequest()
                {
                }

                public CharCreateRequest(CharacterView chara)
                {
                        this.chara = chara;
                }
        }
}