package io.github.caleb67.modulartools.client;

import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.content.blocks.ForgeMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import java.awt.*;

public class ForgeScreen extends AbstractContainerScreen<ForgeMenu> {
    private static final Identifier CONTAINER_TEXTURE = Identifier.fromNamespaceAndPath(
            ModularTools.MODID, "textures/gui/forge.png"
    );
    private static final int SLOT_SIZE = 18;

    public ForgeScreen(ForgeMenu abstractContainerMenu, Inventory inventory, Component component) {
        super(abstractContainerMenu, inventory, component);
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
        this.inventoryLabelY -= 6;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractBackground(graphics, mouseX, mouseY, delta);
        graphics.blit(
                RenderPipelines.GUI_TEXTURED, CONTAINER_TEXTURE,
                this.leftPos, this.topPos, 0.0F, 0.0F,
                this.imageWidth, this.imageHeight,
                BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT);
    }

    protected void extractLabels(final GuiGraphicsExtractor graphics, final int xm, final int ym) {
        graphics.text(this.font, this.title, this.titleLabelX, this.titleLabelY, Color.WHITE.getRGB(), false);
        graphics.text(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, Color.WHITE.getRGB(), false);
    }

    @Override
    protected void init() {
        super.init();
        var position = this.getHammerButtonPosition();
        this.addRenderableWidget(
                new ImageButton(position.x(), position.y() - SLOT_SIZE, 20, 18,
                        RecipeBookComponent.RECIPE_BUTTON_SPRITES,
                        button -> this.onHammerButtonClick()));
    }

    protected ScreenPosition getHammerButtonPosition() {
        return new ScreenPosition(this.leftPos + 15 + (SLOT_SIZE*6), (this.height / 2) - 49 + (SLOT_SIZE*2));
    }

    private void onHammerButtonClick() {
        this.menu.attemptClientForge();
    }
}
