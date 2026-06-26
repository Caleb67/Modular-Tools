package io.github.caleb67.modulartools.tool;

import io.github.caleb67.modulartools.ModularTools;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;

public class BaseAxeItem extends AbstractModularToolItem{

    public BaseAxeItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        var result = Items.NETHERITE_AXE.useOn(context);
        return result == InteractionResult.PASS ? super.useOn(context) : result;
    }

    @Override
    protected @NotNull HeadType getHeadType() {
        return new HeadType.Axe();
    }
}
