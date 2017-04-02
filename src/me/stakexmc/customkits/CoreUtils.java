package me.stakexmc.customkits;

import java.util.List;

import org.bukkit.ChatColor;

public class CoreUtils {
	public static String rColor(String nonColoredText) {
        String coloredText = ChatColor.translateAlternateColorCodes('&', nonColoredText);
        return coloredText;
    }
	
	public static boolean isint(String s){
		try{
			@SuppressWarnings("unused")
			int i = Integer.parseInt(s);
			return true;
		}
		catch(NumberFormatException er){
			return false;
		}
		
	}
	
	public static int toint(String s){
		try{
			int i = Integer.parseInt(s);
			return i;
		}
		catch(NumberFormatException er){
			return 0;
		}
		
	}

	//Da Formato de colores a los Textos
	public static List<String> rColorList(List<String> s) {
		
		for (int i = 0; i < s.size(); i++) {
             String p = ChatColor.translateAlternateColorCodes('&', s.get(i));
             s.set(i, p);
        }
        return s;
	}
}
