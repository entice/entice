using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Runtime.Serialization;
using System.Windows.Forms;
using Protocol.Components;
using Protocol.Messages;

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

                public void Update(IncomingComponent incomingComponent)
                {
                        Component component = ComponentFactory.Instance.CreateComponent(incomingComponent);

                        if (component == null)
                        {
                                MessageBox.Show("unknown incoming IncomingComponent type: " + incomingComponent.Type);
                                return;
                        }

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

                private class ComponentFactory
                {
                        public static readonly ComponentFactory Instance = new ComponentFactory();

                        private readonly Dictionary<string, Type> _componentTypesMapping;

                        private ComponentFactory()
                        {
                                _componentTypesMapping = new Dictionary<string, Type>();

                                IEnumerable<Type> typedMessagesTypes = Assembly.GetExecutingAssembly().GetTypes().Where(t => t.IsSubclassOf(typeof (Component)));

                                foreach (Type type in typedMessagesTypes)
                                {
                                        _componentTypesMapping.Add(type.Name, type);
                                }
                        }

                        public Component CreateComponent(IncomingComponent incomingIncomingComponent)
                        {
                                Type type;
                                if (!_componentTypesMapping.TryGetValue(incomingIncomingComponent.Type, out type)) return null;

                                return (Component) Activator.CreateInstance(type, incomingIncomingComponent);
                        }
                }
        }
}