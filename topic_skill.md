## Topic `skill`

Enables you to use skills and the skillbar and such.

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
- skillbar        // your current skillbar (slot to skill-id association)
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

Success:

```
skillbar:ok
- skillbar        // your current skillbar (slot to skill-id association)
```

Failure:

```
*socket crash*
```

---
