package plus.dragons.homingendereye.neoforge;

import net.minecraft.registry.RegistryKey;
import net.minecraft.world.poi.PointOfInterestType;
import plus.dragons.homingendereye.neoforge.misc.POITypeRegistry;

public class HomingEnderEyeImpl {
    public static RegistryKey<PointOfInterestType> UnfilledEndPortalFramePoi() {
        return POITypeRegistry.UNFILLED_END_PORTAL_FRAME.getDelegate().getKey().get();
    }
}
