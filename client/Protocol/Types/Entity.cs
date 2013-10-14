using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Windows.Forms;
using Protocol.Components;
using Protocol.Messages;

namespace Protocol.Types
{
        [DataContract]
        public class Entity : Typeable
        {
                private readonly List<Component> _components = new List<Component>();
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

                        foreach (Component component in other._components)
                        {
                                _components.Add(component);
                        }
                }

                protected void RegisterComponentHandler<T>(Action<T> handler) where T : Component
                {
                        _handledComponents.Add(typeof (T), handler);
                }

                private void UpdateComponent(Component component)
                {
                        Delegate handler;
                        if (_handledComponents.TryGetValue(component.GetType(), out handler))
                        {
                                int index = _components.FindIndex(e => e.type.Equals(component.type));
                                if (index >= 0)
                                {
                                        _components[index] = component;
                                }
                                else
                                {
                                        _components.Add(component);
                                }

                                handler.DynamicInvoke(component);
                        }
                        else
                        {
                                MessageBox.Show("entity " + uuid + "(" + GetType().Name + ") ignored component update " + component.GetType());
                        }
                }

                private void AddComponent(Component component)
                {
                        UpdateComponent(component);
                }

                private void RemoveComponent(string componentType)
                {
                        _components.RemoveAll(e => e.type.Equals(componentType));
                }

                protected bool TryGetComponent<T>(out T result) where T : Component
                {
                        result = (T) _components.FirstOrDefault(e => e.GetType() == typeof (T));

                        return result != null;
                }

                // used to strip all additional info from a subtype of Entity to reduce network traffic
                public static Entity Strip(Entity input)
                {
                        return input != null ? new Entity(input.uuid) : null;
                }

                public static List<Entity> Strip(List<Entity> input)
                {
                        return input != null ? input.Select(Strip).ToList() : null;
                }

                public void Update(EntityView entityView)
                {
                        foreach (Component component in entityView.added)
                        {
                                AddComponent(component);
                        }

                        foreach (Component component in entityView.changed)
                        {
                                UpdateComponent(component);
                        }

                        foreach (string componentType in entityView.removed)
                        {
                                RemoveComponent(componentType);
                        }
                }
        }
}