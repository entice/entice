## Topic `entity`

By joining this channel, you will be able to detect other entities on the map and their essential attributes.
This is also a mandatory maintenance topic - you can manage your entity here and force it to change maps and
so on, but you will also kick yourself from the server if you leave this channel. (Which is intended in the
case of a map change)

This topic broadcasts changes to the following attributes of entities in the following format:

```
Guaranteed to be present when an entity is reported to have spawned:
- name            // the name of the entity
- position        // the position of the entity (only when adding/spawning!)
 - x
 - y
 - plane
- health          // the health of the entity (in HP)
 - health         // current level of health
 - max_health     // maximum possible level
- energy          // the energy of the entity (in mana)
 - mana           // current level of mana
 - max_mana       // maximum possible level
- level           // the guild-wars character level of the entity

Present if the entity is a player
- appearance      // as it says (the GW stuff)
 - hair_color
 - ...

The Position attribute is only sent when the entity spawns.
This means that changes to that attribute will be broadcasted via another topic,
which is the movement topic.
```

---

Asynchroneous initial message after successful join

```
initial
- attributes      // attributes of the entity, see above
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
- attributes      // attributes of the entity, see above
```

```
change
- entity          // the entity by id
- added           // new attributes of the entity, see above
- changed         // updated attributes of the entity, see above
- removed         // removed attributes of the entity - this will be
                  // a list with [name, position, health, ...]
```

```
remove
- entity          // the entity by id
```

---
