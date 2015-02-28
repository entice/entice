## Topic `social`

For chat and emote. This topic may possibly become a more general API, if we want to
enable players to chat also trough other interfaces than the official client.

_Hint: Since these features usually do not affect the gameplay logic, they are mainly
left for the client to use freely. You can even implement your own emotes since the
server wont check them._

---

Synchroneously join.

```
join
- client_id       // the id of your client, from API
- entity_token    // a temporary token for authentication
```

Success:

```
join:ok
```

Failure:

```
*socket crash*
```

---

Asynchroneous chat message event. (Topic-wide broadcast)
Note that you will receive the Server -> Client broadcast yourself as well,
after successfully sending a chat message.

Client -> Server:

```
message
- text            // the message text
```

Server -> Client:

```
message
- text            // the message text
- sender          // the sender's char name
```

---

Asynchroneous emote event. (Topic-wide broadcast)
Note that you will receive the Server -> Client broadcast yourself as well,
after successfully sending an emote.

Client -> Server:

```
emote
- action          // the actual emote action
```

Server -> Client:

```
emote
- action          // the actual emote action
- sender          // the sender's char name
```

---
