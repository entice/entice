## Routes

Note: Api routes offer a json based interface. You need a session cookie to
access most of the api, which you can aquire by logging in. It always helps to check out the
state of the live instance, since I will try to keep the web UI in sync with the capabilities

```
       page_path  GET   /                  Entice.Web.PageController.index/2
       page_path  GET   /auth              Entice.Web.PageController.auth/2
       page_path  GET   /client/:map       Entice.Web.PageController.client/2
       auth_path  POST  /api/login         Entice.Web.AuthController.login/2
       auth_path  POST  /api/logout        Entice.Web.AuthController.logout/2
       char_path  GET   /api/char          Entice.Web.CharController.list/2
       char_path  POST  /api/char          Entice.Web.CharController.create/2
       docu_path  GET   /api/maps          Entice.Web.DocuController.maps/2
       docu_path  GET   /api/skills        Entice.Web.DocuController.skills/2
       docu_path  GET   /api/skills/:id    Entice.Web.DocuController.skills/2
      token_path  GET   /api/token/entity  Entice.Web.TokenController.entity_token/2
 web_socket_path  GET   /ws                Phoenix.Transports.WebSocket.upgrade/2
 web_socket_path  POST  /ws                Phoenix.Transports.WebSocket.upgrade/2
long_poller_path  GET   /ws/poll           Phoenix.Transports.LongPoller.poll/2
long_poller_path  POST  /ws/poll           Phoenix.Transports.LongPoller.publish/2

```

### Details

#### `/client/:map`

A JavaScript frontend to the server, mainly here for testing purposes for now.

#### `/api/char`

Available skills of this API are represented as a base-16 encoded bit array.

#### `/api/token/entity`

Use this to acquire the key to the websockets. Essentially the server will initialize
your entity and assign it to your account. Later on your can access the different
game-mechanics through the different topics.

```Javascript
{
  "client_id": ...,     // your client's UUID
  "entity_id": ...,     // your entity's UUID
  "entity_token": ...,  // your access token to the websocket based APIs
  "map": ...,           // the underscore map name you'll be able to join
  "is_outpost": ...,    // indicates whether you'll be joining an outpost
}
```
