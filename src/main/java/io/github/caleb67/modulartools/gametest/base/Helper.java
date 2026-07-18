package io.github.caleb67.modulartools.gametest.base;

import io.github.caleb67.modulartools.register.MTDataComponents;
import io.github.caleb67.modulartools.tool.AbstractModularToolItem;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTestAssertException;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class Helper {
    
    
    public static void setTool(AbstractModularToolItem tool, Player player, int slot,
                               MaterialBehavior head, MaterialBehavior rod, MaterialBehavior trim) {
        var stack = tool.getDefaultInstance();
        stack.set(MTDataComponents.MODULAR_TOOL_HEAD, head.key);
        stack.set(MTDataComponents.MODULAR_TOOL_ROD, rod.key);
        stack.set(MTDataComponents.MODULAR_TOOL_TRIM, trim.key);
        player.getInventory().setItem(slot, stack);
    }
    
    public static void setTool(AbstractModularToolItem tool, Player player, int slot, MaterialBehavior material) {
        var stack = tool.getDefaultInstance();
        stack.set(MTDataComponents.MODULAR_TOOL_HEAD, material.key);
        stack.set(MTDataComponents.MODULAR_TOOL_ROD, material.key);
        stack.set(MTDataComponents.MODULAR_TOOL_TRIM, material.key);
        player.getInventory().setItem(slot, stack);
    }
    
    public static void expectLevellessEnchantment(GameTestHelper helper, ResourceKey<Enchantment> enchantmentKey, ItemStack stack, int level) {
        expectEnchantment(helper, enchantmentKey, stack, Math.min(level, 1));
    }
    
    public static void expectEnchantment(GameTestHelper context, ResourceKey<Enchantment> enchantmentKey, ItemStack stack, int level) {
        Holder<Enchantment> enchantment = context.getLevel().registryAccess()
                                                 .lookupOrThrow(Registries.ENCHANTMENT)
                                                 .getOrThrow(enchantmentKey);
        
        var enchantments = stack.getEnchantments().entrySet();
        if (level == 0) {
            context.assertTrue(enchantments.isEmpty(), TestError.wrongNumberEnchantments(0, enchantments.size()));
            return;
        }
        
        var toolsEnchantment = enchantments.stream().filter(entry -> entry.getKey() == enchantment).findFirst();
        context.assertTrue(toolsEnchantment.isPresent(), TestError.noEnchantment(enchantmentKey));
        
        var enchantLevel = toolsEnchantment.get().getIntValue();
        context.assertTrue(enchantLevel == level, TestError.wrongLevelEnchantment(level, enchantLevel));
    }
    
    public static void expectAttributeModifier(GameTestHelper context,
                                               Holder<Attribute> attribute, Identifier modifier,
                                               ServerPlayer player, int level, double value) {
        var has_modifier = player.getAttributes().hasModifier(attribute, modifier);
        if (level == 0) {
            context.assertFalse(has_modifier, TestError.unexpectedAttributeModifier(modifier));
            return;
        }
        context.assertTrue(has_modifier, TestError.noAttributeModifier(modifier));
        var modifier_value = player.getAttributes().getModifierValue(attribute, modifier);
        context.assertTrue(modifier_value == value, TestError.wrongValueAttributeModifier(value, modifier_value));
    }
    
    /**
     * Get the number of enchantments on an item.
     *
     * @param stack the item
     * @return number of enchantments on <code>ItemStack stack</code>
     */
    public static int getNumEnchantments(ItemStack stack) {
        return stack.getEnchantments().size();
    }
    
    /**
     * Append more information to a game test exception.
     *
     * @param context   context
     * @param exception original exception
     * @param with      text to append
     * @return new exception with additional text
     */
    public static GameTestAssertException specify(GameTestHelper context, GameTestAssertException exception, String with) {
        return new GameTestAssertException(exception.message.copy().append(" " + with), (int) context.getTick());
    }
}
