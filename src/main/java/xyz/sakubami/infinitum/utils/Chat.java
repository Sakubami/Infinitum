package xyz.sakubami.infinitum.utils;

import org.bukkit.ChatColor;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Chat {

    public static String format(String input) {
        return "§7» " + input;
    }

    public static String translate(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static List<String> translate(List<String> input ) {
        List<String> list = new ArrayList<>();
        if ( input != null )
        {
            for ( String line : input ) {
                list.add( ChatColor.translateAlternateColorCodes('&', line) );
            }
        }
        return list;
    }
}