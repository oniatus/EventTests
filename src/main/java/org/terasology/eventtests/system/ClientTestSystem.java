package org.terasology.eventtests.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.event.ReceiveEvent;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterMode;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.eventtests.event.BroadcastTestEvent;
import org.terasology.eventtests.event.DefaultTestEvent;
import org.terasology.eventtests.event.OwnerTestEvent;
import org.terasology.eventtests.event.ServerTestEvent;

@RegisterSystem(RegisterMode.CLIENT)
public class ClientTestSystem extends BaseComponentSystem {

    private static final Logger logger = LoggerFactory.getLogger(ClientTestSystem.class);

    @Override
    public void initialise() {
        logger.info("initialized");
    }

    //broadcast
    @ReceiveEvent(netFilter = RegisterMode.ALWAYS)
    public void onBroadcastAlways(BroadcastTestEvent event, EntityRef entity) {
        logger.info("Triggered broadcast always");
    }

    @ReceiveEvent(netFilter = RegisterMode.AUTHORITY)
    public void onBroadcastAuthority(BroadcastTestEvent event, EntityRef entity) {
        logger.info("Triggered broadcast authority");
    }

    @ReceiveEvent(netFilter = RegisterMode.CLIENT)
    public void onBroadcastClient(BroadcastTestEvent event, EntityRef entity) {
        logger.info("Triggered broadcast client");
    }

    @ReceiveEvent(netFilter = RegisterMode.REMOTE_CLIENT)
    public void onBroadcastRemoteClient(BroadcastTestEvent event, EntityRef entity) {
        logger.info("Triggered broadcast remote client");
    }

    //default
    @ReceiveEvent(netFilter = RegisterMode.ALWAYS)
    public void onDefaultAlways(DefaultTestEvent event, EntityRef entity) {
        logger.info("Triggered default always");
    }

    @ReceiveEvent(netFilter = RegisterMode.AUTHORITY)
    public void onDefaultAuthority(DefaultTestEvent event, EntityRef entity) {
        logger.info("Triggered default authority");
    }

    @ReceiveEvent(netFilter = RegisterMode.CLIENT)
    public void onDefaultClient(DefaultTestEvent event, EntityRef entity) {
        logger.info("Triggered default client");
    }

    @ReceiveEvent(netFilter = RegisterMode.REMOTE_CLIENT)
    public void onDefaultRemoteClient(DefaultTestEvent event, EntityRef entity) {
        logger.info("Triggered default remote client");
    }

    //owner
    @ReceiveEvent(netFilter = RegisterMode.ALWAYS)
    public void onOwnerAlways(OwnerTestEvent event, EntityRef entity) {
        logger.info("Triggered owner always");
    }

    @ReceiveEvent(netFilter = RegisterMode.AUTHORITY)
    public void onOwnerAuthority(OwnerTestEvent event, EntityRef entity) {
        logger.info("Triggered owner authority");
    }

    @ReceiveEvent(netFilter = RegisterMode.CLIENT)
    public void onOwnerClient(OwnerTestEvent event, EntityRef entity) {
        logger.info("Triggered owner client");
    }

    @ReceiveEvent(netFilter = RegisterMode.REMOTE_CLIENT)
    public void onOwnerRemoteClient(OwnerTestEvent event, EntityRef entity) {
        logger.info("Triggered owner remote client");
    }

    //server
    @ReceiveEvent(netFilter = RegisterMode.ALWAYS)
    public void onServerAlways(ServerTestEvent event, EntityRef entity) {
        logger.info("Triggered server always");
    }

    @ReceiveEvent(netFilter = RegisterMode.AUTHORITY)
    public void onServerAuthority(ServerTestEvent event, EntityRef entity) {
        logger.info("Triggered server authority");
    }

    @ReceiveEvent(netFilter = RegisterMode.CLIENT)
    public void onServerClient(ServerTestEvent event, EntityRef entity) {
        logger.info("Triggered server client");
    }

    @ReceiveEvent(netFilter = RegisterMode.REMOTE_CLIENT)
    public void onServerRemoteClient(ServerTestEvent event, EntityRef entity) {
        logger.info("Triggered server remote client");
    }
}
