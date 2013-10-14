using System.Runtime.Serialization;
using Protocol.Components;

namespace Protocol.Messages.CharacterCreation
{
        public class CharCreateRequest : Message
        {
                [DataMember] public Appearance appearance;
                [DataMember] public Name name;

                public CharCreateRequest()
                {
                }

                public CharCreateRequest(Name name, Appearance appearance)
                {
                        this.name = name;
                        this.appearance = appearance;
                }
        }
}