package io.github.caleb67.modulartools.content.materials;

import io.github.caleb67.modulartools.datagen.TranslationUtil;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import io.github.caleb67.modulartools.tool.Part;
import io.github.caleb67.modulartools.tool.tooltip.MaterialEffectTooltipOperation;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.Optional;

import static io.github.caleb67.modulartools.content.LootTableChanges.SMELTING_CHECK;

public class BlazeMaterialBehavior extends MaterialBehavior {
    public static final LootTableEvents.ModifyDrops SMELTING_BEHAVIOR = (table, context, stacks) -> {
        var isKill = context.hasParameter(LootContextParams.ATTACKING_ENTITY);
        var isBlockBreak = context.hasParameter(LootContextParams.BLOCK_STATE);
        if (!isKill && !isBlockBreak) return;

        ItemInstance tool;
        if (isKill) {
            var source = context.getParameter(LootContextParams.ATTACKING_ENTITY);
            if (!(source instanceof Player player)) return;
            tool = player.getMainHandItem();
        } else {
            if (!context.hasParameter(LootContextParams.TOOL)) return;
            tool = context.getParameter(LootContextParams.TOOL);
        }

        var head = Part.HEAD.getMaterial(tool);
        var rod = Part.ROD.getMaterial(tool);
        var trim = Part.TRIM.getMaterial(tool);
        if (head.isEmpty() || rod.isEmpty() || trim.isEmpty()) return;
        if (!(head.get() instanceof BlazeMaterialBehavior) &&
            !(rod.get() instanceof BlazeMaterialBehavior) &&
            !(trim.get() instanceof BlazeMaterialBehavior)) return;

        stacks.replaceAll(stack -> {
                    SingleRecipeInput input = new SingleRecipeInput(stack);
                    var out_stack = SMELTING_CHECK.getRecipeFor(input, context.getLevel())
                        .map(RecipeHolder::value)
                        .map(recipe -> recipe.assemble(input))
                        .orElse(stack);
                    out_stack.setCount(stack.getCount());
                    return out_stack;
                }
        );

    };
    public BlazeMaterialBehavior(Properties properties) {
        super(properties);
    }

    public Optional<MaterialEffectTooltipOperation> getEffectTooltip(ItemStack itemStack, int numTimes) {
        return Optional.of((executor, context,
                            display, builder, tooltipFlag) -> {
            builder.accept(
                    Component.translatable(TranslationUtil.makeEffectDescId(this.key, numTimes))
                            .withStyle(this.getEffectFormatting())
            );
        });
    }
}
