using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.Reflection;
using System.Runtime.Serialization.Json;
using System.Threading;

namespace Protocol
{
        public class Deserializer
        {
                private readonly ConcurrentDictionary<Type, KeyValuePair<DateTime, TypedMessage>> _lastMessages = new ConcurrentDictionary<Type, KeyValuePair<DateTime, TypedMessage>>();
                private readonly Dictionary<Type, Delegate> _messageHandlers;
                private readonly Dictionary<string, Type> _typedMessages;

                public Deserializer()
                {
                        _typedMessages = new Dictionary<string, Type>();
                        _messageHandlers = new Dictionary<Type, Delegate>();

                        IEnumerable<Type> typedMessagesTypes = Assembly.GetExecutingAssembly().GetTypes().Where(t => t.IsSubclassOf(typeof (TypedMessage)));

                        foreach (Type type in typedMessagesTypes)
                        {
                                _typedMessages.Add(type.Name, type);
                        }
                }

                public int WaitForMessage<T1, T2>(int timeout, out T1 messagePossibility1, out T2 messagePossibility2)
                        where T1 : TypedMessage
                        where T2 : TypedMessage
                {
                        DateTime timeStamp = DateTime.Now;

                        messagePossibility1 = null;
                        messagePossibility2 = null;

                        while (DateTime.Now.Subtract(timeStamp).TotalMilliseconds < timeout)
                        {
                                if (MessageAvailable(timeStamp, out messagePossibility1)) return 1;
                                if (MessageAvailable(timeStamp, out messagePossibility2)) return 2;

                                Thread.Sleep(1);
                        }

                        return 0;
                }

                public bool WaitForMessage<T>(int timeout, out T message) where T : TypedMessage
                {
                        DateTime timeStamp = DateTime.Now;

                        message = null;

                        while (DateTime.Now.Subtract(timeStamp).TotalMilliseconds < timeout)
                        {
                                if (MessageAvailable(timeStamp, out message)) return true;

                                Thread.Sleep(1);
                        }

                        return false;
                }

                private bool MessageAvailable<T>(DateTime timeStamp, out T message) where T : TypedMessage
                {
                        KeyValuePair<DateTime, TypedMessage> lastTMessage;
                        if (_lastMessages.TryGetValue(typeof (T), out lastTMessage))
                        {
                                if (lastTMessage.Key > timeStamp)
                                {
                                        message = (T) lastTMessage.Value;
                                        return true;
                                }
                        }

                        message = null;
                        return false;
                }

                public void RegisterMessageHandler<T>(Action<T, Socket> handler) where T : TypedMessage
                {
                        _messageHandlers.Add(typeof (T), handler);
                }

                public void Deserialize(byte[] data, Socket socket = null)
                {
                        var stream = new MemoryStream(data) {Position = 2}; // throw away first 2 bytes (protocol)

                        TypedMessage typedMessage = DeserializeMessage(typeof (TypedMessage), stream);
                        stream.Position = 2; // reset to right after the first 2 bytes

                        Type type;
                        if (!_typedMessages.TryGetValue(typedMessage.Type, out type))
                        {
                                throw new ArgumentException("unknown message type: " + typedMessage.Type);
                        }

                        TypedMessage message = DeserializeMessage(type, stream);


                        Delegate handler;
                        if (_messageHandlers.TryGetValue(type, out handler))
                        {
                                handler.DynamicInvoke(message, socket);
                        }
                }

                public TypedMessage DeserializeMessage(Type messageType, Stream stream)
                {
                        var serializer = new DataContractJsonSerializer(messageType);

                        var message = (TypedMessage) serializer.ReadObject(stream);

                        var newValue = new KeyValuePair<DateTime, TypedMessage>(DateTime.Now, message);
                        _lastMessages.AddOrUpdate(messageType, newValue, (k, v) => newValue);

                        return message;
                }
        }
}