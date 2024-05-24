
package com.brodi.welcomer;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {
    public Main() {
    }





    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        String title = getConfig().getString("welcome");
        String subtitle = getConfig().getString("welcome-subtitle");
        event.getPlayer().sendTitle(title, subtitle, 20, 200, 20);
    }
}
