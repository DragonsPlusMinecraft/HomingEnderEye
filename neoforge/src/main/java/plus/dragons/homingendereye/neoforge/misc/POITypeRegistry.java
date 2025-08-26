package plus.dragons.homingendereye.neoforge.misc;

import net.minecraft.block.Blocks;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.poi.PointOfInterestType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import plus.dragons.homingendereye.HomingEnderEye;

import java.util.stream.Collectors;

public class POITypeRegistry {
    public static final DeferredRegister<PointOfInterestType> POI = DeferredRegister.create(RegistryKeys.POINT_OF_INTEREST_TYPE, HomingEnderEye.MOD_ID);
    public static final DeferredHolder<PointOfInterestType,PointOfInterestType> UNFILLED_END_PORTAL_FRAME = POI.register(
            "unfilled_end_portal_frame",
            () -> new PointOfInterestType(Blocks.END_PORTAL_FRAME.getStateManager().getStates().stream().filter(blockState->
                    blockState.get(EndPortalFrameBlock.EYE)==false).collect(Collectors.toSet()), 1, 1));
}
