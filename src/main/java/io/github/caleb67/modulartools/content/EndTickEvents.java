package io.github.caleb67.modulartools.content;

import io.github.caleb67.modulartools.content.materials.BlazeMaterialBehavior;
import io.github.caleb67.modulartools.content.materials.CopperMaterialBehavior;
import io.github.caleb67.modulartools.content.materials.PrismarineMaterialBehavior;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import java.util.ArrayList;

public class EndTickEvents {
    public static final ArrayList<ServerTickEvents.EndTick> changes = new ArrayList<>();

    public static void load() {
        changes.add(CopperMaterialBehavior.REACH_BEHAVIOR);
        changes.add(PrismarineMaterialBehavior.SUBMERGED_BEHAVIOR);
    }
}
