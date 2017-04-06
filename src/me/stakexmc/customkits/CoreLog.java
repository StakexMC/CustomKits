package me.stakexmc.customkits;

import java.util.logging.Level;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoreLog {
private CustomKits plugin;
	
	public CoreLog(CustomKits p){
		
		this.plugin = p; 
		
	}
	
	public void info(String info)
	  {
		plugin.getServer().getConsoleSender().sendMessage(CoreUtils.rColor("&b[&7&lCustomKits&b]&7 " + info + "&7"));
	  }
	public void line(String info)
	  {
		plugin.getServer().getConsoleSender().sendMessage(CoreUtils.rColor("&7" + info + "&7"));
	  }
	
	public void error(String info)
	  {
		plugin.getServer().getLogger().log(Level.SEVERE, info);
	  }
	
	public void Message(Player p, String Message){
		p.sendMessage(CoreUtils.rColor("&b[&7&lCustomKits&b]&7 " + Message));
	}
}
