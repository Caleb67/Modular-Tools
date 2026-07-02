package io.github.caleb67.modulartools.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;

@Environment(EnvType.CLIENT)
public class ForgeHammerButton extends ImageButton {
    private final OnRelease onRelease;
    private boolean being_pressed;
    
    public ForgeHammerButton(int x, int y, int width, int height, WidgetSprites sprites, OnPress onPress, OnRelease onRelease) {
        super(x, y, width, height, sprites, onPress);
        this.onRelease = onRelease;
        this.being_pressed = false;
    }
    
    @Override
    public void onRelease(MouseButtonEvent event) {
        this.onRelease.onRelease(this);
        this.being_pressed = false;
    }
    
    @Override
    public void onPress(InputWithModifiers input) {
        super.onPress(input);
        this.being_pressed = true;
    }
    
    @Override
    public void extractContents(final GuiGraphicsExtractor graphics, final int mouseX, final int mouseY, final float a) {
        Identifier sprite = this.sprites.get(this.being_pressed, this.isHovered());
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, sprite, this.getX(), this.getY(), this.width, this.height);
    }
    
    @Override
    public void playDownSound(SoundManager soundManager) {}
    
    ;
    
    public static void playHammeringSound(SoundManager soundManager) {
        soundManager.play(SimpleSoundInstance.forUI(SoundEvents.ANVIL_USE, 0.8F, 0.5F));
    }
    
    public static void playFailHammeringSound(SoundManager soundManager) {
        soundManager.play(SimpleSoundInstance.forUI(SoundEvents.METAL_PLACE, 0.0F, 1.0F));
    }
    
    @Environment(EnvType.CLIENT)
    public interface OnRelease {
        void onRelease(final Button button);
    }
}
