using System.Collections.Generic;
using System.Runtime.Serialization;
using Protocol.Types;
using Protocol.Views;

namespace Protocol.Messages.Update
{
        public class UpdateCommand : Message
        {
                [DataMember] public uint timeDelta;
                [DataMember] public List<EntityView> entityViews;
                [DataMember] public List<Entity> added;
                [DataMember] public List<Entity> removed;


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