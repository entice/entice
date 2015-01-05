### Channel-Overview

- `area` - handles all maps and what happens on them entity-wise
- `chat` - handles chat between players, parties, guilds and so on

All channels list the events they can react to and their appropriate parameters below:

#### Channel `area`

_Topics in this channel set the map you're trying to access. When you joined a map, you can only change it with a special mapchange request. This means that the following json will always still require you to set the channel `area` and topic `[fill_in_map_name]` appropriately._

---

```JavaScript
{ ...,
  event: "join",
  message: {}
}
```

Joins an area. The message of this is empty. Request can fail - you'll get ok or error:

```JavaScript
{ ...,
  event: "join:ok",
  message: {
    entity_id: "some_entity_id",
    entities: [{
      id: "some_other_entity_id",
      attributes: [...]
    }, ...]
  }
}

{ ...,
  event: "join:error",
  message: {}
}
```

---
