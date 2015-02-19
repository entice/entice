### Routes-Overview

Note: Api routes offer a form-urlencoded / json based interface. You need a session cookie to
access most of the api, which you can aquire by logging in. It always helps to check out the
state of the live instance, since I will try to keep the web UI in sync with the capabilities

```
       page_path  GET   /                  Entice.Web.PageController.index/2
       page_path  GET   /auth              Entice.Web.PageController.auth/2
       page_path  GET   /client/:area      Entice.Web.PageController.client/2
       auth_path  POST  /api/login         Entice.Web.AuthController.login/2
       auth_path  POST  /api/logout        Entice.Web.AuthController.logout/2
       char_path  GET   /api/char          Entice.Web.CharController.list/2
       char_path  POST  /api/char          Entice.Web.CharController.create/2
       docu_path  GET   /api/maps          Entice.Web.DocuController.maps/2
       docu_path  GET   /api/skills        Entice.Web.DocuController.skills/2
       docu_path  GET   /api/skills/:id    Entice.Web.DocuController.skills/2
      token_path  GET   /api/token/entity  Entice.Web.TokenController.entity_token/2
 web_socket_path  GET   /ws                Phoenix.Transports.WebSocket.upgrade/2
 web_socket_path  POST  /ws                Phoenix.Transports.WebSocket.upgrade/2
long_poller_path  GET   /ws/poll           Phoenix.Transports.LongPoller.poll/2
long_poller_path  POST  /ws/poll           Phoenix.Transports.LongPoller.publish/2

```

### Topic-Overview

To join a topic, you will need an access token, usually called entity_token if joining the game to play.
You can get your token by using the token API: `/api/token/entity?map=[...]&char_name=[...]`

General syntax for topics is: `topic:subtopic` e.g. `entity:heroes_ascent`

- `entity` - handles access to the other entity focused functionality
- `group` - handles the groups and their interactions (merging, kicking, ...)
- `movement` - enables the player to move and also get notified of other entities position changes
- `skill` - enables the player to use skills and the skill bar
- `social` - handles chat & emotes between players, parties, guilds and so on

The messages that we can handle have the structure that is given exemplarily below.
The following sections describe what events the API can understand and for each event what content is in the payload.

```Javascript
{
  "topic": "some_topic:some_sub_topic",
  "event": "player:create",
  "payload": {
    // arbitrary json content, see below
  }
}
```

You can leave a channel (which doesn't terminate your websocket connection) by sending:

```Javascript
{
  "topic": "some_topic:some_sub_topic",
  "event": "leave",
  "payload": {}
}
```

Since you will risk getting kicked if you're not sending data for a longer period of time, you should send heartbeats (exact copies of this):

```Javascript
{
  "topic": "phoenix",
  "event": "heartbeat",
  "payload": {}
}
```

#### Topic `entity`

Subtopics set the map you're trying to access. When you joined a map, you can only change it with a special mapchange request.

---

Synchroneously join an area.
Note: The entity-list's shape may change in the future.

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

Asynchroneous entity events. (Topic-wide broadcast)
(Server -> Client only)

```
add
- entity          // the entity by id
- attributes      // the entities attributes (need not be complete, sometimes you need to join other topics)
```

```
remove
- entity          // the entity by id
```

---


#### Topic `group`

Subtopics set the map you're on. (Future extensions might build on group-specific subtopics)

Token API: You receive the `access_token` through the entity channel.

---

Synchroneously add the group ability to your player.

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

Asynchroneous client requests.

```
merge
- target          // the target entity (player, not a group!)
```

```
kick
- target          // the target entity (player, not a group!)
```

---

Asynchroneous server updates.

```
change
- leader          // the group leader (identifies the group!)
- members         // list of member entity ids
- invited         // list of invited entities
```

---


#### Topic `movement`

Subtopics set the map you're on.

---

Synchroneously add the movement ability to your player.

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

Asynchroneous client requests.

```
update:pos
- pos             // the new position
```

```
update:goal
- goal            // the new goal
- plane           // the new plane
```

```
update:movetype
- movetype        // the new movement type (0-10)
```

```
update:speed
- speed           // the new speed (-1-2)
```

---

Asynchroneous server updates.

```
update:pos
- entity          // event sender
- pos             // the new position
```

```
update:goal
- entity          // event sender
- goal            // the new goal
- plane           // the new plane
```

```
update:movetype
- entity          // event sender
- movetype        // the new movement type (0-10)
```

```
update:speed
- entity          // event sender
- speed           // the new speed (-1-2)
```

---


#### Topic `skill`

Subtopics set the map you're on.

---

Synchroneously add the skill ability to your player.

```
join
- client_id       // the id of your client, from API
- entity_token    // a temporary token for authentication
```

Success:

```
join:ok
- unlocked_skills // overall available skills
- skillbar        // your character's current skillbar
```

Failure:

```
*socket crash*
```

---

Synchroneous client requests.

```
skillbar:set
- slot            // the slot of the skillbar (1-9)
- id              // the id of the skill to be placed there, or 0 for deletion
```

Answers:

```
skillbar:ok
```

```
skillbar:error
```

---


#### Topic `social`

Subtopics set the map you're on.

---

Synchroneously join a room.

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
- text            // the message text, lol
```

Server -> Client:

```
message
- text            // the message text, lol
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
