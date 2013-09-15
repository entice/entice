using System.Runtime.Serialization;
using Protocol.Types;

namespace Protocol.Messages
{
        [DataContract]
        public class IncomingComponent
        {
                [DataMember] private Coord2D dir;
                [DataMember] private string name;
                [DataMember] private Coord2D pos;
                [DataMember] private float speed;
                [DataMember] private string state;
                [DataMember] private string type;


                public string Type
                {
                        get { return type; }
                        set { type = value; }
                }

                public string Name
                {
                        get { return name; }
                        set { name = value; }
                }

                public Coord2D Position
                {
                        get { return pos; }
                        set { pos = value; }
                }

                public Coord2D Direction
                {
                        get { return dir; }
                        set { dir = value; }
                }

                public float Speed
                {
                        get { return speed; }
                        set { speed = value; }
                }

                public string State
                {
                        get { return state; }
                        set { state = value; }
                }
        }
}