using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Windows.Forms;
using Protocol.Components;
using Protocol.Messages;

namespace Protocol.Types
{
        [DataContract]
        public class Entity : Typeable
        {
                [DataMember] public UUID uuid;

                public Entity()
                {
                }

                public Entity(UUID uuid)
                {
                        this.uuid = uuid;
                }

                // used to strip all additional info from a subtype of Entity to reduce network traffic
                public static Entity Strip(Entity input)
                {
                        return input != null ? new Entity(input.uuid) : null;
                }

                public static List<Entity> Strip(List<Entity> input)
                {
                        return input != null ? input.Select(Strip).ToList() : null;
                }
        }
}