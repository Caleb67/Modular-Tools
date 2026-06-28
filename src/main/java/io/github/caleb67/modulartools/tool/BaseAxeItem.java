package io.github.caleb67.modulartools.tool;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Items;
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
    public @NotNull HeadType getHeadType() {
        return new HeadType.Axe();
    }
}
