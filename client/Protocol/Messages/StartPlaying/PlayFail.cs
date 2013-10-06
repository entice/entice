using System.Runtime.Serialization;

namespace Protocol.Messages.StartPlaying
{
        public class PlayFail : Message
        {
                [DataMember] public string error;
        }
}