package io.github.caleb67.modulartools.gametest.base;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

public final class TestError {
    private TestError() {}
    
    public static String wrongNumberEnchantments(int expected, int actual) {
        return String.format("Wrong number of enchantments, expected %d, but found %d.", expected, actual);
    }
    
    public static String noEnchantment(ResourceKey<Enchantment> expected) {
        return String.format("Missing enchantment, expected %s, but wasn't found.", expected);
    }
    
    public static String wrongLevelEnchantment(int expected, int actual) {
        return String.format("Wrong level enchantment, expected %d, but found %d.", expected, actual);
    }
    
    public static String unexpectedAttributeModifier(Identifier actual) {
        return String.format("Unexpected attribute modifier, expected nothing, but found %s.", actual);
    }
    
    public static String noAttributeModifier(Identifier expected) {
        return String.format("Missing attribute modifier, expected %s, but wasn't found.", expected);
    }
    
    public static String wrongValueAttributeModifier(double expected, double actual) {
        return String.format("Wrong value for attribute modifier, expected %f, but found %f.", expected, actual);
    }
}
