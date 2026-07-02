package io.github.caleb67.modulartools.content.blocks;

import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.network.ServerboundForgeToolPayload;
import io.github.caleb67.modulartools.register.Blocks;
import io.github.caleb67.modulartools.register.MTDataComponents;
import io.github.caleb67.modulartools.register.MenuTypes;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import io.github.caleb67.modulartools.tool.ToolTemplateItem;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BlastFurnaceBlock;
import org.jspecify.annotations.NonNull;

import java.util.function.Function;

public class ForgeMenu extends AbstractContainerMenu {
    private static final int SLOTS_ROWS = 3;
    private static final int SLOTS_COLUMNS = 3;
    private static final int SLOTS_COUNT = SLOTS_ROWS*SLOTS_COLUMNS;
    
    private static final int CONTAINER_START = 0;
    private static final int CONTAINER_END = SLOTS_COUNT;
    private static final int INVENTORY_START = CONTAINER_END;
    private static final int INVENTORY_END = INVENTORY_START + Inventory.INVENTORY_SIZE;
    
    private static final int CONTAINER_START_X = 62;
    private static final int CONTAINER_START_Y = 17;
    private static final int INVENTORY_START_X = 8;
    private static final int INVENTORY_START_Y = 84;
    
    protected final CraftingContainer forgeSlots;
    protected final ResultContainer resultSlots = new ResultContainer();
    private final ContainerLevelAccess access;
    private final Player player;
    private final ContainerData data;
    
    public ForgeMenu(final int containerId, final Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL, new SimpleContainerData(1));
    }
    
    public ForgeMenu(int containerId, final Inventory inventory,
                     final ContainerLevelAccess access, ContainerData data) {
        super(MenuTypes.FORGE, containerId);
        this.access = access;
        this.player = inventory.player;
        this.data = data;
        
        this.forgeSlots = new TransientCraftingContainer(this, 3, 3);
        
        this.add3x3GridSlots();
        this.addStandardInventorySlots(inventory, INVENTORY_START_X, INVENTORY_START_Y);
        this.addDataSlots(data);
    }
    
    private void add3x3GridSlots() {
        for (int y = 0; y < SLOTS_ROWS; y++) {
            for (int x = 0; x < SLOTS_COLUMNS; x++) {
                final int slot = x + y*SLOTS_COLUMNS;
                this.addSlot(new Slot(
                    forgeSlots,
                    slot,
                    CONTAINER_START_X + x*SLOT_SIZE,
                    CONTAINER_START_Y + y*SLOT_SIZE
                ));
            }
        }
    }
    
    @Override
    public @NonNull ItemStack quickMoveStack(Player player, int slotIndex) {
        Slot slot = this.slots.get(slotIndex);
        
        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }
        
        ItemStack stack = slot.getItem();
        ItemStack clicked = stack.copy();
        
        if (slotIndex < CONTAINER_END) {
            if (!this.moveItemStackTo(stack, INVENTORY_START, INVENTORY_END, true)) {
                return ItemStack.EMPTY;
            }
        }
        else {
            if (!this.moveItemStackTo(stack, CONTAINER_START, CONTAINER_END, false)) {
                return ItemStack.EMPTY;
            }
        }
        
        if (stack.isEmpty())
            slot.setByPlayer(ItemStack.EMPTY);
        else
            slot.setChanged();
        
        return clicked;
    }
    
    @Override
    public void removed(Player player) {
        super.removed(player);
        this.access.execute((level, pos) -> {
            this.clearContainer(player, this.forgeSlots);
            this.clearContainer(player, this.resultSlots);
        });
    }
    
    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, Blocks.FORGE);
    }
    
    public void updateFuel() {
        this.access.execute((level, pos) -> {
            var state = level.getBlockState(pos);
            if (!state.is(Blocks.FORGE)) {
                this.data.set(0, 0);
                return;
            }
            var furnaces = Blocks.FORGE.getBlastFurnaces(level, pos, state);
            if (furnaces.isEmpty()) {
                this.data.set(0, 0);
                return;
            }
            
            boolean a_is_lit = level.getBlockState(furnaces.get().getKey()).getValue(BlastFurnaceBlock.LIT);
            boolean b_is_lit = level.getBlockState(furnaces.get().getValue()).getValue(BlastFurnaceBlock.LIT);
            
            int heat = 0;
            if (a_is_lit)
                heat += 1;
            if (b_is_lit)
                heat += 1;
            
            this.data.set(0, heat);
        });
    }
    
    public int getHeat() {
        return this.data.get(0);
    }
    
    public void attemptClientForge() {
        ServerboundForgeToolPayload payload = new ServerboundForgeToolPayload(this.containerId);
        ClientPlayNetworking.send(payload);
    }
    
    
    public boolean attemptForge(ServerLevel level) {
        if (this.getHeat() != 2) return false;
        
        var template_slot = this.slots.get(CONTAINER_START + 4);
        var head_slot = this.slots.get(CONTAINER_START + 1);
        var rod_slot = this.slots.get(CONTAINER_START + 3);
        var trim_slot = this.slots.get(CONTAINER_START + 7);
        
        var result_slot = this.slots.get(CONTAINER_START + 5);
        
        Function<Slot, Boolean> conflict_test = slot -> {
            var materials = MaterialBehavior.fromItem(slot.getItem().getItem());
            try {
                if (materials.size() > 1)
                    throw new IllegalStateException(
                        "Unable to forge: conflicting items for materials: '" + materials + "'!");
                else
                    return true;
            } catch (IllegalStateException ise) {
                ModularTools.LOGGER.error(ise.getMessage());
                return false;
            }
        };
        
        if (!conflict_test.apply(head_slot) || !conflict_test.apply(rod_slot) || !conflict_test.apply(trim_slot))
            return false;
        
        var head_material = MaterialBehavior.fromItem(head_slot.getItem().getItem()).stream().findFirst().orElse(null);
        var rod_material = MaterialBehavior.fromItem(rod_slot.getItem().getItem()).stream().findFirst().orElse(null);
        var trim_material = MaterialBehavior.fromItem(trim_slot.getItem().getItem()).stream().findFirst().orElse(null);
        
        if (head_material == null || rod_material == null || trim_material == null) return false;
        
        if (!template_slot.hasItem()) return false;
        
        var tool_template = template_slot.getItem().getItem();
        if (!(tool_template instanceof ToolTemplateItem)) return false;
        
        if (result_slot.hasItem()) return false;
        
        var result = ((ToolTemplateItem) tool_template).modularTool.getDefaultInstance();
        result.set(MTDataComponents.MODULAR_TOOL_HEAD, head_material.key);
        result.set(MTDataComponents.MODULAR_TOOL_ROD, rod_material.key);
        result.set(MTDataComponents.MODULAR_TOOL_TRIM, trim_material.key);
        head_slot.remove(1);
        rod_slot.remove(1);
        trim_slot.remove(1);
        result_slot.set(result);
        this.broadcastChanges();
        return true;
    }
}
