﻿using System.Collections.Generic;
using System.Runtime.Serialization;
using Protocol.Views;

namespace Protocol.Messages.StartPlaying
{
        public class PlaySuccess : Message
        {
                [DataMember] public List<EntityView> world;
        }
}