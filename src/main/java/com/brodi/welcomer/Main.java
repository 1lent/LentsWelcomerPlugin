
package com.brodi.welcomer;

import com.brodi.welcomer.utils.ColorizeText;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    private ColorizeText cc;

    public Main() {}

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        String title = getConfig().getString("welcomer.title");
        String subtitle = getConfig().getString("welcomer.subtitle");
        int fadeIn = getConfig().getInt("welcomer.title-fade-in", 10);
        int stay = getConfig().getInt("welcomer.title-stay", 80);
        int fadeOut = getConfig().getInt("welcomer.title-fade-out", 10);
        String soundName = getConfig().getString("welcomer.sound", "ENTITY_PLAYER_LEVELUP");

        title = cc.formatColors(title);
        subtitle = cc.formatColors(subtitle);
        p.sendTitle(title, subtitle, fadeIn, stay, fadeOut);

        if (getConfig().getBoolean("welcomer.join-sound", true)) {
            Sound sound = Sound.valueOf(soundName);
            p.playSound(p.getLocation(), sound, 1, 1);
        }
    }
}