## FAQ & Known Bugs

### Running the server locally

*Weird error while trying to connect to the server, it says the method is unknown:*

```
[error] Process #PID<0.313.0> raised an exception
** (FunctionClauseError) no function clause matching in :cowboy_protocol.parse_method/3
    (cowboy) src/cowboy_protocol.erl:168: :cowboy_protocol.parse_method("", {:state, #Port<0.8513>, :ranch_tcp, [:cowboy_router, :cowboy_handler], false, [listener: Entice.Web.Endpoint.HTTP, dispatch: [{:_, [], [{["socket", "websocket"], [], Phoenix.Endpoint.CowboyWebSocket, {Phoenix.Transports.WebSocket, {Entice.Web.Endpoint, Entice.Web.Socket, :websocket}}}, {:_, [], Plug.Adapters.Cowboy.Handler, {Entice.Web.Endpoint, []}}]}]], :undefined, :undefined, 5, 1, 100, 4096, 64, 4096, 100, 5000, 1443424126397}, <<22, 3, 1, 0, 112, 1, 0, 0, 108, 3, 1, 86, 8, 231, 121, 154, 28, 85, 101, 127, 67, 142, 235, 201, 11, 48, 5, 91, 227, 3, 110, 145, 206, 106, 231, 149, 85, 250, 184, 82, 220, 226, 185, 0, 0, 24, 192, 20, 192, 19, ...>>)

    [error] Ranch protocol #PID<0.313.0> (:cowboy_protocol) of listener Entice.Web.Endpoint.HTTP terminated
    ** (exit) an exception was raised:
        ** (FunctionClauseError) no function clause matching in :cowboy_protocol.parse_method/3
                (cowboy) src/cowboy_protocol.erl:168: :cowboy_protocol.parse_method("", {:state, #Port<0.8513>, :ranch_tcp, [:cowboy_router, :cowboy_handler], false, [listener: Entice.Web.Endpoint.HTTP, dispatch: [{:_, [], [{["socket", "websocket"], [], Phoenix.Endpoint.CowboyWebSocket, {Phoenix.Transports.WebSocket, {Entice.Web.Endpoint, Entice.Web.Socket, :websocket}}}, {:_, [], Plug.Adapters.Cowboy.Handler, {Entice.Web.Endpoint, []}}]}]], :undefined, :undefined, 5, 1, 100, 4096, 64, 4096, 100, 5000, 1443424126397}, <<22, 3, 1, 0, 112, 1, 0, 0, 108, 3, 1, 86, 8, 231, 121, 154, 28, 85, 101, 127, 67, 142, 235, 201, 11, 48, 5, 91, 227, 3, 110, 145, 206, 106, 231, 149, 85, 250, 184, 82, 220, 226, 185, 0, 0, 24, 192, 20, 192, 19, ...>>)
                Terminate batch job (Y/N)? 
```

*Solution:* The client is configured to use a secured connection with SSL enabled. Disable SSL support in the client by using "ws://" for websocket connections instead of "wss://"
