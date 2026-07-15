package io.github.caleb67.modulartools.content.materials;

import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.datagen.TranslationUtil;
import io.github.caleb67.modulartools.tool.BaseMaterialBehavior;
import io.github.caleb67.modulartools.tool.Part;
import io.github.caleb67.modulartools.tool.tooltip.MaterialEffectTooltipOperation;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class PrismarineMaterialBehavior extends BaseMaterialBehavior {
    public static final ServerTickEvents.EndTick SUBMERGED_BEHAVIOR = minecraftServer -> {
        minecraftServer.getPlayerList().getPlayers().forEach(serverPlayer -> {
            PrismarineMaterialBehavior.testAndApply(serverPlayer.getMainHandItem(), serverPlayer);
        });
    };
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
    
    public static void testAndApply(ItemStack itemStack, Entity owner) {
        var head = Part.HEAD.getMaterial(itemStack);
        var rod = Part.ROD.getMaterial(itemStack);
        var trim = Part.TRIM.getMaterial(itemStack);
        
        var isPlayer = owner instanceof Player;
        if (!isPlayer) return;
        var player = (Player) owner;
        
        var submerged_mine_speed = player.getAttribute(Attributes.SUBMERGED_MINING_SPEED);
        assert submerged_mine_speed != null;
        var oxygen_bonus = player.getAttribute(Attributes.OXYGEN_BONUS);
        assert oxygen_bonus != null;
        
        if (head.isEmpty() || rod.isEmpty() || trim.isEmpty() ||
            !ItemStack.isSameItemSameComponents(player.getMainHandItem(), itemStack)) {
            submerged_mine_speed.removeModifier(SUBMERGED_MINING_INCREASE);
            oxygen_bonus.removeModifier(OXYGEN_BONUS_INCREASE);
            return;
        }
        
        double increase = (head.get() instanceof PrismarineMaterialBehavior ? 1 : 0)
            + (rod.get() instanceof PrismarineMaterialBehavior ? 1 : 0)
            + (trim.get() instanceof PrismarineMaterialBehavior ? 1 : 0);
        increase = increase*LapisMaterialBehavior.getAmplifierAmount(itemStack);
        
        if (increase == 0) {
            submerged_mine_speed.removeModifier(SUBMERGED_MINING_INCREASE);
            oxygen_bonus.removeModifier(OXYGEN_BONUS_INCREASE);
            return;
        }
        
        submerged_mine_speed.addOrReplacePermanentModifier(new AttributeModifier(
            SUBMERGED_MINING_INCREASE,
            increase/3,
            AttributeModifier.Operation.ADD_VALUE
        ));
        oxygen_bonus.addOrReplacePermanentModifier(new AttributeModifier(
            OXYGEN_BONUS_INCREASE,
            increase*10,
            AttributeModifier.Operation.ADD_VALUE
        ));
    }
}

