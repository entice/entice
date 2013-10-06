using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Web.Script.Serialization;

namespace Protocol
{
        public class CustomJsonResolver : JavaScriptConverter
        {
                public static readonly Dictionary<string, Type> KNOWN_TYPES = Assembly.GetExecutingAssembly().GetTypes().Where(t => t.IsSubclassOf(typeof (Serializable))).ToDictionary(t => t.Name, t => t);

                public override IEnumerable<Type> SupportedTypes
                {
                        get { return KNOWN_TYPES.Values; }
                }

                public override object Deserialize(IDictionary<string, object> dictionary, Type type, JavaScriptSerializer serializer)
                {
                        Type typed;
                        if (KNOWN_TYPES.TryGetValue((string) dictionary["type"], out typed))
                        {
                                var serializableClass = (Serializable) Activator.CreateInstance(typed);

                                return serializableClass.Deserialize(dictionary, serializer);
                        }

                        throw new ArgumentException();
                }

                public override IDictionary<string, object> Serialize(object obj, JavaScriptSerializer serializer)
                {
                        throw new NotSupportedException();
                }
        }
}