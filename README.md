# Protocol definition

_Always be sure to check the appropriate source files for more details._  
_Hint: We use the same server for login, char selection and playing._

### 1. Login process

- Login is requested by the client for a certain email and password and either succeeds or fails with a reason
- Upon login, the client will receive all its associated characters

### 2. Character creation

- Char creation is requested by the client (it must be in the 'lobby' state)
- Either succeeds with the creation of a new entity, or fails with a reason
- Fails under certain conditions:
  - Client is not in the right state (e.g. not logged in, or currently playing)
  - The character's new attributes are invalid
  - The character name is inappropriate or already taken

### 3. Game instance load

- Instance load is requested by the client for a certain entity (it must be in the 'lobby' state)
- Either succeeds with special instance load data, or fails with a reason
- Fails under certain conditions:
  - Client is not in the right state (e.g. not logged in)
  - The chosen entity does not belong to the client (results in a kick)

#### 3.1 Map change

- Clients can request to change their current map (using the world-map)
- Either succeeds with special instance load data, or fails with a reason
- Conditions under which it fails might be:
  - Client is not in the right state (e.g. not logged in, not playing)
  - Map is inaccesible to the client
  - Client already is on the map

### 4. General game events

- ChatMessage: Bidirectional. Has an entity as sender and should be ignored by the client if the entity is unknown
- ServerMessage: From server only. Notification for anything that concerns the server (e.g. broadcasts etc.)
- ChatCommand: From client only. Has a no-spaces string as command and several args. Commands are server specific (scripted) and can documentation can be found in-game by entering "/helpme" or "/info [command]"

### 5. Game state propagation

**General**  

- Game state must be pushed to the clients in world diffs after a certain time interval 
- A world diff should contain a listing of entities and their components that changed  
_(Note that a world diff is always **recipient specific**. Clients do only get updates to entities that they can actually see. If a new entity enters the view-distance/relevant-set of the client then its complete state will get pushed to the client.)_
- A world diff should also contain a listing of entities that have been added or removed

**Concerning the time interval**  

- If critical events occur (i.e. a player changes her walking direction) the diff will be send in a shorter interval - think of it as a flush invoked by a players action
- The diff can only be send after a certain minimum time interval, and must be send after a certain maximum time interval

---

# TODOs / Versions / Milestones

With each milestone, we will add new features in the protocol and implement them in the server and client-mod. The milestone definitions are a rough overview of the features. Details will be added in the source code directly on demand. The milestones only define 

Each milestone may modify existing processes and/or create new processes and messages.

**Definition of done:  
A milestone is `DONE` when all messages of the protocol are supported both in the client-mod and server, including all their features. Ideally, we will have automated tests for the messages to prove that the milestone is done.**

Generally, we need to create the following features over the single milestones:  

```
* login                [DONE]
* account-create       [?]
* account-delete       [?]
* instance-load        [70%]
* map-change           [0%]
* char-create          [DONE]
* char-delete          [0%]
* game-state-diff      [70%]
* movement             [50%]
* chat                 [50%]
* emotes               [DONE] (scripted)
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

### Milestone 4

**Should support**

- movement computation and collision detection, **server side**
- group chat (introduction of channels)
- a generally more reasonable instanceload/diffing/spawning system, _details yet to come_

**Should not support**

- any other chat channels than group chat and all chat

### Milestone 5

**May support:**

- (improvement) more maps generally
- (old feature) explorable zones
- (old feature) NPCs
- (old feature) movement speed
- (new feature) portals
- (new feature) NPC dialogues