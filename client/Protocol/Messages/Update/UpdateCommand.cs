using System.Collections.Generic;
using System.Runtime.Serialization;
using Protocol.Types;

namespace Protocol.Messages.Update
{
        public class UpdateCommand : Message
        {
                [DataMember] public List<Entity> added;
                [DataMember] public List<EntityView> entityViews;
                [DataMember] public List<Entity> removed;
                [DataMember] public uint timeDelta;


                public UpdateCommand()
                {
                }

                public UpdateCommand(uint timeDelta, List<EntityView> entityViews, List<Entity> added, List<Entity> removed)
                {
                        this.timeDelta = timeDelta;
                        this.entityViews = entityViews;
                        this.added = added;
                        this.removed = removed;
                }
        }
}