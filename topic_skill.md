## Topic `skill`

Enables you to use skills and the skillbar and such.

_Note: Skills in the skillbar can have the id 0 to identify
empty skillbar slots._

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
- skillbar        // your current skillbar as list of skill-ids
```

Failure:

```
*socket crash*
```

---

Synchroneous client requests.

Setting a skillbar slot:

```
skillbar:set
- slot            // the slot index of the skillbar (0-based, i.e. has to be between 0-7)
- id              // the id of the skill to be placed there, or 0 for deletion
```

Success:

```
skillbar:ok
- skillbar        // your current skillbar as list of skill-ids
```

Failure: (No permission to change skillbar)

```
skillbar:error
```

Casting a skill:

```
cast
- slot            // the slot index on the skillbar that you want to cast
```

Success:

```
cast:ok
```

Failure: (Already casting a skill)

```
cast:error
```

---

Asynchroneous server events.

```
cast:instantly    // ### Only message that is sent when no cast time ###
- entity          // entity id of the caster
- skill           // the id of the skill that they are casting
```

```
cast:start        // ### Part 1 of skill casting with cast-time ###
- entity          // entity id of the caster
- skill           // the id of the skill that they are casting
- cast_time       // the actual cast-time in milliseconds
```

```
cast:end          // ### Part 2 of skill casting with cast-time ###
- entity          // entity id of the caster
- skill           // the id of the skill that they are casting
```

---
