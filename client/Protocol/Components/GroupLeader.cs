using System.Collections.Generic;
using System.Runtime.Serialization;
using Protocol.Types;

namespace Protocol.Components
{
        [DataContract]
        public class GroupLeader : Component
        {
                [DataMember] public List<Entity> invited;
                [DataMember] public List<Entity> joinRequests;
                [DataMember] public List<Entity> members;

                public GroupLeader()
                {
                }

                public GroupLeader(List<Entity> members, List<Entity> invited, List<Entity> joinRequests)
                {
                        this.members = Entity.Strip(members);
                        this.invited = Entity.Strip(invited);
                        this.joinRequests = Entity.Strip(joinRequests);
                }
        }
}