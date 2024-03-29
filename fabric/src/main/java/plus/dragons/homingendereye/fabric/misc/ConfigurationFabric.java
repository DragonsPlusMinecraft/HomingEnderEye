package plus.dragons.homingendereye.fabric.misc;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "homing_ender_eye")
public class ConfigurationFabric implements ConfigData {
    @Comment("Should Ender Eye Destroy Event Only Belongs to Specific Player?")
    public boolean INDIVIDUAL_MODE = false;
    @Comment("Scanning interval for nearby End Portal Frame. The unit is tick.")
    public int SCANNING_RATE = 300;
    @Comment("Scanning Radius for nearby End Portal Frame.")
    public int SCANNING_RADIUS = 30;
    @Comment("The Probability of broken ender eye warping. From minimum 0 to Maximum 100.")
    @ConfigEntry.BoundedDiscrete(max=100)
    public int WARPING_PROBABILITY = 100;

    public static ConfigurationFabric getRealTimeConfig(){
        return AutoConfig.getConfigHolder(ConfigurationFabric.class).getConfig();
    }
}
