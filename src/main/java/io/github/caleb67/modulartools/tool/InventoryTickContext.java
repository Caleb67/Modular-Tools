package io.github.caleb67.modulartools.tool;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryTickContext {
    private ArrayList<ResourceKey<MaterialBehavior>> has_been_seen = new ArrayList<>();
    public void add(ResourceKey<MaterialBehavior> seen) {
        this.has_been_seen.add(seen);
    }

    public boolean hasSeen(ResourceKey<MaterialBehavior> what) {
        return this.has_been_seen.contains(what);
    }
}
