using System.Runtime.Serialization;
using Protocol.Types;

namespace Protocol.Messages.CharacterCreation
{
        public class CharCreateSuccess : Message
        {
                [DataMember] public Entity chara;
        }
}