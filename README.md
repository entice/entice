# Protocol definition

_Check me for naming issues._

---

### Game State propagation

For this we will use an update packet structured similar to the example below.
There will be two kinds of updates:

* **ClientUpdate**: Client's world diff (concerning the entities it can see, and itself).
* **ServerUpdate**: Server's world diff plus timediff (concerning the entities the client can see).

The client can only send requests, because the server is not required to take them into concern.
Every server update will be a must-change. The server updates contain a diff of what components changed over time.


More or less generic structure:
```Java
class Update {
  Diff[] diffs;
}

class Diff {
  Entity e;
  Component[] comps;
}
```
