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
                        var serializer2 = new JavaScriptSerializer();

                        string serialized = serializer2.Serialize(this);

                        byte[] messageData = Encoding.ASCII.GetBytes(serialized);
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