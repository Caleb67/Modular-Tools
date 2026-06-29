package io.github.caleb67.modulartools.content;

import io.github.caleb67.modulartools.content.materials.DragonMaterialBehavior;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLevelEvents;

import java.util.ArrayList;

public class LevelLoadEvents {
    public static final ArrayList<ServerLevelEvents.Load> changes = new ArrayList<>();

    public static void load() {
        changes.add(DragonMaterialBehavior.ORE_SIGHT_BEHAVIOR_LOAD_LEVEL);
    }
}
