using System.Drawing;
using System.Runtime.Serialization;

namespace Protocol.Types
{
        [DataContract]
        public class Coord2D
        {
                [DataMember] private float x;
                [DataMember] private float y;


                public Coord2D(float x, float y)
                {
                        X = x;
                        Y = y;
                }

                public Coord2D(float[] native)
                {
                        X = native[0];
                        Y = native[1];
                }

                public float X
                {
                        get { return x; }
                        private set { x = value; }
                }

                public float Y
                {
                        get { return y; }
                        private set { y = value; }
                }

                public float[] ToNative()
                {
                        return new[] {X, Y};
                }

                public PointF ToPointF()
                {
                        return new PointF(x, y);
                }
        }
}