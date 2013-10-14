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


                public Appearance()
                {
                }


                public Appearance(int packed)
                {
                        sex = GetPackedValue(packed, 0, 1);
                        height = GetPackedValue(packed, 1, 4);
                        skinColor = GetPackedValue(packed, 5, 5);
                        hairColor = GetPackedValue(packed, 10, 5);
                        face = GetPackedValue(packed, 15, 5);
                        profession = GetPackedValue(packed, 20, 4);
                        hairstyle = GetPackedValue(packed, 24, 5);
                        campaign = GetPackedValue(packed, 29, 3);
                }

                public Appearance(int sex, int height, int skinColor, int hairColor, int face, int profession, int hairstyle, int campaign)
                {
                        this.sex = sex;
                        this.height = height;
                        this.skinColor = skinColor;
                        this.hairColor = hairColor;
                        this.face = face;
                        this.profession = profession;
                        this.hairstyle = hairstyle;
                        this.campaign = campaign;
                }

                public uint GetPackedValue()
                {
                        return PackValue(sex, 0, 1) |
                               PackValue(height, 1, 4) |
                               PackValue(skinColor, 5, 5) |
                               PackValue(hairColor, 10, 5) |
                               PackValue(face, 15, 5) |
                               PackValue(profession, 20, 4) |
                               PackValue(hairstyle, 24, 5) |
                               PackValue(campaign, 29, 3);
                }

                private uint PackValue(int value, int position, int length)
                {
                        uint mask = ~(0xFFFFFFFF << length);

                        var maskedValue = (uint) (value & mask);

                        return maskedValue << position;
                }

                private int GetPackedValue(int packed, int position, int length)
                {
                        uint mask = ~(0xFFFFFFFF << length);

                        int shifted = packed >> position;

                        return (int) (shifted & mask);
                }
        }
}