package plus.dragons.homingendereye.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import plus.dragons.homingendereye.HomingEnderEye;
import plus.dragons.homingendereye.neoforge.misc.ConfigurationNeoForge;
import plus.dragons.homingendereye.neoforge.misc.POITypeRegistry;

@Mod(HomingEnderEye.MOD_ID)
public class HomingEnderEyeNeoForge {

    public HomingEnderEyeNeoForge(IEventBus modEventbus, ModContainer container) {
        POITypeRegistry.POI.register(modEventbus);
        container.registerConfig(ModConfig.Type.SERVER, ConfigurationNeoForge.MOD_CONFIG);
    }
}
