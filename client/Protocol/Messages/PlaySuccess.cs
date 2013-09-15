using System.Collections.Generic;
using System.Runtime.Serialization;
using Protocol.Types;

namespace Protocol.Messages
{
        [DataContract]
        public class PlaySuccess : TypedMessage
        {
                [DataMember] private Entity player;
                [DataMember] private List<EntityView> worldState;


                public PlaySuccess(Entity controlledEntity, List<EntityView> worldState)
                {
                        ControlledEntity = controlledEntity;
                        WorldState = worldState;
                }

                public Entity ControlledEntity
                {
                        get { return player; }
                        private set { player = value; }
                }

                public List<EntityView> WorldState
                {
                        get { return worldState; }
                        private set { worldState = value; }
                }
        }
}