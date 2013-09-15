using System.Collections.Generic;
using System.Runtime.Serialization;
using Protocol.Types;

namespace Protocol.Messages
{
        [DataContract]
        public class GameUpdate : TypedMessage
        {
                [DataMember] private List<Entity> added;
                [DataMember] private List<EntityView> entityDiffs;
                [DataMember] private List<Entity> removed;
                [DataMember] private uint timeDelta;


                public GameUpdate(uint timeDelta, List<EntityView> entityChanges, List<Entity> addedEntities, List<Entity> removedEntities)
                {
                        TimeDelta = timeDelta;
                        EntityChanges = entityChanges;
                        AddedEntities = addedEntities;
                        RemovedEntities = removedEntities;
                }

                public uint TimeDelta
                {
                        get { return timeDelta; }
                        private set { timeDelta = value; }
                }

                public List<EntityView> EntityChanges
                {
                        get { return entityDiffs; }
                        private set { entityDiffs = value; }
                }

                public List<Entity> AddedEntities
                {
                        get { return added; }
                        private set { added = value; }
                }

                public List<Entity> RemovedEntities
                {
                        get { return removed; }
                        private set { removed = value; }
                }
        }
}