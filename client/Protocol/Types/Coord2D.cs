using System.Drawing;
using System.Runtime.Serialization;

namespace Protocol.Types
{
        [DataContract]
        public class Coord2D : Typeable
        {
                [DataMember] public float x;
                [DataMember] public float y;

                public Coord2D()
                {
                }

                public Coord2D(float[] position)
                {
                        x = position[0];
                        y = position[1];
                }

                public Coord2D(float x, float y)
                {
                        this.x = x;
                        this.y = y;
                }

                public PointF ToPointF()
                {
                        return new PointF(x, y);
                }

                public float[] ToNative()
                {
                        return new[] { x, y};
                }
        }
}