package io.github.caleb67.modulartools.content;

import io.github.caleb67.modulartools.content.materials.EchoMaterialBehavior;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import java.util.ArrayList;

public class EndTickEvents {
    public static final ArrayList<ServerTickEvents.EndTick> changes = new ArrayList<>();
    
    public static void load() {
        changes.add(EchoMaterialBehavior.ORE_SIGHT_BEHAVIOR_CLEAR_DISPLAYS);
    }
}
