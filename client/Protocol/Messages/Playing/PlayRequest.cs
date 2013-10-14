using System.Runtime.Serialization;
using Protocol.Types;

namespace Protocol.Messages.Playing
{
        public class PlayRequest : Message
        {
                [DataMember] public Entity chara;

                public PlayRequest()
                {
                }

                public PlayRequest(Entity controlledCharacter)
                {
                        chara = Entity.Strip(controlledCharacter);
                }
        }
}