package io.github.caleb67.modulartools.content.materials;

import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.ModularToolsRegistries;
import io.github.caleb67.modulartools.datagen.TranslationUtil;
import io.github.caleb67.modulartools.register.MTDataComponents;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import io.github.caleb67.modulartools.tool.tooltip.ToolEffectTooltipOperation;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class CopperMaterialBehavior extends MaterialBehavior {
    public static final ServerTickEvents.EndTick REACH_BEHAVIOR = minecraftServer -> {
        minecraftServer.getPlayerList().getPlayers().forEach(serverPlayer -> {
            CopperMaterialBehavior.testAndApply(serverPlayer.getMainHandItem(), serverPlayer);
        });
    };
    public static final Identifier INCREASE_ENTITY_REACH = Identifier.fromNamespaceAndPath(ModularTools.MODID, "increase_entity_reach");
    public static final Identifier INCREASE_BLOCK_REACH = Identifier.fromNamespaceAndPath(ModularTools.MODID, "increase_block_reach");

    public CopperMaterialBehavior(Properties properties) {
        super(properties);
    }

    public Optional<ToolEffectTooltipOperation> getEffectTooltip(ItemStack itemStack, int numTimes) {
        return Optional.of((executor, context,
                            display, builder, tooltipFlag) -> {
            builder.accept(
                    Component.translatable(TranslationUtil.makeEffectDescId(this.key, numTimes))
                            .withStyle(this.getEffectFormatting())
            );
        });
    }

    public static void testAndApply(ItemStack itemStack, Entity owner) {
        var modular_tool_head = itemStack.get(MTDataComponents.MODULAR_TOOL_HEAD);
        var modular_tool_rod = itemStack.get(MTDataComponents.MODULAR_TOOL_ROD);
        var modular_tool_trim = itemStack.get(MTDataComponents.MODULAR_TOOL_TRIM);

        var isPlayer = owner instanceof Player;
        if (!isPlayer) return;
        var player = (Player) owner;

        var block_range = player.getAttribute(Attributes.BLOCK_INTERACTION_RANGE);
        assert block_range != null;
        var interaction_range = player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE);
        assert interaction_range != null;

        if (modular_tool_head == null ||
                modular_tool_rod == null ||
                modular_tool_trim == null) {
            block_range.removeModifier(INCREASE_BLOCK_REACH);
            return;
            // !TODO log this at some point
        }

        var head = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(modular_tool_head).value();
        var rod = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(modular_tool_rod).value();
        var trim = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(modular_tool_trim).value();

        if (!ItemStack.isSameItemSameComponents(player.getMainHandItem(), itemStack)) {
            block_range.removeModifier(INCREASE_BLOCK_REACH);
            return;
        }

        double increase = 0;
        if (head instanceof CopperMaterialBehavior)
            increase += 2;
        if (rod instanceof CopperMaterialBehavior)
            increase += 2;
        if (trim instanceof CopperMaterialBehavior)
            increase += 2;

        increase = increase * LapisMaterialBehavior.getAmplifierAmount(itemStack);

        if (increase == 0) {
            block_range.removeModifier(INCREASE_BLOCK_REACH);
            interaction_range.removeModifier(INCREASE_ENTITY_REACH);
            return;
        }


        block_range.addOrReplacePermanentModifier(new AttributeModifier(
                INCREASE_BLOCK_REACH,
                increase,
                AttributeModifier.Operation.ADD_VALUE
        ));

        interaction_range.addOrReplacePermanentModifier(new AttributeModifier(
                INCREASE_ENTITY_REACH,
                increase,
                AttributeModifier.Operation.ADD_VALUE
        ));
    }
}
