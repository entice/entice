using System.Runtime.Serialization;
using Protocol.Types;

namespace Protocol.Messages.Update
{
        public class GroupMergeRequest : Message
        {
                [DataMember] public Entity target;

                public GroupMergeRequest()
                {
                }

                public GroupMergeRequest(UUID targetUUID)
                {
                        this.target = new Entity(targetUUID);
                }
        }
}