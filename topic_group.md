## Topic `group`

Groups enable you to team up with other players on the same map,
and change and merge groups and so on. Leaving (or not joining) this
channel will also disable teaming up with you for other players.

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
merge
- target          // the target entity (player, not a group!)
```

```
kick
- target          // the target entity (player, not a group!)
```

---

Asynchroneous server updates.

```
update
- leader          // the group leader (identifies the group!)
- members         // list of member entity ids
- invited         // list of invited entities
```

```
remove
- entity          // the entity that used to be a leader
```

```
mapchange
- leader          // the new entity_id of the group leader
- map             // the map that the leader travels to
```

---
