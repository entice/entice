using System.Runtime.Serialization;
using Protocol.Views;

namespace Protocol.Messages.Update
{
        public class UpdateRequest : Message
        {
                [DataMember] public EntityView entityView;

                public UpdateRequest()
                {
                }

                public UpdateRequest(EntityView entityView)
                {
                        this.entityView = entityView;
                }
        }
}