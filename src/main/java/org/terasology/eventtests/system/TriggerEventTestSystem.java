package org.terasology.eventtests.system;

import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.event.ReceiveEvent;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterMode;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.eventtests.event.BroadcastTestEvent;
import org.terasology.eventtests.event.DefaultTestEvent;
import org.terasology.eventtests.event.OwnerTestEvent;
import org.terasology.eventtests.event.ServerTestEvent;
import org.terasology.logic.console.commandSystem.CommandParameterSuggester;
import org.terasology.logic.console.commandSystem.annotations.Command;
import org.terasology.logic.console.commandSystem.annotations.CommandParam;
import org.terasology.logic.console.commandSystem.annotations.Sender;
import org.terasology.logic.permission.PermissionManager;
import org.terasology.network.ClientComponent;
import org.terasology.network.events.ConnectedEvent;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

@RegisterSystem(RegisterMode.ALWAYS)
public class TriggerEventTestSystem extends BaseComponentSystem {

    public static enum EventType {
        DEFAULT, BROADCAST, OWNER, SERVER
    }

    public static class EventTypeSuggester implements CommandParameterSuggester<String> {

        private static final Set<EventType> TYPES = EnumSet.allOf(EventType.class);
        
        @Override
        public Set<String> suggest(EntityRef sender, Object ... resolvedParameters) {
            //TODO return enum set as soon as the command adapter works
            return TYPES.stream().map(x -> x.name()).collect(Collectors.toSet());
        }
        
    }

    @Override
    public void initialise() {
        super.initialise();
    }

    @ReceiveEvent(components = ClientComponent.class)
    public void onConnect(ConnectedEvent event, EntityRef client) {
        client.send(new BroadcastTestEvent());
        client.send(new DefaultTestEvent());
        client.send(new OwnerTestEvent());
        client.send(new ServerTestEvent());
    }

    @Command(requiredPermission = PermissionManager.NO_PERMISSION)
    public void triggerLocalEvent(@CommandParam(value = "type", suggester = EventTypeSuggester.class) String type, @Sender EntityRef sender) {
        switch (EventType.valueOf(type)) {
            case BROADCAST:
                sender.send(new BroadcastTestEvent());
                break;
            case DEFAULT:
                sender.send(new DefaultTestEvent());
                break;
            case OWNER:
                sender.send(new OwnerTestEvent());
                break;
            case SERVER:
                sender.send(new ServerTestEvent());
                break;
            default:
                break;
        }
    }

    @Command(requiredPermission = PermissionManager.NO_PERMISSION, runOnServer = true)
    public void triggerServerEvent(@CommandParam(value = "type", suggester = EventTypeSuggester.class) String type, @Sender EntityRef sender) {
        switch (EventType.valueOf(type)) {
            case BROADCAST:
                sender.send(new BroadcastTestEvent());
                break;
            case DEFAULT:
                sender.send(new DefaultTestEvent());
                break;
            case OWNER:
                sender.send(new OwnerTestEvent());
                break;
            case SERVER:
                sender.send(new ServerTestEvent());
                break;
            default:
                break;
        }
    }
}
