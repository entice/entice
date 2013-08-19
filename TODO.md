# TODOs  

With each milestone, we will add new features in the protocol and implement them in the server and clientmod. The milestone definitions are a rough overview of the features. Details will be added in the source code directly on demand. The milestones only define 

Each milestone may modifiy existing processes and/or create new processes and messages.

**A milestone is `DONE` when all messages of the protocol are supported both in the clientmode and server, including all their features. Ideally, we will have automated tests for the messages to prove that the milestone is done.**

Generally, we need to define the following processes, and add their protobuf stuff:  

```
* login
* dispatch
* instance-load
* char-create
* gamestate-update
```

### Milestone 1

**Should support**  

* account login and proper reply (success or errorcode)
* instance load into one single map
* one other sample entity (a player)
* movement calculations of entities

**Should not support**

* any char actions (selection, creation etc.)
* any account actions (creation, management etc.)
* different entity types (everything is a player)
* character appearance, names or whatever



