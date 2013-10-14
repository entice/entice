using System.Runtime.Serialization;

namespace Protocol.Messages.Playing
{
        public class PlayChangeMap : Message
        {
                [DataMember] public string map;


                public PlayChangeMap()
                {
                }

                public PlayChangeMap(string map)
                {
                        this.map = map;
                }
        }
}