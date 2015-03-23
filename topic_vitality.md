## Topic `vitality`

By joining this topic, you provide your entity with basic vital stats and energy.

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
```

Failure:

```
*socket crash*
```

---

Asynchroneous server updates.

```
update:health
- entity          // event sender
- health          // the health of the sender in points
```

```
update:energy
- entity          // event sender
- energy          // the energy of the sender in points
```

---
