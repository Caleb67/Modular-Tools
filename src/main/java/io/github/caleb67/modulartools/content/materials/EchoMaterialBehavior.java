package io.github.caleb67.modulartools.content.materials;

import com.mojang.math.Transformation;
import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.datagen.TranslationUtil;
import io.github.caleb67.modulartools.register.MaterialBehaviors;
import io.github.caleb67.modulartools.tool.AbstractModularToolItem;
import io.github.caleb67.modulartools.tool.InventoryTickContext;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import io.github.caleb67.modulartools.tool.tooltip.MaterialEffectTooltipOperation;
import io.github.caleb67.modulartools.util.MethodChain;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLevelEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Display.BlockDisplay;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.awt.*;
import java.util.*;
import java.util.List;


public class EchoMaterialBehavior extends MaterialBehavior {

    private final HashMap<Entity, HashMap<BlockPos, BlockDisplay>> active;
    public static Component ORE_HIGHLIGHT_BLOCK_DISPLAY_NAME = Component.literal(
            Identifier.fromNamespaceAndPath(ModularTools.MODID, "ore_highlight_block_display").toString()
    );
    public Colors ore_colors = new Colors() {
        private final HashMap<ResourceKey<Block>, Color> colors = new HashMap<>();
        @Override public Color get(BlockState state) {
            return colors.getOrDefault(state.getBlock().properties().blockId(), Color.WHITE);
        }
        @Override public void set(Color color, Block... blocks) {
            for (var block : blocks)
                colors.put(block.properties().blockId(), color);
        }

        @Override
        public boolean isEmpty() {return colors.isEmpty();}
    };

    public static final ServerTickEvents.EndTick ORE_SIGHT_BEHAVIOR_CLEAR_DISPLAYS = minecraftServer -> {
        minecraftServer.getPlayerList().getPlayers().forEach(serverPlayer -> {
            var this_material = MaterialBehaviors
                    .ECHO_MATERIAL_BEHAVIOR;
            if (!this_material.active.containsKey(serverPlayer))
                this_material.active.put(serverPlayer, new HashMap<>());
            for (var entry: this_material.active.get(serverPlayer).entrySet()) {
                var display = entry.getValue();
                var level = (ServerLevel) display.level();
                var test_pos = display.position().subtract(0.0001);
                var test_block = display.getBlockState().getBlock();
                if (!display.closerThan(serverPlayer, 5.0) && entry.getValue() != null)
                    new MethodChain<>(entry)
                        .and(Map.Entry::getValue, e -> e.kill(level))
                        .and(Map.Entry::setValue, (BlockDisplay) null);
                else if (!level.getBlockState(BlockPos.containing(test_pos)).is(test_block))
                    new MethodChain<>(entry)
                        .and(Map.Entry::getValue, e -> e.kill(level))
                        .and(Map.Entry::setValue, (BlockDisplay) null);
            }
            this_material.active.get(serverPlayer).values().removeIf(Objects::isNull);
        });
    };

    public static final ServerEntityEvents.Load ORE_SIGHT_BEHAVIOR_PURGE_DISPLAYS = (entity, level) -> {
        if (entity instanceof BlockDisplay bd) {
            var name = bd.getCustomName();
            if (name == null) return;
            if (!name.getString().equals(ORE_HIGHLIGHT_BLOCK_DISPLAY_NAME.getString())) return;
            if (MaterialBehaviors.ECHO_MATERIAL_BEHAVIOR.active.entrySet()
                    .stream().anyMatch(entry -> entry.getValue().containsValue(bd))) return;
            entity.kill(level);
        }
    };

    public static final ServerLevelEvents.Load ORE_SIGHT_BEHAVIOR_LOAD_LEVEL =
        (_, _) -> MaterialBehaviors.ECHO_MATERIAL_BEHAVIOR.active.clear();

    public EchoMaterialBehavior(Properties properties) {
        super(properties);
        this.active = new HashMap<>();
    }

    @Override
    public Optional<MaterialEffectTooltipOperation> getEffectTooltip(ItemStack itemStack, int numTimes) {
        return Optional.of((executor, context,
                            display, builder, tooltipFlag) -> {
            builder.accept(
                    Component.translatable(TranslationUtil.makeEffectDescId(this.key, numTimes))
                            .withStyle(this.getEffectFormatting())
            );
        });
    }

