using System.Runtime.Serialization;

namespace Protocol.Messages.CharacterCreation
{
        public class CharCreateFail : Message
        {
                [DataMember] public string error;
        }
}