package io.github.caleb67.modulartools.content.materials;

import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.datagen.TranslationUtil;
import io.github.caleb67.modulartools.tool.BaseMaterialBehavior;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import io.github.caleb67.modulartools.tool.MaterialFunctionContext;
import io.github.caleb67.modulartools.tool.Part;
import io.github.caleb67.modulartools.tool.tooltip.MaterialEffectTooltipOperation;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
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

public class PrismarineMaterialBehavior extends BaseMaterialBehavior {
    public static final Identifier OXYGEN_BONUS_INCREASE = Identifier.fromNamespaceAndPath(ModularTools.MODID,
        "oxygen_bonus_increase");
    public static final Identifier SUBMERGED_MINING_INCREASE = Identifier.fromNamespaceAndPath(ModularTools.MODID,
        "submerged_mining_increase");
    
    public PrismarineMaterialBehavior(Properties properties) {
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
        
        var submerged_mine_speed = getAttribute(player, Attributes.SUBMERGED_MINING_SPEED).orElseThrow();
        var oxygen_bonus = getAttribute(player, Attributes.OXYGEN_BONUS).orElseThrow();
        
        if (!(slot == EquipmentSlot.MAINHAND)) {
            removeModifiers(submerged_mine_speed, oxygen_bonus);
            return;
        }
        
        double increase = getIncrease(context.head, context.rod, context.trim, itemStack);
        
        addModifiers(submerged_mine_speed, oxygen_bonus, increase);
    }
    
    private static double getIncrease(MaterialBehavior head, MaterialBehavior rod, MaterialBehavior trim, ItemStack itemStack) {
        return (
            (head instanceof PrismarineMaterialBehavior ? 1 : 0)
                + (rod instanceof PrismarineMaterialBehavior ? 1 : 0)
                + (trim instanceof PrismarineMaterialBehavior ? 1 : 0)
        )*LapisMaterialBehavior.getAmplifierAmount(itemStack);
    }
    
    private void removeModifiers(AttributeInstance submergedMineSpeed, AttributeInstance oxygenBonus) {
        submergedMineSpeed.removeModifier(SUBMERGED_MINING_INCREASE);
        oxygenBonus.removeModifier(OXYGEN_BONUS_INCREASE);
    }
    
    private void addModifiers(AttributeInstance submergedMineSpeed, AttributeInstance oxygenBonus, double increase) {
        submergedMineSpeed.addOrReplacePermanentModifier(new AttributeModifier(
            SUBMERGED_MINING_INCREASE,
            increase/3,
            AttributeModifier.Operation.ADD_VALUE
        ));
        oxygenBonus.addOrReplacePermanentModifier(new AttributeModifier(
            OXYGEN_BONUS_INCREASE,
            increase*10,
            AttributeModifier.Operation.ADD_VALUE
        ));
    }
}

