package plus.dragons.homingendereye.neoforge.misc;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ConfigurationNeoForge {
    public static final ModConfigSpec MOD_CONFIG;

    public static ModConfigSpec.BooleanValue INDIVIDUAL_MODE;
    public static ModConfigSpec.DoubleValue WARPING_PROBABILITY;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.push("general");
        INDIVIDUAL_MODE = builder.comment("Should Ender Eye Destroy Event Only Belongs to Specific Player?").define("INDIVIDUAL_MODE", false);
        WARPING_PROBABILITY = builder.comment("The Probability of broken ender eye warping").defineInRange("WARPING_PROBABILITY", 1.0, 0, 1.0);
        builder.pop();

        MOD_CONFIG = builder.build();
    }
}
