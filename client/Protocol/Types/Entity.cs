using System;
using System.Collections.Generic;
using System.Runtime.Serialization;
using System.Windows.Forms;
using Protocol.Components;

namespace Protocol.Types
{
        [DataContract]
        public class Entity : Serializable
        {
                private readonly Dictionary<Type, Delegate> _handledComponents = new Dictionary<Type, Delegate>();
                [DataMember] public UUID uuid;


                public Entity()
                {
                }

                public Entity(UUID uuid)
                {
                        this.uuid = uuid;
                }

                public Entity(Entity other)
                {
                        uuid = other.uuid;
                }

                protected void RegisterComponentHandler<T>(Action<T> handler) where T : Component
                {
                        _handledComponents.Add(typeof (T), handler);
                }

                public void Update(Component component)
                {
                        Delegate handler;
                        if (_handledComponents.TryGetValue(component.GetType(), out handler))
                        {
                                handler.DynamicInvoke(component);
                        }
                        else
                        {
                                MessageBox.Show("entity " + uuid + "(" + GetType().Name + ") ignored component update " + component.GetType());
                        }
                }
        }
}