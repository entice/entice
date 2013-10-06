using System.Runtime.Serialization;

namespace Protocol.Messages.Chat
{
        public class ServerMessage : Message
        {
                [DataMember] public string message;
        }
}