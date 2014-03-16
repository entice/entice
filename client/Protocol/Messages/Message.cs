using System;
using System.IO;
using System.Text;
using System.Web.Script.Serialization;

namespace Protocol.Messages
{
        public abstract class Message : Typeable
        {
                public byte[] Serialize()
                {
                        var serializer = new JavaScriptSerializer();

                        string serialized = serializer.Serialize(this);

                        byte[] messageData = Encoding.UTF8.GetBytes(serialized);
                        byte[] messageLength = BitConverter.GetBytes(messageData.Length);
                        Array.Reverse(messageLength); //to big endian

                        var finalStream = new MemoryStream();

                        finalStream.Write(messageLength, 0, messageLength.Length);
                        finalStream.Write(messageData, 0, messageData.Length);
                        finalStream.Position = 0;

                        return finalStream.ToArray();
                }
        }
}