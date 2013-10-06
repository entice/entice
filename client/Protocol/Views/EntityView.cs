using System.Runtime.Serialization;
using Protocol.Types;

namespace Protocol.Views
{
        public class EntityView : Serializable
        {
                [DataMember] public Entity entity;
                [DataMember] public View view;

                public EntityView()
                {
                }

                public EntityView(Entity entity, View view)
                {
                        this.entity = entity;
                        this.view = view;
                }
        }
}