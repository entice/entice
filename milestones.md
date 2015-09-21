## Milestones

With each milestone, we will add new features in the protocol and implement them in the server and client-mod. The milestone definitions are a rough specification of the features. Details will be added in the source code directly on demand.

Each milestone may modify existing processes and/or create new processes and messages. It can also simply consist of fixes for features that should be implemented in other milestones.

**Definition of done:
A milestone is `DONE` when all messages of the protocol are supported both in the client-mod and server, including all their features. Ideally, we will have automated tests for the messages to prove that the milestone is done.**

Generally, we need to create the following features over the single milestones:

```
* login                [DONE]
* account-c/r/u/d      [70%]
* char-c/r/u/d         [50%]
* entity-propagation   [DONE]
* map-change           [DONE]
* movement             [70%] <- long term WIP
* chat/emotes          [70%]
* npc-spawns           [?]
* npc-dialogues        [?]
* ...
```

### Milestone 1 `DONE`

**Should support**

- account login and proper reply (success or error-code)
- instance load into one single map (propagation of the complete entity system)
- spawn at least one other sample entity (a player)
- movement calculations of entities (simple: no collision, client based)

**Should not support**

- any char actions (selection, creation etc.)
- any account actions (creation, management etc.)
- different entity types (everything is a player)
- character appearance and that (but might support names for entity-system testing purposes)
- movement speed

### Milestone 2 `DONE`

**Should support**

- account login, character selection (plus dispatch) based on a DB backend
- char creation (on the LS, bringing the client back to the char selection afterwards)
- chat (at least general chat and recognition of commands)
- emotes
- char appearance as component (necessary consequence of char creation and selection)
- mod: enhanced debugging and exception handling (visually etc.)

**Should not support**

- account actions such as creation etc. (might come with another 'web interface' prj later on)
- different entity types (everything still is a player, but it also has an appearance)
- any changes on the movement or collision system etc.

### Milestone 3 `DONE`

**Should support**

- different worlds, i.e. the outposts of The Battle Isles, incl. world-map based map-change
- character deletion
- quit playing and going back to char selection
- proper despawning/deletion of entities upon map-change or logout etc. (which is a fix)
- player groups, incl. invite, accept, merge, leave, etc.
_(Hint: Make sure that all groups in a world are known to all clients, either by handling them separately or by having a well designed group component... or both. The server does all the checks (group-size etc.) but the client can and should do its own soft checking when trying to merge groups etc.)_
- a 4 bytes length prefix in the protocol to support very large JSON docs

**Should not support**

- different instances and districts of one outpost
- group search window (who needs that really?)

### The following are skipped milestones, due to rework of the server branch (yet again :D)

> ### Milestone 4 `DONE`
>
> **Should support**
>
> - movement computation and collision detection, **server side**
> - group chat (introduction of channels)
> - a generally more reasonable instance-load/diffing/spawning system
>   - client side: enable spawning of incomplete entities
>   - ~~protocol: communicate when the client is ready to play after instance-load~~
>
> **Should not support**
>
> - any other chat channels than group chat and all chat
>
> ### Milestone 5
>
> **Should support**
>
> - remove PlayReady leftover code
> - animations with variable lengths
> - automatic world unloading if empty
> - server: flush should specify a world to flush (see WorldDiff controller)
> - further refinement of movement
> - NPCs (spawning and scripted AI)
> - Skillbar interactions with mock-up skills
>
> **Should not support**
>
> - skill-functionality, i.e. skill casting

### Milestone 6 `DONE`

**Should support**

- (probably) fix groups. if that works, add group-map-change
- add a place to store available skills and a way to work with them (check if available, lock/unlock etc.)
  - store as bitarray, transfer as base64 string
- let clients manage their current skillbars
- propagate client-side movement updates (add apropriate attributes)

### Milestone 7 `DONE`

**Should fix + support**

- ~~remove leader from groups, use list semantics `[leader|members]` only~~ (changed since group system changed)
- fix group travel, especially with new leader semantics
- find a better way to map between client- and player-entities
- separate movement speed, movement type and goal/pos and add special messages
- ~~make attributes a protocol/macro/behaviour, add :not_propagated / :not_visible~~ (changed semantics of attributes)
- kick open sockets of an account on logging out
- take over client-entity but kick player when logging in again
- avoid form URL encoded API, replace with only JSON API instead

### Milestone 8 `DONE`

**Should support**

- entity-behaviour results - `:become` and `:terminate`
- force-leave all topics when the entity terminates
- persistent skillbars for characters
- explorable zones
- add sample explorable: "great_temple_of_balthazar" <- fake explorable for testing purpose
- add sample explorable: "isle_of_the_nameless"
- no skillbar changes in explorables
- group chat (or rather: general private chat rooms API)
- client side movement message enhancement

### Milestone 9 `DONE`

**Should support**

- entity as a generic state-synced event manager
- entity attribute access as a behaviour
- casting skills from the skillbar, considering cast-time and recharge-time
- the skill distortion (id: 11), also update the unlocked skills

### Milestone 10 (a.k.a. the longest milestone in the world of entice :P)

**Should support**

- replace observ(er/able) & discover behaviour with something more general
- make use of channel processes where feasible (channel inits etc.)
- refactor channels to have a standard join/terminate procedure via helpers
- players have a health and energy (mana) level
- skill casts influence the mana level
- invitation system which enables users to generate access-keys linked to emails
- account creation, based on the email and linked access-key
- basic friendslist implementation based on account-ids, no online status or playing character

### Milestone 11

**May support**

- (improvement) more maps generally
- (improvement) clients should time out after some inactivity
- (improvement) group chat should close connection when leaders / members change
- (improvement) group-mapchange should be implemented more stable, server side
- (improvement) skill casting should disable movement (if not stances etc.)
- (improvement) before skillbar changes, check if the skill is available for the player
- (improvement) segmented message buses in coordination layer (based on entity location? or attributes? etc.)
- (old feature) whisper messages
- (new feature) portals
- (new feature) health regen/degen in its own module
- (new feature) NPC dialogues
- (new feature) items
- (new feature) guilds
- (new feature) more chat channels (guild, trade)
