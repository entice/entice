using System;
using System.Runtime.Serialization;

namespace Protocol.Types
{
        [DataContract]
        public class UUID : IEquatable<UUID>
        {
                [DataMember] private long leastSigBytes;
                [DataMember] private long mostSigBytes;


                public UUID(long leastSignificantBytes, long mostSignificantBytes)
                {
                        LeastSignificantBytes = leastSignificantBytes;
                        MostSignificantBytes = mostSignificantBytes;
                }

                public long LeastSignificantBytes
                {
                        get { return leastSigBytes; }
                        private set { leastSigBytes = value; }
                }

                public long MostSignificantBytes
                {
                        get { return mostSigBytes; }
                        private set { mostSigBytes = value; }
                }

                public bool Equals(UUID other)
                {
                        return MostSignificantBytes == other.MostSignificantBytes && LeastSignificantBytes == other.LeastSignificantBytes;
                }

                public override int GetHashCode()
                {
                        return MostSignificantBytes.GetHashCode() ^ LeastSignificantBytes.GetHashCode();
                }

                public override string ToString()
                {
                        return string.Format("[{0}:{1}]", MostSignificantBytes, LeastSignificantBytes);
                }
        }
}