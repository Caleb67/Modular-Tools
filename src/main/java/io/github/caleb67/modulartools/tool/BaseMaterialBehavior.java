package io.github.caleb67.modulartools.tool;

import net.minecraft.world.item.Item;

import java.util.Set;

public class BaseMaterialBehavior extends MaterialBehavior {
    public BaseMaterialBehavior(Properties properties) {
        properties
            .setAttributesForHeadType(new HeadType.Pickaxe(), 1.0F, -2.8F)
            .setAttributesForHeadType(new HeadType.Shovel(), 1.5F, -3.0F)
            .setAttributesForHeadType(new HeadType.Axe(), 7.0F, -3.2F)
            .setAttributesForHeadType(new HeadType.Sword(), 3.0F, -2.4F)
            .setAttributesForHeadType(new HeadType.Hoe(), -3.0F, 0.0F);
        super(properties);
    }
    
    @Override protected Set<Item> validItemsToRepair() {
        return Set.of();
    }
}
