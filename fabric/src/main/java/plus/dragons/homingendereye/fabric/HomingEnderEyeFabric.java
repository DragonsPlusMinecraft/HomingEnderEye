package plus.dragons.homingendereye.fabric;

import com.google.common.collect.ImmutableSet;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.block.Blocks;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;
import plus.dragons.homingendereye.HomingEnderEye;
import net.fabricmc.api.ModInitializer;
import plus.dragons.homingendereye.fabric.misc.ConfigurationFabric;
import plus.dragons.homingendereye.misc.EyeThrowCache;
import plus.dragons.homingendereye.fabric.mixin.PointOfInterestTypesInvoker;

import java.util.stream.Collectors;

public class HomingEnderEyeFabric implements ModInitializer {

    public static PointOfInterestType UNFILLED_END_PORTAL_FRAME;

    public static RegistryKey<PointOfInterestType> UNFILLED_END_PORTAL_FRAME_KEY;

    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTING.register((server -> {
            if(HomingEnderEye.EYE_THROW_CACHE == null){
                HomingEnderEye.EYE_THROW_CACHE = new EyeThrowCache();
            }
        }));

        UNFILLED_END_PORTAL_FRAME = registerPOI();

        AutoConfig.register(ConfigurationFabric.class, JanksonConfigSerializer::new);
    }

    public static PointOfInterestType registerPOI() {
        UNFILLED_END_PORTAL_FRAME_KEY = RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, Identifier.of(HomingEnderEye.MOD_ID, "unfilled_end_portal_frame"));
        var stats = ImmutableSet.copyOf(Blocks.END_PORTAL_FRAME.getStateManager().getStates()).stream().filter(blockState->
                blockState.get(EndPortalFrameBlock.EYE)==false).collect(Collectors.toSet());
        var ret = Registry.register(Registries.POINT_OF_INTEREST_TYPE, UNFILLED_END_PORTAL_FRAME_KEY,
                new PointOfInterestType(stats, 1, 1));
        PointOfInterestTypesInvoker.invokeRegisterStates(Registries.POINT_OF_INTEREST_TYPE.getEntry(ret),stats);
        return ret;
    }
}
