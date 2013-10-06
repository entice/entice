using System.Collections.Generic;
using System.Runtime.Serialization;
using Protocol.Components;

namespace Protocol.Views
{
        public class CharacterView : View
        {
                [DataMember] public Appearance appearance;
                [DataMember] public Name name;

                public CharacterView()
                {
                }

                public CharacterView(Name name, Appearance appearance)
                {
                        this.name = name;
                        this.appearance = appearance;
                }


                public override List<Component> GetComponents()
                {
                        return new List<Component> {name, appearance};
                }
        }
}