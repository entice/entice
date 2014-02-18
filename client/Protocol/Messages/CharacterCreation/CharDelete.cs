using System.Runtime.Serialization;
using Protocol.Types;

namespace Protocol.Messages.CharacterCreation
{
        public class CharDelete : Message
        {
                [DataMember] public Entity chara;


                public CharDelete()
                {
                }

                public CharDelete(UUID charaUuid)
                {
                        this.chara = new Entity(charaUuid);
                }
        }
}