## Topic `group`

Groups enable you to team up with other players on the same map,
and change and merge groups and so on. Leaving (or not joining) this
channel will also disable teaming up with you for other players.

_Note: When a group changes a map, the leader can inform the members
by using the `entity` topic mapchange API. The members will then get
notified of where the leader wants to travel and they must then travel
there as well and let the leader re-invite them. (The client should
figure this out and negotiate on its own using the existing APIs)_

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
map:change
- map             // the map that the leader travels to
```

---
