package plus.dragons.homingendereye.fabric.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.common.SyncedClientOptions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import plus.dragons.homingendereye.misc.ScanningForEndPortal;

@Mixin(ServerPlayerEntity.class)
public abstract class MixinServerPlayerEntity extends PlayerEntity {

    @Shadow public abstract ServerWorld getWorld();

    public MixinServerPlayerEntity(MinecraftServer server, ServerWorld world, GameProfile profile, SyncedClientOptions clientOptions) {
        super(world, profile);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void init(CallbackInfo info) {
        ScanningForEndPortal.scan((ServerPlayerEntity) (Object)this,getWorld());
    }
}
