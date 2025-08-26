package plus.dragons.homingendereye;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.poi.PointOfInterestType;
import plus.dragons.homingendereye.misc.EyeThrowCache;

public class HomingEnderEye {
    public static final String MOD_ID = "homing_ender_eye";
    public static EyeThrowCache EYE_THROW_CACHE;

    @ExpectPlatform
    public static RegistryKey<PointOfInterestType> UnfilledEndPortalFramePoi(){
        throw new RuntimeException();
    }
}
