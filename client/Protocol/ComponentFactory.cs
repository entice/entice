using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using Protocol.Components;

namespace Protocol
{
        public static class ComponentFactory
        {
                private static readonly IEnumerable<Type> _components = Assembly.GetExecutingAssembly().GetTypes().Where(t => t.IsSubclassOf(typeof(Component)));

                public static Component GetComponentByName(string name)
                {
                        var type = _components.FirstOrDefault(e => e.Name.Equals(name));

                        if (type == null) return null;

                        return (Component) Activator.CreateInstance(type);
                }
        }
}
