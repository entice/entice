## Topic `social`

For chat and emote. This topic may possibly become a more general API, if we want to
enable players to chat also trough other interfaces than the official client.

Subtopics set the map and rooms which your are chatting on. Currently you can access these rooms:

* `social:some_map` - The public chatroom of some_map
* `social:some_map:group:12345abc-12ab-34cd-1234ab` - The group chatroom of the group led by the
entity which has the ID given in the last subtopic. You can only join this channel if you are this
groupleader or if you are a member of the group. Note that you currently need to leave this channel
on your own if the group constellation changes!

_Hint: Since these features usually do not affect the gameplay logic, they are mainly
left for the client to use freely. You can even implement your own emotes since the
server wont check them._

---

Synchroneously join.

```
join
```

Success:

```
join:ok
```

Failure: (If no crash: Means that you're not allowed to join this topic)

```
join:error
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
