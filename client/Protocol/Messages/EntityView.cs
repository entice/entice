using System.Collections.Generic;
using System.Runtime.Serialization;
using Protocol.Types;

namespace Protocol.Messages
{
        [DataContract]
        public class EntityView
        {
                [DataMember] private List<IncomingComponent> components;
                [DataMember] private Entity entity;


                public EntityView(Entity entity, List<IncomingComponent> components)
                {
                        Entity = entity;
                        Components = components;
                }

                public Entity Entity
                {
                        get { return entity; }
                        private set { entity = value; }
                }

                public List<IncomingComponent> Components
                {
                        get { return components; }
                        private set { components = value; }
                }
        }
}