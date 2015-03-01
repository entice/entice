## Topic `entity`

By joining this channel, you will be able to detect other entities on the map and their essential attributes.
This is also a mandatory maintenance topic - you can manage your entity here and force it to change maps and
so on, but you will also kick yourself from the server if you leave this channel. (Which is intended in the
case of a map change)


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
- name            // the name of the player
- position        // the position of the player
 - x
 - y
- appearance      // as it says (the GW stuff)
 - hair_color
 - ...
```

Failure:

```
*socket crash*
```

---

Synchroneously change the map.

```
map:change
- map             // the underscore name of the map
```

Success: (Since this topic kills your entity upon leave, you will have to do so on your own)

```
map:change:ok
- map             // the underscore name of the map
```

Failure:

```
*socket crash*
```

---

Asynchroneous entity events. (Topic-wide broadcast)

```
add
- entity          // the entity by id
- name            // the name of the player
- position        // the position of the player
 - x
 - y
- appearance      // as it says (the GW stuff)
 - hair_color
 - ...
```

```
remove
- entity          // the entity by id
```

---
