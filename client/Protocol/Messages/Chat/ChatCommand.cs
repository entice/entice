using System.Collections.Generic;
using System.Runtime.Serialization;

namespace Protocol.Messages.Chat
{
        public class ChatCommand : Message
        {
                [DataMember] public List<string> args;
                [DataMember] public string command;


                public ChatCommand()
                {
                }

                public ChatCommand(string command, List<string> args)
                {
                        this.command = command;
                        this.args = args;
                }
        }
}