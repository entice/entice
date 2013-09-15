using System;
using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;

namespace Protocol
{
        [DataContract]
        public class TypedMessage
        {
                [DataMember] private string type;


                public string Type
                {
                        get { return type; }
                        private set { type = value; }
                }

                public byte[] Serialize()
                {
                        type = GetType().Name;

                        var messageStream = new MemoryStream();

                        var serializer = new DataContractJsonSerializer(GetType());
                        serializer.WriteObject(messageStream, this);
                        messageStream.Position = 0;

                        byte[] messageData = messageStream.ToArray();
                        byte[] messageLength = BitConverter.GetBytes((ushort) messageData.Length);

                        var finalStream = new MemoryStream();

                        finalStream.WriteByte(messageLength[1]);
                        finalStream.WriteByte(messageLength[0]);
                        finalStream.Write(messageData, 0, messageData.Length);
                        finalStream.Position = 0;

                        return finalStream.ToArray();
                }
        }
}