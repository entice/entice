using System.Runtime.Serialization;
using Protocol.Types;

namespace Protocol.Messages.Chat
{
        public class ChatMessage : Message
        {
                [DataMember] public Entity sender;
                [DataMember] public string message;
                [DataMember] public string channel;

                public ChatMessage()
                {
                }

                public ChatMessage(UUID sender, string message, string channel)
                {
                        this.sender = new Entity(sender);
                        this.message = message;
                        this.channel = channel;
                }
        }
}