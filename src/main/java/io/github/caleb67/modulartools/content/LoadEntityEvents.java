package io.github.caleb67.modulartools.content;

import io.github.caleb67.modulartools.content.materials.EchoMaterialBehavior;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;

import java.util.ArrayList;

public class LoadEntityEvents {
    public static final ArrayList<ServerEntityEvents.Load> changes = new ArrayList<>();

    public static void load() {
        changes.add(EchoMaterialBehavior.ORE_SIGHT_BEHAVIOR_PURGE_DISPLAYS);
    }
}
