package io.github.caleb67.modulartools.tool;

import io.github.caleb67.modulartools.ModularToolsRegistries;
import net.minecraft.world.item.ItemInstance;

import java.util.Optional;

import static io.github.caleb67.modulartools.tool.AbstractModularToolItem.*;

public enum Part {
    HEAD,
    ROD,
    TRIM;

    public String getName() {
        return switch (this) {
            case ROD -> "rod";
            case HEAD -> "head";
            case TRIM -> "trim";
        };
    }

    public Optional<MaterialBehavior> getMaterial(ItemInstance itemInstance) {
        return switch (this) {
            case HEAD -> getToolHead(itemInstance).flatMap(ModularToolsRegistries.MATERIAL_BEHAVIOR::get)
                        .flatMap(value -> Optional.of(value.value()));
            case ROD -> getToolRod(itemInstance).flatMap(ModularToolsRegistries.MATERIAL_BEHAVIOR::get)
                        .flatMap(value -> Optional.of(value.value()));
            case TRIM -> getToolTrim(itemInstance).flatMap(ModularToolsRegistries.MATERIAL_BEHAVIOR::get)
                        .flatMap(value -> Optional.of(value.value()));
        };
    }
}
