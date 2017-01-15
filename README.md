Module to test event broadcasting.

The module offers two commands:
- `triggerLocalEvent <BROADCAST | DEFAULT | OWNER | SERVER>`
- `triggerServerEvent <BROADCAST | DEFAULT | OWNER | SERVER>`

Both commands will send a new event to the player entity (the `@Sender` of the command) but `triggerServerEvent` requires the command to run on the server (`@Command(runOnServer = true)`).

- BROADCAST sends an Event with `@BroadcastEvent`
- DEFAULT sends an Event without any modifications
- OWNER sends an Event with `@OwnerEvent`
- SERVER sends an Event with `@ServerEvent`

The module will register 4 Systems which will listen to all events:

- `AlwaysTestSystem` with `@RegisterSystem(RegisterMode.ALWAYS)`
- `AuthorityTestSystem` with `@RegisterSystem(RegisterMode.AUTHORITY)`
- `ClientTestSystem` with `@RegisterSystem(RegisterMode.CLIENT)`
- `RemoteClientTestSystem` with `@RegisterSystem(RegisterMode.REMOTE_CLIENT)`

Each system registers four event listeners for all events, each with a different netFilter.
Example:
```
    @ReceiveEvent(netFilter = RegisterMode.ALWAYS)
    public void onBroadcastAlways(BroadcastTestEvent event, EntityRef entity) {
        logger.info("Triggered broadcast always");
    }
```

For testing, start the game in singleplayer, headless server or local multiplayer, connect with as many players as you want and then trigger events to observer the log output.

For example, `triggerLocalEvent BROADCAST` in singleplayer would give the following log output:
```
11:38:29.020 [main] INFO  o.t.e.system.AlwaysTestSystem - Triggered broadcast client
11:38:29.020 [main] INFO  o.t.e.system.AuthorityTestSystem - Triggered broadcast client
11:38:29.020 [main] INFO  o.t.e.system.AuthorityTestSystem - Triggered broadcast always
11:38:29.020 [main] INFO  o.t.e.system.AuthorityTestSystem - Triggered broadcast authority
11:38:29.020 [main] INFO  o.t.e.system.ClientTestSystem - Triggered broadcast always
11:38:29.020 [main] INFO  o.t.e.system.ClientTestSystem - Triggered broadcast client
11:38:29.020 [main] INFO  o.t.e.system.ClientTestSystem - Triggered broadcast authority
11:38:29.020 [main] INFO  o.t.e.system.AlwaysTestSystem - Triggered broadcast always
11:38:29.020 [main] INFO  o.t.e.system.AlwaysTestSystem - Triggered broadcast authority
```
So all systems except remote client triggered and each system triggered on three listeners, except the listener with the remote client netFilter
