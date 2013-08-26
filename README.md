# Protocol definition

### 1. Login process

Login might be implemented in a service kind of way, meaning the client **requests** a login, and the 
server **responds** with a reply containing either general account information and characters etc.,
or and invalid accountUID and an additional errorcode.

### 2. Game State propagation

**Game state might be pushed to the clients in world diffs after a certain time interval.**  

A world diff should contain a listing of entities and their components that changed. Note that
a world diff is always **recipient specific**. Clients do only get updates to entities that they can actually
see. If a new entity enters the viewdistance of the client then its complete state will get pushed to the
client.  

This means that we need to keep track of the changes that happened in an entities viewing distance (which can be
done by checking for any changes of any entities and pushing only the changes a certain client can see). Plus
we need to keep track of the changes of each entities's view. When we push the world diff to the clients,
we then need to include both of these aspects.  
When an entity enters the viewing distance of a client, then the entity basically gets 'spawned' for the client.
We can either have a special spawning and despawning packet, or the clientmod needs to keep track of the changes
happening to the View component of the entity. (Meaning we can either do it explicitly or implicitly)  

The client can request state changes of the entity system as well, meaning it can push its own world diff
containing any number of state changes of entities it can influence.  

We can either send general purpose updates, having an entity-to-component association with all components that
changed for a particular entity, or we can send definite changes, affecting only special components
of some entities. (E.g. when we trigger a skill and only affect certain components of multiple entities
in a special way)

