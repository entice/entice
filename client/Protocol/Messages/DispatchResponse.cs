using System.Runtime.Serialization;

namespace Protocol.Messages
{
        [DataContract]
        public class DispatchResponse : TypedMessage
        {
                [DataMember] private string host;
                [DataMember] private long key;
                [DataMember] private int port;


                public DispatchResponse(string host, int port, long key)
                {
                        Host = host;
                        Port = port;
                        Key = key;
                }

                public string Host
                {
                        get { return host; }
                        private set { host = value; }
                }

                public int Port
                {
                        get { return port; }
                        private set { port = value; }
                }

                public long Key
                {
                        get { return key; }
                        private set { key = value; }
                }
        }
}