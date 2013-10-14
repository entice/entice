using System.Collections.Generic;
using System.Runtime.Serialization;

namespace Protocol.Messages.Playing
{
        public class PlaySuccess : Message
        {
                [DataMember] public string map;
                [DataMember] public List<EntityView> world;


                public PlaySuccess()
                {
                }

                public PlaySuccess(string map, List<EntityView> world)
                {
                        this.map = map;
                        this.world = world;
                }
        }
}