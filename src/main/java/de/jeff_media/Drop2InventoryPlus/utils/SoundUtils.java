package de.jeff_media.Drop2InventoryPlus.utils;

import com.google.common.base.Enums;

import de.jeff_media.Drop2InventoryPlus.Config;
import de.jeff_media.Drop2InventoryPlus.Main;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundUtils {

    private final Main main;
    private Sound sound;
    private boolean soundEnabled;
    private boolean soundGlobal;
    private float soundVolume;
    private float soundPitch;

    public SoundUtils() {
        this.main=Main.getInstance();
        String soundName = main.getConfig().getString(Config.SOUND_EFFECT);
        sound = Enums.getIfPresent(Sound.class,soundName).orNull();
        if(sound==null) {
            main.getLogger().warning("Unknown sound effect: "+soundName);
        }
        soundEnabled = main.getConfig().getBoolean(Config.SOUND_ENABLED);
        soundGlobal = main.getConfig().getBoolean(Config.SOUND_GLOBAL);
        soundVolume = (float) main.getConfig().getDouble(Config.SOUND_VOLUME);
        soundPitch = (float) main.getConfig().getDouble(Config.SOUND_PITCH);

    }

    public void playPickupSound(Player player) {
        if(!soundEnabled) {
            return;
        }
        if(sound==null) return;
        if(soundGlobal) {
            player.getWorld().playSound(player.getLocation(),sound,soundVolume,soundPitch);
        } else {
            player.playSound(player.getLocation(),sound,soundVolume,soundPitch);
        }
    }

}
