package com.zeeyeh.mctoosimple.mixin;

import com.zeeyeh.mctoosimple.client.ThirstData;
import com.zeeyeh.mctoosimple.config.ThirstConfig;
import com.zeeyeh.mctoosimple.networking.MTSNetworking;
import com.zeeyeh.mctoosimple.networking.packet.ThirstDataS2CPacket;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Leon_Keiran
 * @description 水瓶注入
 * @date 2024/6/27 下午1:18
 * @github <a href="https://github.com/bmlingqi">https://github.com/bmlingqi</a>
 */
@Mixin(PotionItem.class)
public class MTSPotionItem {

    @Inject(method = "finishUsingItem", at = @At("HEAD"))
    public void onFinishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, CallbackInfoReturnable<ItemStack> cir) {
        int thirst = ThirstData.getThirst();
        System.out.println("喝完水瓶");
        thirst += ThirstConfig.DRINK_POTION_VALUE.get();
        MTSNetworking.sendToServer(MTSNetworking.THIRST_CHANNEL, new ThirstDataS2CPacket(thirst));
    }
}
