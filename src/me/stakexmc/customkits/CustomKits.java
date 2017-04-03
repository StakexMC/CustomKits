package me.stakexmc.customkits;

import org.bukkit.plugin.java.JavaPlugin;

public class CustomKits extends JavaPlugin {
	
	private CoreLog log;
	
	public void onEnable(){
		this.log = new CoreLog(this);
		log.line("--------------------------------");
		log.info("Hola bebe");
		log.info("Pruebaaaaaaaaaaaa");
		log.line("--------------------------------");
	}
	
	public CoreLog getLog(){
		return this.log;
	}
}
