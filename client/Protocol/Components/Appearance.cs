using System.Runtime.Serialization;

namespace Protocol.Components
{
        public class Appearance : Component
        {
                [DataMember] public int campaign;
                [DataMember] public int face;
                [DataMember] public int hairColor;
                [DataMember] public int hairstyle;
                [DataMember] public int height;
                [DataMember] public int profession;
                [DataMember] public int sex;
                [DataMember] public int skinColor;

        }
}