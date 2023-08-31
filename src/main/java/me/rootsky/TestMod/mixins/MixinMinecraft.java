package me.rootsky.TestMod.mixins;

import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    @Shadow
    @Final
    private static Logger logger;

    @Inject(method="startGame", at = @At("HEAD"))
    public void startGame(final CallbackInfo ci) {
        logger.info("[TestMod] Starting game 2");
    }
}
