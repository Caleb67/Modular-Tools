package io.github.caleb67.modulartools.content;

import io.github.caleb67.modulartools.content.materials.DragonMaterialBehavior;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;

import java.util.ArrayList;

public class LoadEntityEvents {
    public static final ArrayList<ServerEntityEvents.Load> changes = new ArrayList<>();

    public static void load() {
        changes.add(DragonMaterialBehavior.ORE_SIGHT_BEHAVIOR_PURGE_DISPLAYS);
    }
}
