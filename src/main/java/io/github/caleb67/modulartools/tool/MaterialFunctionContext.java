package io.github.caleb67.modulartools.tool;

import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;

import java.util.ArrayList;

public class MaterialFunctionContext {
    private ArrayList<ResourceKey<MaterialBehavior>> has_been_seen = new ArrayList<>();
    public final HolderLookup.Provider registryAccess;
    
    public MaterialFunctionContext(HolderLookup.Provider registryAccess) {
        this.registryAccess = registryAccess;
    }
    
    public MaterialFunctionContext(MaterialFunctionContext other, HolderLookup.Provider registryAccess) {
        this.registryAccess = registryAccess;
        this.has_been_seen = other.has_been_seen;
    }
    
    public void add(ResourceKey<MaterialBehavior> seen) {
        this.has_been_seen.add(seen);
    }
    
    public boolean hasSeen(ResourceKey<MaterialBehavior> what) {
        return this.has_been_seen.contains(what);
    }
}
