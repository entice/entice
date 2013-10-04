using System;
using System.Collections.Generic;
using System.Runtime.Serialization;
using System.Windows.Forms;
using Protocol.Components;
using Protocol.Views;

namespace Protocol.Types
{
        [DataContract]
        public class Entity
        {
                private readonly Dictionary<Type, Delegate> _handledComponents;
                [DataMember] private UUID uuid;

                public Entity(UUID uuid)
                {
                        UUID = uuid;

                        _handledComponents = new Dictionary<Type, Delegate>();
                }

                public UUID UUID
                {
                        get { return uuid; }
                        private set { uuid = value; }
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
                                MessageBox.Show("entity " + UUID + "(" + GetType().Name + ") ignored component update " + component.GetType());
                        }
                }
        }
}