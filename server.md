#### The server-side implementation of the protocol has been moved to the server repository for ease of use

Please visit [https://github.com/entice/server](https://github.com/entice/server) and look for the protocol directory there.

As for Milestone-5, the protocol used for the client/server communication is a mixture of simple HTTP with POST Forms and a JSON Api as a REST service and over websockets.  

- The HTTP controllers can be found here: [https://github.com/entice/server/tree/milestone5/app/controllers](https://github.com/entice/server/tree/milestone5/app/controllers)
- The JSON (via plain HTTP or Websocket) protocol consists of:
  - Coord2D: [https://github.com/entice/server/blob/milestone5/app/entice/server/implementation/utils/Coor2D.scala](https://github.com/entice/server/blob/milestone5/app/entice/server/implementation/utils/Coor2D.scala)
  - Enums: [https://github.com/entice/server/blob/milestone5/app/entice/server/Enums.scala](https://github.com/entice/server/blob/milestone5/app/entice/server/Enums.scala)
  - Entities are represented by an integer ID only, e.g.: `{ id: 321 }`
  - Attributes: [https://github.com/entice/server/blob/milestone5/app/entice/server/implementation/attributes/Attributes.scala](https://github.com/entice/server/blob/milestone5/app/entice/server/implementation/attributes/Attributes.scala)
  - World Events: [https://github.com/entice/server/blob/milestone5/app/entice/server/implementation/events/WorldEvents.scala](https://github.com/entice/server/blob/milestone5/app/entice/server/implementation/events/WorldEvents.scala)
