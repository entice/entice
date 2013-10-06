using System.Collections.Generic;
using Protocol.Components;

namespace Protocol.Views
{
        public class View : Typeable
        {
                public virtual List<Component> GetComponents()
                {
                        return null;
                }
        }
}