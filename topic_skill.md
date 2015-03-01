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

```
skillbar:set
- slot            // the slot index of the skillbar (1-based, i.e. has to be between 1-8)
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

---
