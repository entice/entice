using System.Runtime.Serialization;
using Protocol.Types;

namespace Protocol.Messages.Update
{
        public class GroupKickRequest : Message
        {
                [DataMember] public Entity target;

                public GroupKickRequest()
                {
                }

                public GroupKickRequest(Entity target)
                {
                        this.target = Entity.Strip(target);
                }
        }
}