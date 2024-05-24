
package com.brodi.welcomer;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.bukkit.ChatColor.COLOR_CHAR;

public final class Main extends JavaPlugin implements Listener {
    public Main() {
    }


    public String translateHexColorCodes(String startTag, String endTag, String message)
    {
        final Pattern hexPattern = Pattern.compile(startTag + "([A-Fa-f0-9]{6})" + endTag);
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find())
        {
            String group = matcher.group(1);
            matcher.appendReplacement(buffer, COLOR_CHAR + "x"
                    + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
                    + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
                    + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
            );
        }
        return matcher.appendTail(buffer).toString();
    }



    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        String color = getConfig().getString("color");
        translateHexColorCodes("#","", color);
        String hex = translateHexColorCodes("#","", color);
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        String color = getConfig().getString("color");
        translateHexColorCodes("#","", color);
        String hex = translateHexColorCodes("#","", color);
        String title = getConfig().getString("welcome");
        String subtitle = getConfig().getString("welcome-subtitle");
        event.getPlayer().sendTitle(hex + title, hex + subtitle, 20, 200, 20);

    }
}
