## Routes

Note: Api routes offer a json based interface. You need a session cookie to
access most of the api, which you can aquire by logging in. It always helps to check out the
state of the live instance, since I will try to keep the web UI in sync with the capabilities

```
   page_path  GET     /                          Entice.Web.PageController :index
   page_path  GET     /auth                      Entice.Web.PageController :auth
   page_path  GET     /client/:map               Entice.Web.PageController :client
   page_path  GET     /register                  Entice.Web.PageController :account
   page_path  GET     /invitation                Entice.Web.PageController :invitation
   page_path  GET     /friend                    Entice.Web.PageController :friend
   auth_path  POST    /api/login                 Entice.Web.AuthController :login
   auth_path  POST    /api/logout                Entice.Web.AuthController :logout
   char_path  GET     /api/char                  Entice.Web.CharController :list
   char_path  POST    /api/char                  Entice.Web.CharController :create
   docu_path  GET     /api/maps                  Entice.Web.DocuController :maps
   docu_path  GET     /api/skills                Entice.Web.DocuController :skills
   docu_path  GET     /api/skills/:id            Entice.Web.DocuController :skills
  token_path  GET     /api/token/entity          Entice.Web.TokenController :entity_token
account_path  GET     /api/account/by_char_name  Entice.Web.AccountController :by_char_name
account_path  POST    /api/account/register      Entice.Web.AccountController :register
account_path  POST    /api/account/request       Entice.Web.AccountController :request_invite
friends_path  GET     /api/friend                Entice.Web.FriendsController :index
friends_path  POST    /api/friend                Entice.Web.FriendsController :create
friends_path  DELETE  /api/friend                Entice.Web.FriendsController :delete

Websockets should connect to /socket/websocket
```

### Details

#### `/client/:map`

A JavaScript frontend to the server, mainly here for testing purposes for now.

#### `/api/char`

Available skills of this API are represented as a base-16 encoded bit array.

Chars can be create with a POST to this route.
You can define the name of the new char (which needs to be unique) and the appearance, with the parameters:

```
name
profession     // only used to determine how a character looks
campaign
sex
height
skin_color
hair_color
hairstyle
face
```

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

#### `/api/friend`

Use this to get a listing of all friends on the server. Friends are identified by
the name of the character that you added them to your friendslist with. They have
an online status, and depending on that a character that they are currently playing with.

{
  "friends": [
    {"base_name": "Char Name", "current_name": "Other Name", "status": "online"},
    {...}
  ]
}

- `base_name` is the name of the character that they were added to the friendslist with
- `current_name` is the name of the char that they are currently online with, or the same as `base_name`
- `status` is either `online` or `offline`
