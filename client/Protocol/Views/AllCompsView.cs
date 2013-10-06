using System.Collections.Generic;
using System.Runtime.Serialization;
using Protocol.Components;

namespace Protocol.Views
{
        public class AllCompsView : View
        {
                [DataMember] public List<Component> components;


                public AllCompsView()
                {
                }

                public AllCompsView(List<Component> components)
                {
                        this.components = components;
                }

                public override List<Component> GetComponents()
                {
                        return components;
                }
        }
}