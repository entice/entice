## Topic `movement`

By joining this topic, you can move your entity and also observe others as they move theirs.

_Hint: Movement is currently completely client based. This is very beneficial for the server in terms
of workload and code complexity, but may change in the future. Also, the server aims to at least do sanity
checks on the client updates. Since the client updates are async, you will only notice the real change
when the server propagates it back to you._

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
