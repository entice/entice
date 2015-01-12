### Routes-Overview

Note: Api routes offer a form-urlencoded / json based interface. You need a session cookie to
access most of the api, which you can aquire by logging in. It always helps to check out the
state of the live instance, since I will try to keep the web UI in sync with the capabilities

```
       page_path  GET   /                  Entice.Web.PageController.index/2
       page_path  GET   /auth              Entice.Web.PageController.auth/2
       page_path  GET   /client/:area      Entice.Web.PageController.client/2
       page_path  GET   /chat/:chat        Entice.Web.PageController.chat/2
       auth_path  POST  /api/login         Entice.Web.AuthController.login/2
       auth_path  POST  /api/logout        Entice.Web.AuthController.logout/2
       char_path  GET   /api/char          Entice.Web.CharController.list/2
       char_path  POST  /api/char          Entice.Web.CharController.create/2
      token_path  GET   /api/token/area    Entice.Web.TokenController.area_transfer_token/2
      token_path  GET   /api/token/social  Entice.Web.TokenController.social_transfer_token/2
 web_socket_path  GET   /ws                Phoenix.Transports.WebSocket.upgrade/2
 web_socket_path  POST  /ws                Phoenix.Transports.WebSocket.upgrade/2
long_poller_path  GET   /ws/poll           Phoenix.Transports.LongPoller.poll/2
long_poller_path  POST  /ws/poll           Phoenix.Transports.LongPoller.publish/2

```

### Topic-Overview

General syntax for topics is: `topic:subtopic` e.g. `area:heroes_ascent`

- `area` - handles all maps and what happens on them entity-wise
- `social` - handles chat & emotes between players, parties, guilds and so on

The messages that we can handle have the structure that is given exemplarily below.
The following sections describe what events the API can understand and for each event what content is in the payload.

```Javascript
{
  "topic": "some_topic:some_sub_topic"
  "event": "player:create"
  "payload": {
    // arbitrary json content, see below
  }
}
```

#### Topic `area`

Subtopics set the map you're trying to access. When you joined a map, you can only change it with a special mapchange request.

Token API: `/api/token/area?map=[...]&char_name=[...]`

---

Synchroneously join an area.
Note: The entity-list's shape may change in the future.

```
join
- client_id       // the id of your client, from API
- transfer_token  // a temporary token for authentication
```

Success:

```
join:ok
- entity          // your new entity by id
- entities        // a list of all available entities
  - id            // the entity id
  - attributes    // list of the entities attributes
```

Failure:

```
join:error
```

---

Asynchroneous entity events. (Topic-wide broadcast)
(Server -> Client only)

```
entity:add
- entity          // the entity by id
```

```
entity:remove
- entity          // the entity by id
```

```
entity:attribute:update
- entity          // the entity by id
- attribute       // the whole attribute struct
```

```
entity:attribute:remove
- entity          // the entity by id
- attribute_type  // the attribute's type (name)
```

---

Synchroneous map change request.

```
map:change
- new_map         // the new map's snake-cased name
```

Success: (Decouples the client from the former channel,
you will need to rejoin the new map with the token)

```
map:change:ok
- client_id       // the client's id (should be known anyway)
- transfer_token  // the temporary token
```

Failure: (You'll get decoupled from the current map.)

```
map:change:error
```

---

Asynchroneous client requests.

```
entity:move
- pos             // the new position
  - x
  - y
```

```
group:merge
- target          // the target entity (player, not a group!)
```

```
group:kick
- target          // the target entity (player, not a group!)
```

---


#### Topic `social`

Subtopics set the room you're trying to access.

Token API: `/api/token/social?room=[...]&char_name=[...]`

---

Synchroneously join a room.

```
join
- client_id       // the id of your client, from API
- transfer_token  // a temporary token for authentication
```

Success:

```
join:ok
```

Failure:

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
