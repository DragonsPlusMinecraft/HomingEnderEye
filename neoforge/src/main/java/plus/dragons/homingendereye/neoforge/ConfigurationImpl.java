package plus.dragons.homingendereye.neoforge;

import plus.dragons.homingendereye.neoforge.misc.ConfigurationNeoForge;

public class ConfigurationImpl {
    public static boolean isIndividualMode() {
        return ConfigurationNeoForge.INDIVIDUAL_MODE.get();
    }

    public static double getWarpingProbability() {
        return ConfigurationNeoForge.WARPING_PROBABILITY.get();
    }
}
