using System;
using System.Collections;
using System.Collections.Generic;
using System.Reflection;
using System.Web.Script.Serialization;

namespace Protocol
{
        public abstract class Serializable
        {
                protected List<T> DeserializeList<T>(string identifier, IDictionary<string, object> dictionary, JavaScriptSerializer serializer)
                {
                        var items = ((ArrayList) dictionary[identifier]);
                        var result = new List<T>();

                        foreach (object item in items)
                        {
                                result.Add(DeserializeSingle<T>((IDictionary<string, object>) item, serializer));
                        }

                        return result;
                }

                protected object DeserializeList(string identifier, IDictionary<string, object> dictionary, JavaScriptSerializer serializer, Type t)
                {
                        var items = ((ArrayList) dictionary[identifier]);
                        var result = (IList) Activator.CreateInstance(t);

                        foreach (object item in items)
                        {
                                if (item.GetType().IsPrimitive || item is string)
                                {
                                        result.Add(item);
                                }
                                else
                                {
                                        result.Add(DeserializeSingle((IDictionary<string, object>)item, serializer));
                                }
                        }

                        return result;
                }

                protected T DeserializeSingle<T>(IDictionary<string, object> dictionary, JavaScriptSerializer serializer)
                {
                        var obT = (Serializable) Activator.CreateInstance(CustomJsonResolver.KNOWN_TYPES[(string) dictionary["type"]]);

                        return (T) obT.Deserialize(dictionary, serializer);
                }

                private object DeserializeSingle(IDictionary<string, object> dictionary, JavaScriptSerializer serializer)
                {
                        var obT = (Serializable) Activator.CreateInstance(CustomJsonResolver.KNOWN_TYPES[(string) dictionary["type"]]);

                        return obT.Deserialize(dictionary, serializer);
                }

                public object Deserialize(IDictionary<string, object> dictionary, JavaScriptSerializer serializer)
                {
                        Type myType = GetType();

                        FieldInfo[] fields = myType.GetFields();

                        foreach (FieldInfo field in fields)
                        {
                                if (!dictionary.ContainsKey(field.Name)) continue;

                                if (field.FieldType.IsPrimitive || field.FieldType == typeof (string))
                                {
                                        field.SetValue(this, DeserializePrimitive(dictionary, field.Name, field.FieldType));
                                }
                                else if (field.FieldType.IsGenericType)
                                {
                                        field.SetValue(this, DeserializeList(field.Name, dictionary, serializer, field.FieldType));
                                }
                                else
                                {
                                        field.SetValue(this, DeserializeSingle((IDictionary<string, object>) dictionary[field.Name], serializer));
                                }
                        }

                        return this;
                }

                private object DeserializePrimitive(IDictionary<string, object> dictionary, string fieldName, Type t)
                {
                        return Convert.ChangeType(dictionary[fieldName], t);
                }
        }
}