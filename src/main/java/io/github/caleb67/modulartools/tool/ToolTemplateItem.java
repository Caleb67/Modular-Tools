package io.github.caleb67.modulartools.tool;

import net.minecraft.world.item.Item;

public class ToolTemplateItem extends Item {
    public final AbstractModularToolItem modularTool;
    
    public ToolTemplateItem(Properties properties, AbstractModularToolItem modularTool) {
        super(properties.stacksTo(1));
        this.modularTool = modularTool;
    }
}
