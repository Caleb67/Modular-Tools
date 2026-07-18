package io.github.caleb67.modulartools.content.materials;

import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.datagen.TranslationUtil;
import io.github.caleb67.modulartools.tool.BaseMaterialBehavior;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import io.github.caleb67.modulartools.tool.MaterialFunctionContext;
import io.github.caleb67.modulartools.tool.tooltip.MaterialEffectTooltipOperation;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CopperMaterialBehavior extends BaseMaterialBehavior {
    public static final Identifier INCREASE_ENTITY_REACH = Identifier.fromNamespaceAndPath(ModularTools.MODID,
        "increase_entity_reach");
    public static final Identifier INCREASE_BLOCK_REACH = Identifier.fromNamespaceAndPath(ModularTools.MODID,
        "increase_block_reach");
    
    public CopperMaterialBehavior(Properties properties) {
        super(properties);
    }
    
    public Optional<MaterialEffectTooltipOperation> getEffectTooltip(ItemStack itemStack, int numTimes) {
        return Optional.of((collector, context,
                            display, builder, tooltipFlag) -> {
            builder.accept(
                Component.translatable(TranslationUtil.makeEffectDescId(this.key, numTimes))
                         .withStyle(this.getEffectFormatting())
            );
        });
    }
    
    @Override
    public void inventoryTick(MaterialFunctionContext context, ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        if (context.hasSeen(this.key)) return;
        if (!(owner instanceof Player player)) return;
        
        var block_range = getAttribute(player, Attributes.BLOCK_INTERACTION_RANGE).orElseThrow();
        var interaction_range = getAttribute(player, Attributes.ENTITY_INTERACTION_RANGE).orElseThrow();
        
        if (!(slot == EquipmentSlot.MAINHAND)) {
            removeModifiers(block_range, interaction_range);
            return;
        }
        
        double increase = getIncrease(context.head, context.rod, context.trim, itemStack);
        
        addModifiers(block_range, interaction_range, increase);
    }
    
    private static double getIncrease(MaterialBehavior head, MaterialBehavior rod, MaterialBehavior trim, ItemStack itemStack) {
        return (
            (head instanceof CopperMaterialBehavior ? 2 : 0)
                + (rod instanceof CopperMaterialBehavior ? 2 : 0)
                + (trim instanceof CopperMaterialBehavior ? 2 : 0)
        )*LapisMaterialBehavior.getAmplifierAmount(itemStack);
    }
    
    @Override public void removeEffects(MaterialFunctionContext context, Entity owner, ItemStack itemStack) {
        if (!(owner instanceof Player player)) return;
        var block_range = getAttribute(player, Attributes.BLOCK_INTERACTION_RANGE).orElseThrow();
        var interaction_range = getAttribute(player, Attributes.ENTITY_INTERACTION_RANGE).orElseThrow();
        removeModifiers(block_range, interaction_range);
    }
    
    private void removeModifiers(AttributeInstance blockRange, AttributeInstance interactionRange) {
        blockRange.removeModifier(INCREASE_BLOCK_REACH);
        interactionRange.removeModifier(INCREASE_ENTITY_REACH);
    }
    
    private void addModifiers(AttributeInstance blockRange, AttributeInstance interactionRange, double increase) {
        blockRange.addOrReplacePermanentModifier(new AttributeModifier(
            INCREASE_BLOCK_REACH,
            increase,
            AttributeModifier.Operation.ADD_VALUE
        ));
        interactionRange.addOrReplacePermanentModifier(new AttributeModifier(
            INCREASE_ENTITY_REACH,
            increase,
            AttributeModifier.Operation.ADD_VALUE
        ));
    }
}
