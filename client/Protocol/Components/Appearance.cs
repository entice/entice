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

                public Appearance()
                {
                }
        }
}