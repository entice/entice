using System;
using System.Runtime.Serialization;

namespace Protocol.Types
{
        [DataContract]
        public class UUID : Typeable, IEquatable<UUID>
        {
                [DataMember] public long leastSigBytes;
                [DataMember] public long mostSigBytes;

                public UUID()
                {
                }

                public UUID(long leastSigBytes, long mostSigBytes)
                {
                        this.leastSigBytes = leastSigBytes;
                        this.mostSigBytes = mostSigBytes;
                }

                public bool Equals(UUID other)
                {
                        return mostSigBytes == other.mostSigBytes && leastSigBytes == other.leastSigBytes;
                }

                public override int GetHashCode()
                {
                        return mostSigBytes.GetHashCode() ^ leastSigBytes.GetHashCode();
                }

                public override string ToString()
                {
                        return string.Format("[{0}:{1}]", mostSigBytes, leastSigBytes);
                }
        }
}