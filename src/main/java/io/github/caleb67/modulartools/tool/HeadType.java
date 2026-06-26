package io.github.caleb67.modulartools.tool;

import io.github.caleb67.modulartools.ModularTools;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public interface HeadType {
    record Pickaxe() implements HeadType {
        @Override
        public Identifier getName() {return Identifier.fromNamespaceAndPath(ModularTools.MODID, "pickaxe");}
        @Override
        public Tool getTool(ToolMaterial material) {
            return new Tool(List.of(
                            Tool.Rule.deniesDrops(BuiltInRegistries.BLOCK.getOrThrow(material.incorrectBlocksForDrops())),
                            Tool.Rule.minesAndDrops(BuiltInRegistries.BLOCK.getOrThrow(BlockTags.MINEABLE_WITH_PICKAXE), material.speed())
                    ), 1.0F, 1, true);
        }
    }
    record Shovel() implements HeadType {
        @Override
        public Identifier getName() {return Identifier.fromNamespaceAndPath(ModularTools.MODID, "shovel");}
        @Override
        public Tool getTool(ToolMaterial material) {
            return new Tool(List.of(
                            Tool.Rule.deniesDrops(BuiltInRegistries.BLOCK.getOrThrow(material.incorrectBlocksForDrops())),
                            Tool.Rule.minesAndDrops(BuiltInRegistries.BLOCK.getOrThrow(BlockTags.MINEABLE_WITH_SHOVEL), material.speed())
                    ), 1.0F, 1, true);
        }
    }
    record Hoe() implements HeadType {
        @Override
        public Identifier getName() {return Identifier.fromNamespaceAndPath(ModularTools.MODID, "hoe");}
    }
    record Sword() implements HeadType {
        @Override
        public Identifier getName() {return Identifier.fromNamespaceAndPath(ModularTools.MODID, "sword");}

        @Override
        public Tool getTool(ToolMaterial material) {
            return new Tool(List.of(
                            Tool.Rule.minesAndDrops(HolderSet.direct(Blocks.COBWEB.builtInRegistryHolder()), 15.0F),
                            Tool.Rule.overrideSpeed(BuiltInRegistries.BLOCK.getOrThrow(BlockTags.SWORD_INSTANTLY_MINES), Float.MAX_VALUE),
                            Tool.Rule.overrideSpeed(BuiltInRegistries.BLOCK.getOrThrow(BlockTags.SWORD_EFFICIENT), 1.5F)
                    ), 1.0F, 2, false);
        }
    }
    record Axe() implements HeadType {
        @Override
        public Identifier getName() {return Identifier.fromNamespaceAndPath(ModularTools.MODID, "axe");}
        @Override
        public Tool getTool(ToolMaterial material) {
            return new Tool(List.of(
                    Tool.Rule.deniesDrops(BuiltInRegistries.BLOCK.getOrThrow(material.incorrectBlocksForDrops())),
                    Tool.Rule.minesAndDrops(BuiltInRegistries.BLOCK.getOrThrow(BlockTags.MINEABLE_WITH_AXE), material.speed())
            ), 1.0F, 1, true);
        }
    }
    record Spear() implements HeadType {
        @Override
        public Identifier getName() {return Identifier.fromNamespaceAndPath(ModularTools.MODID, "spear");}
    }
    record NotApplicable() implements HeadType {
        @Override
        public Identifier getName() {return Identifier.fromNamespaceAndPath(ModularTools.MODID, "not_applicable");}
    }

    Identifier getName();

    default Tool getTool(ToolMaterial material) { return null; };
}
