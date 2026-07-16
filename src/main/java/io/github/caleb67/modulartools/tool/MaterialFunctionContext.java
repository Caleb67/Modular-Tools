package io.github.caleb67.modulartools.tool;

import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;

import java.util.ArrayList;

public class MaterialFunctionContext {
    private final ArrayList<ResourceKey<MaterialBehavior>> has_been_seen = new ArrayList<>();
    public final HolderLookup.Provider registryAccess;
    
    public MaterialFunctionContext(HolderLookup.Provider registryAccess,
                                   MaterialBehavior head, MaterialBehavior rod, MaterialBehavior trim) {
        this.registryAccess = registryAccess;
    }
    
    public void add(ResourceKey<MaterialBehavior> seen) {
        this.has_been_seen.add(seen);
    }
    
    public boolean hasSeen(ResourceKey<MaterialBehavior> what) {
        return this.has_been_seen.contains(what);
    }
}
