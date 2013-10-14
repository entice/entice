using System.Collections.Generic;
using System.Runtime.Serialization;
using Protocol.Components;
using Protocol.Types;

namespace Protocol.Messages
{
        public class EntityView : Typeable
        {
                [DataMember] public List<Component> added;
                [DataMember] public List<Component> changed;
                [DataMember] public Entity entity;
                [DataMember] public List<string> removed;

                public EntityView()
                {
                }

                public EntityView(Entity entity, List<Component> changed, List<Component> added, List<string> removed)
                {
                        this.entity = Entity.Strip(entity);
                        this.changed = changed;
                        this.added = added;
                        this.removed = removed;
                }
        }
}