using System.Runtime.Serialization;
using Protocol.Types;

namespace Protocol.Messages.StartPlaying
{
        public class PlayRequest : Message
        {
                [DataMember] public Entity chara;

                public PlayRequest()
                {
                }

                public PlayRequest(Entity controlledCharacter)
                {
                        chara = new Entity(controlledCharacter);
                }
        }
}