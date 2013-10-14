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

                public CharDelete(Entity chara)
                {
                        this.chara = Entity.Strip(chara);
                }
        }
}