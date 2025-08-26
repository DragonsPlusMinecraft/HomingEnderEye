package plus.dragons.homingendereye.fabric.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Set;

@Mixin(PointOfInterestTypes.class)
public interface PointOfInterestTypesInvoker {
    @Invoker("registerStates")
    public static void invokeRegisterStates(RegistryEntry<PointOfInterestType> poiTypeEntry, Set<BlockState> states) {
        throw new AssertionError();
    }
}
