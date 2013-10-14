using System;
using System.Runtime.Serialization;

namespace Protocol.Components
{
        public class Animation : Component
        {
                [DataMember] public string id;

                public bool GetNativeValue(out CharacterAnimations nativeAnimation)
                {
                        return Enum.TryParse(id, true, out nativeAnimation);
                }
        }
}