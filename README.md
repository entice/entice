# Protocol definition

_Always be sure to check the appropriate source files for more details._

### 1. Login process

- Login is requested by the client and either succeeds or fails with a reason

### 2. Dispatch  

- Dispatch is only possible if the client is logged in
- Dispatch is requested by the client and can only succeed  
_(The server will always provide a game server, even if the game server itself is a non functional one)_
- The dispatch response holds an additional key that is used to identify the client - its needed to successfully connect/play to/on a game server
- The game server might refuse the client, but a connect/play fail usually means that there simply is no game server at all
- When the client connects to the game server, the server publishes the complete world state _(Afterwards, only diffs will be send)_

### 3. Game State propagation

**General**  

- Game state might be pushed to the clients in world diffs after a certain time interval 
- A world diff should contain a listing of entities and their components that changed  
_(Note that a world diff is always **recipient specific**. Clients do only get updates to entities that they can actually see. If a new entity enters the viewdistance of the client then its complete state will get pushed to the
client.)_
- A world diff should also contain a listing of entities that have been added or removed

**Concerning the time interval**  

- If critical events occur (i.e. a player changes her walking direction) the diff will be send in a shorter interval - think of it as a flush invoked by a players action
- The diff can only be send after a certain minimum time interval, and must be send after a certain maximum time interval

---

# TODOs / Versions / Milestones

With each milestone, we will add new features in the protocol and implement them in the server and clientmod. The milestone definitions are a rough overview of the features. Details will be added in the source code directly on demand. The milestones only define 

Each milestone may modifiy existing processes and/or create new processes and messages.

**Definition of done:  
A milestone is `DONE` when all messages of the protocol are supported both in the clientmode and server, including all their features. Ideally, we will have automated tests for the messages to prove that the milestone is done.**

Generally, we need to define the following processes over the single milestones:  

```
* login                [WIP]
* dispatch             [WIP]
* instance-load        [WIP]
* char-create          [?]
* gamestate-update     [WIP]
* ...
```

### Milestone 1

**Should support**  

- account login and proper reply (success or errorcode)
- instance load into one single map (propagation of the complete entity system)
- spawn at least one other sample entity (a player)
- movement calculations of entities (simple: no collision, client based)

**Should not support**

- any char actions (selection, creation etc.)
- any account actions (creation, management etc.)
- different entity types (everything is a player)
- character appearance and that (but might support names for entity-system testing purposes)
- movement speed

### Milestone 2

**Should support**

- account login, character selection (plus dispatch) based on a DB backend
- char creation (on the LS, bringing the client back to the char selection afterwards)
- chat (at least general chat and recognition of commands)
- emotes
- char appearance as component (necessary consequence of char creation and selection)
- mod: enhanced debugging and exception handling (visually etc.)

**Should not support**

- account actions such as creation etc. (might come with another 'web interface' prj later on)
- different entity types (everthing still is a player, but it also has an appearance)
- any changes on the movement or collision system etc.