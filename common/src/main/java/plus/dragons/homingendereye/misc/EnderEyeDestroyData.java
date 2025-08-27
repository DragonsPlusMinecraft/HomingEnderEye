package plus.dragons.homingendereye.misc;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.datafixer.DataFixTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.PersistentStateType;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import plus.dragons.homingendereye.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

public class EnderEyeDestroyData extends PersistentState {
    private final static Function<PersistentState.Context,Codec<EnderEyeDestroyData>> CODEC = ctx -> RecordCodecBuilder.create(instance -> instance.group(
            RecordCodecBuilder.point(ctx),
            Codec.BOOL.fieldOf("shared").forGetter(data -> data.shared),
            Codec.INT.fieldOf("count").forGetter(data -> data.count),
            Codec.unboundedMap(Codec.stringResolver(UUID::toString,UUID::fromString), Codec.INT).fieldOf("countMap").forGetter(data -> data.countMap)
    ).apply(instance, EnderEyeDestroyData::new));

    public final static PersistentStateType<EnderEyeDestroyData> TYPE =
            new PersistentStateType<>("ender_eye_destroy",
                    EnderEyeDestroyData::new,
                    CODEC, DataFixTypes.LEVEL);

    private final boolean shared;
    private int count;
    private final Map<UUID,Integer> countMap;

    public EnderEyeDestroyData(PersistentState.Context ctx) {
        shared = !Configuration.isIndividualMode();
        count = 0;
        countMap = new HashMap<>();
    }

    public EnderEyeDestroyData(PersistentState.Context ctx, boolean shared, int count, Map<UUID,Integer> countMap) {
        this.shared = shared;
        this.count = count;
        this.countMap = countMap;
    }

    public static EnderEyeDestroyData get(World world){
        if (!(world instanceof ServerWorld)) {
            throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
        }

        ServerWorld serverWorld = world.getServer().getOverworld();
        PersistentStateManager manager = serverWorld.getPersistentStateManager();
        return manager.getOrCreate(TYPE);
    }

    public int getCount(@Nullable UUID uuid) {
        if(shared){
            return count;
        } else {
            return uuid == null?0:countMap.getOrDefault(uuid,0);
        }
    }

    public void setCount(@Nullable UUID uuid, int count) {
        count = Math.max(count, 0);
        if(shared) {
            this.count = count;
            markDirty();
        }
        else{
            if(uuid!=null){
                countMap.put(uuid,count);
                markDirty();
            }
        }
    }

    public void increaseCount(@Nullable UUID uuid){
        if(shared) {
            count += 1;
            markDirty();
        }
        else{
            if(uuid!=null){
                if(countMap.containsKey(uuid)){
                    countMap.put(uuid,countMap.get(uuid)+1);
                } else {
                    countMap.put(uuid,1);
                }
                markDirty();
            }
        }
    }

    public void decreaseCount(@Nullable UUID uuid){
        if(shared) {
            count = Math.max(0,count - 1);
            markDirty();
        }
        else{
            if(uuid!=null){
                countMap.put(uuid,Math.max(0,countMap.get(uuid)-1));
                markDirty();
            }
        }
    }

}
