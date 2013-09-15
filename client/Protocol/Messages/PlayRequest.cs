using System.Runtime.Serialization;

namespace Protocol.Messages
{
        [DataContract]
        public class PlayRequest : TypedMessage
        {
                [DataMember] private long key;


                public PlayRequest(long key)
                {
                        Key = key;
                }

                public long Key
                {
                        get { return key; }
                        private set { key = value; }
                }
        }
}