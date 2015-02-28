## Topics

Semantically, topics add an ability to your player. By joining a topic you can access the topic's event based API
which is defined in the topics documentation page. You also enable yourself to receive the events emitted by the server
on the topic that you joined. In some cases it might become necessary for us to enforce that players join certain topics
to be able to play. In that case the process of joining a map (and all required topics) will be documented here.

To join a topic, you will need an access token, usually called entity_token if joining the game to play.
You can get your token by using the secured token API: `/api/token/entity?map=[...]&char_name=[...]`
This will also provide you with the ID of your new entity.

General syntax for topics is: `topic:subtopic` e.g. `entity:heroes_ascent` and usually (unless stated otherwise) the subtopics
define the map you're on and you wont be able to access subtopics that are meant for a different map.

These are the currently available topics:

- `entity` - handles access to the other entity focused functionality
- `group` - handles the groups and their interactions (merging, kicking, ...)
- `movement` - enables the player to move and also get notified of other entities position changes
- `skill` - enables the player to use skills and the skill bar
- `social` - handles chat & emotes between players, parties, guilds and so on

### Communication

The messages that we can handle have the structure that is given exemplarily below.
The following sections describe what events the API can understand and for each event what content is in the payload.

```Javascript
{
  "topic": "some_topic:some_sub_topic",
  "event": "player:create",
  "payload": {
    // arbitrary json content, see the topic's documenation page
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
