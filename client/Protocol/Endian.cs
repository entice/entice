using System;

namespace Protocol
{
        public static class Endian
        {
                public static int Swap(int input)
                {
                        byte[] buf = BitConverter.GetBytes(input);

                        return (buf[0] << 24) | (buf[1] << 16) | (buf[2] << 8) | buf[3];
                }
        }
}