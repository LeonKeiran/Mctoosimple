package com.zeeyeh.mctoosimple.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.zeeyeh.mctoosimple.GlobalConfig;
import com.zeeyeh.mctoosimple.client.ThirstData;
import com.zeeyeh.mctoosimple.config.ThirstConfig;
import com.zeeyeh.mctoosimple.entity.PlayerThirst;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.level.GameType;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.OverlayRegistry;

/**
 * @author Leon_Keiran
 * @description 口渴值GUI
 * @date 2024/6/26 下午8:12
 * @github <a href="https://github.com/bmlingqi">https://github.com/bmlingqi</a>
 */
public class ThirstGuiEventHandler {

    public static void register() {
        OverlayRegistry.registerOverlayAbove(ForgeIngameGui.HOTBAR_ELEMENT, "Thirst",
                (ThirstGuiEventHandler::render));
    }

    public static void render(ForgeIngameGui gui, PoseStack matrixStack, float partialTick, int width, int height) {
        gui.setupOverlayRenderState(true, false);
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) {
            return;
        }
        if (minecraft.gameMode != null) {
            GameType playerMode = minecraft.gameMode.getPlayerMode();
            if (playerMode == GameType.CREATIVE) {
                return;
            }
        }
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GlobalConfig.THIRST_ICON_TEXTURE);
        int x;
        int y;
        x = width / 2;
        y = height;
        for (int i = 0; i < 10; i++) {
            drawThirst(minecraft, matrixStack, x, y, 1, 1, i);
        }
        int thirst = ThirstData.getThirst();
        int waterDropCount = 0;
        int fullWaterDrop = thirst / 4;
        int dissatisfiedWaterDrop = thirst % 4;
        if (fullWaterDrop >= 10) {
            fullWaterDrop = 10;
        }
        for (int i = 0; i < fullWaterDrop; i++) {
            waterDropCount++;
            if (waterDropCount <= 10) {
                drawThirst(minecraft, matrixStack, x, y, 33, 1, i);
            }
        }
        if (thirst >= ThirstConfig.MAX_THIRST_VALUE.get()) {
            stopDraw(minecraft);
        }
        if (waterDropCount <= 10) {
            waterDropCount = 0;
            if (dissatisfiedWaterDrop == 3) {
                drawThirst(minecraft, matrixStack, x, y, 25, 1, fullWaterDrop);
            } else if (dissatisfiedWaterDrop == 2) {
                drawThirst(minecraft, matrixStack, x, y, 17, 1, fullWaterDrop);
            } else if (dissatisfiedWaterDrop == 1) {
                drawThirst(minecraft, matrixStack, x, y, 9, 1, fullWaterDrop);
            }
        }
        stopDraw(minecraft);
    }

    private static void drawThirst(Minecraft minecraft, PoseStack matrixStack, int x, int y, int u, int v, int i) {
        GuiComponent.blit(matrixStack,
                x - 90 + i * 8,
                y - 50,
                80,
                u,
                v,
                7,
                10,
                41,
                34);
    }

    public static void stopDraw(Minecraft minecraft) {
        RenderSystem.disableBlend();
        RenderSystem.setShaderTexture(0, GuiComponent.GUI_ICONS_LOCATION);
        minecraft.getProfiler().pop();
    }
}
