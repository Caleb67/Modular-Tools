package io.github.caleb67.modulartools.tool;

import io.github.caleb67.modulartools.ModularToolsRegistries;
import net.minecraft.world.item.ItemStack;

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

    public Optional<MaterialBehavior> getMaterial(ItemStack itemStack) {
        return switch (this) {
            case HEAD -> getToolHead(itemStack).flatMap(ModularToolsRegistries.MATERIAL_BEHAVIOR::get)
                        .flatMap(value -> Optional.of(value.value()));
            case ROD -> getToolRod(itemStack).flatMap(ModularToolsRegistries.MATERIAL_BEHAVIOR::get)
                        .flatMap(value -> Optional.of(value.value()));
            case TRIM -> getToolTrim(itemStack).flatMap(ModularToolsRegistries.MATERIAL_BEHAVIOR::get)
                        .flatMap(value -> Optional.of(value.value()));
        };
    }
}
