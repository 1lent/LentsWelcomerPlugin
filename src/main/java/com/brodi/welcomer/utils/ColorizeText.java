package com.brodi.welcomer.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ColorizeText {

    /**
     * This method formats color codes in a given message string.
     * Color codes are represented using '&' followed by hexadecimal color codes or RGB values.
     *
     * @param message the input message containing color codes
     * @return the formatted message with color codes replaced by their respective colors
     */
    public String formatColors(String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);
        Pattern pattern = Pattern.compile("&#([A-Fa-f0-9]{6})");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String colorCode = matcher.group();
            ChatColor color = ChatColor.of(colorCode.substring(1));
            message = message.replace(colorCode, color.toString());
        }
        return message;
    }
}