    private void addColors() {
        this.ore_colors.set(
                Color.BLACK, BuiltInRegistries.BLOCK.getOrThrow(ConventionalBlockTags.COAL_ORES)
                        .stream().map(Holder::value).toArray(Block[]::new));
        this.ore_colors.set(
                new Color(254, 222, 200), BuiltInRegistries.BLOCK.getOrThrow(ConventionalBlockTags.IRON_ORES)
                        .stream().map(Holder::value).toArray(Block[]::new));
        this.ore_colors.set(
                Color.CYAN, BuiltInRegistries.BLOCK.getOrThrow(ConventionalBlockTags.DIAMOND_ORES)
                        .stream().map(Holder::value).toArray(Block[]::new));
        this.ore_colors.set(
                Color.BLUE, BuiltInRegistries.BLOCK.getOrThrow(ConventionalBlockTags.LAPIS_ORES)
                        .stream().map(Holder::value).toArray(Block[]::new));
        this.ore_colors.set(
                Color.RED, BuiltInRegistries.BLOCK.getOrThrow(ConventionalBlockTags.REDSTONE_ORES)
                        .stream().map(Holder::value).toArray(Block[]::new));
        this.ore_colors.set(
                Color.YELLOW, BuiltInRegistries.BLOCK.getOrThrow(ConventionalBlockTags.GOLD_ORES)
                        .stream().map(Holder::value).toArray(Block[]::new));
        this.ore_colors.set(
                Color.GREEN, BuiltInRegistries.BLOCK.getOrThrow(ConventionalBlockTags.EMERALD_ORES)
                        .stream().map(Holder::value).toArray(Block[]::new));
        this.ore_colors.set(
                Color.ORANGE, BuiltInRegistries.BLOCK.getOrThrow(ConventionalBlockTags.COPPER_ORES)
                        .stream().map(Holder::value).toArray(Block[]::new));
        this.ore_colors.set(
                new Color(192, 0, 255), BuiltInRegistries.BLOCK.getOrThrow(ConventionalBlockTags.NETHERITE_SCRAP_ORES)
                        .stream().map(Holder::value).toArray(Block[]::new));

    }

    @Override
    public void inventoryTick(InventoryTickContext context, ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        if (context.hasSeen(this.key)) return;
        if (slot != EquipmentSlot.MAINHAND && slot != EquipmentSlot.OFFHAND) return;

        if (!this.active.containsKey(owner)) this.active.put(owner, new HashMap<>());
        if (this.ore_colors.isEmpty()) this.addColors();

        var center = owner.getOnPos();

        getPositions(center).forEach(pos -> {
            var state = level.getBlockState(pos);
            if (state.is(ConventionalBlockTags.ORES) && !this.active.get(owner).containsKey(pos)) {
                var display = EntityType.BLOCK_DISPLAY.create(level, EntitySpawnReason.TRIGGERED);

                if (display == null) return;

                new MethodChain<>(display)
                    .and(BlockDisplay::setBlockState, state)
                    .and(BlockDisplay::setTransformation,
                        new Transformation(
                            null, null,
                            new Vector3f(0.999F, 0.999F, 0.999F),
                            null)
                    )
                    .and(BlockDisplay::setPos,
                        new Vec3(pos.getX(), pos.getY(), pos.getZ()).add(0.0001)
                    )
                    .and(BlockDisplay::setXRot, 0.0F)
                    .and(BlockDisplay::setYRot, 0.0F)
                    .and(BlockDisplay::setGlowingTag, true)
                    .and(BlockDisplay::setGlowColorOverride, ore_colors.get(state).getRGB())
                    .and(BlockDisplay::setCustomNameVisible, false)
                    .and(BlockDisplay::setCustomName, ORE_HIGHLIGHT_BLOCK_DISPLAY_NAME);

                this.active.get(owner).put(pos, display);
                level.addFreshEntity(display);
                AbstractModularToolItem.hurtAndBreakTool(itemStack, 1, (LivingEntity) owner, EquipmentSlot.MAINHAND);
            }
        });
    }

    public interface Colors {
        Color get(BlockState state);
        void set(Color color, Block... blocks);
        boolean isEmpty();
    }

    private static List<BlockPos> getPositions(BlockPos center) {
        ArrayList<BlockPos> positions = new ArrayList<>();
        positions.add(center);
        for (int i = -2; i <= 2 ; i++) {
            for (int j = -1; j <= 4; j++) {
                for (int k = -2; k <= 2; k++) {
                    positions.add(center.offset(i, j, k));
                }
            }
        }
        return positions;
    }
}
