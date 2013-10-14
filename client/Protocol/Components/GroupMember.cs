using System.Runtime.Serialization;
using Protocol.Types;

namespace Protocol.Components
{
        [DataContract]
        public class GroupMember : Component
        {
                [DataMember] public Entity leader;

                public GroupMember()
                {
                }

                public GroupMember(Entity leader)
                {
                        this.leader = Entity.Strip(leader);
                }
        }
}