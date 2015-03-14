[![to.entice.so](http://to.entice.so/images/entice.png)](http://to.entice.so)


* [Client](https://github.com/entice/client)
* [Web](https://github.com/entice/web) master: ![Travis-CI](https://travis-ci.org/entice/web.svg?branch=master)
* [Logic](https://github.com/entice/logic) master: ![Travis-CI](https://travis-ci.org/entice/logic.svg?branch=master)
* [Entity](https://github.com/entice/entity) master: ![Travis-CI](https://travis-ci.org/entice/entity.svg?branch=master)
* [Skill](https://github.com/entice/skill) master: ![Travis-CI](https://travis-ci.org/entice/skill.svg?branch=master)
* [Utils](https://github.com/entice/utils) master: ![Travis-CI](https://travis-ci.org/entice/utils.svg?branch=master)


## Project


This aims to create a game-server & client-modification for a popular MMO (can you guess which one? :P).
You can find more information on how it works in the subprojects of entice. If you're interested in using the API, you
can find the complete documentation here in this repository.

* [Milestones](https://github.com/entice/entice/blob/master/milestones.md) - These define the features we implemented and are working on
* [Routes](https://github.com/entice/entice/blob/master/routes.md) - The public & secured HTTP API for the server
* [Topics](https://github.com/entice/entice/blob/master/topics.md) - The secured Websocket API for the server as an overview. This is the place to start when you are looking
for how things of this server work internally (API wise).


## Overview


The client is a middle-man between the server and its API and the actual game-client. It modifies the latter ones
behaviour and encapsulates it in a way that all the game-client 'sees' of the world is this modified environment.
Every network communication is intercepted and reacted upon in this layer. This provides the benefit for the server
to use an entirely different protocol, and for the client to crash less often since yet unimplemented behaviour
of the server can be simulated in this layer.

The web project provides an HTTP/Websocket access point for any API consumer (mainly the client layer) and communicates
both with the database and the entity system. This provides every player with her own view of the ingame world.

The logic project aims to provide a boilerplate-less code base for the actual gameplay components that the web
project provides access to and the client uses. This is a consumer of the bare entity-framework that lies underneath.

The entity project provides a rather universial implementation of a distributed entity-component-system, which comes in
the shape of so called entities, attributes and behaviours. Attributes and behaviours are in contrast to components and
systems process-local copies of their counterparts. This design works especially well in a highly parallel environment.

The skill project provides implementation for the wide range of in-game skills the players can use. (This might
become part of the logic project in the future)

The utils project provides, as the name suggests, utility functionality for the server. Very universial and
unspecific components that are used in the other server projects reside here.


## Collaboration


Fork any entice repo, change whatever you would like to and create a pull-request. Any help is much appreciated,
and if you want to get in touch with us, we're idling on `irc.rizon.net #gwlpr`.


## License


The WTFPL. Also please check the LICENSE documents that come with the different repositories for other licenses.

```

       DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
                   Version 2, December 2004

Copyright (C) 2004 Sam Hocevar <sam@hocevar.net>

Everyone is permitted to copy and distribute verbatim or modified
copies of this license document, and changing it is allowed as long
as the name is changed.

           DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
  TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION

 0. You just DO WHAT THE FUCK YOU WANT TO.


```
