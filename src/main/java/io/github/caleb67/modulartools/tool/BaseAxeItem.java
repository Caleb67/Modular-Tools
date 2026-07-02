package io.github.caleb67.modulartools.tool;

import org.jetbrains.annotations.NotNull;

public class BaseAxeItem extends AbstractModularToolItem {
    
    public BaseAxeItem(Properties properties) {
        super(properties);
    }
    
    
    @Override
    public @NotNull HeadType getHeadType() {
        return new HeadType.Axe();
    }
}
