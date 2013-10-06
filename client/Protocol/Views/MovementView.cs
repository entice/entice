using System.Collections.Generic;
using System.Runtime.Serialization;
using Protocol.Components;

namespace Protocol.Views
{
        public class MovementView : View
        {
                [DataMember] public Movement movement;
                [DataMember] public Position position;

                public MovementView()
                {
                }

                public MovementView(Position position, Movement movement)
                {
                        this.position = position;
                        this.movement = movement;
                }


                public override List<Component> GetComponents()
                {
                        return new List<Component> {position, movement};
                }
        }
}