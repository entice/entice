## Topic `skill`

Enables you to use skills and the skillbar and such.

_Note: Skills in the skillbar can have the id 0 to identify
empty skillbar slots._

---

Synchroneously add the skill ability to your player.

```
join
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

Failure: (Already casting a skill, or still recharging)

```
cast:error
- slot            // the index of the skillbar-slot that you tried to use
- reason          // the reason why the cast could not be performed
```

---

Asynchroneous server events.

Skill-Casting & skill-recharging:
Casting can end instantly or after a certain time. In any case you will be notified
that the casting is done and it will include who did cast what. After a cast, the skill
might need to recharge. In this case the recharge time will be more than 0 and you will
also be notified that a recharge has ended after it did so. If the recharge time of a
skill is 0, there will be no notification that the recharge has ended (it never took place).

```
cast:instantly    // -> Start & end of casting process, if cast-time is 0
- entity          // entity id of the caster
- slot            // the index of the skillbar-slot that the player used
- skill           // the id of the skill the player used
- recharge_time   // the actual recharge-time in milliseconds
```

```
cast:start        // -> Start of the casting process, if cast-time is not 0
- entity          // entity id of the caster
- slot            // the index of the skillbar-slot that the player used
- skill           // the id of the skill the player used
- cast_time       // the actual cast-time in milliseconds
```

```
cast:end          // -> End of the casting process, if cast-time is not 0
- entity          // entity id of the caster
- slot            // the index of the skillbar-slot that the player used
- skill           // the id of the skill the player used
- recharge_time   // the actual recharge-time in milliseconds
```

```
recharge:end      // -> End of the recharge process, if recharge-time is not 0
- entity          // entity id of the caster
- slot            // the index of the skillbar-slot that the player used
- skill           // the id of the skill the player used
```

---
