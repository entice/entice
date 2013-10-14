using System.Runtime.Serialization;
using Protocol.Types;

namespace Protocol.Messages.Chat
{
        public class ChatMessage : Message
        {
                [DataMember] public string message;
                [DataMember] public Entity sender;

                public ChatMessage()
                {
                }

                public ChatMessage(Entity sender, string message)
                {
                        this.sender = Entity.Strip(sender);
                        this.message = message;
                }
        }
}