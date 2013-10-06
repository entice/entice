using System.Collections.Generic;
using System.Runtime.Serialization;
using Protocol.Types;
using Protocol.Views;

namespace Protocol.Messages.Update
{
        public class UpdateCommand : Message
        {
                [DataMember] public List<Entity> added;
                [DataMember] public List<EntityView> entityViews;

                [DataMember] public List<Entity> removed;
                [DataMember] public uint timeDelta;
        }
}