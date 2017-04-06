package me.stakexmc.customkits;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class CoreConfig {
	public FileConfiguration config;
	public File file;
	public String s;
	private CustomKits plugin;
	
	public CoreConfig(CustomKits plugin,String configfile){
		this.plugin = plugin;
		config = new YamlConfiguration();
		file = new File(plugin.getDataFolder(),configfile + ".yml");
		s = configfile;
		
	}
	
	public boolean Exists(){
		if(file.exists()){
			return true;
		}else{
			return false;
		} 
	}
	
	public void create(){
		plugin.getLog().info("The "+s+".yml file does not exist yet.");
		plugin.getLog().info("Creating and loading file "+s+".yml.");
		config.options().copyDefaults(true);
		try {
			config.save(file);
			plugin.getLog().info(s+".yml  create.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			plugin.getLog().error("Error on loaded "+s+".yml.");
		}
	}
	
	public void save(){
		
		try {
			config.save(file);
			plugin.getLog().info(s+".yml  save.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			plugin.getLog().error("Error on save "+s+".yml.");
		}
	}
	
	public void load(){
		plugin.getLog().info("Load "+s+".yml");
		try {
			config.load(file);
			config.options().copyDefaults(true);
			config.save(file);
			plugin.getLog().info(s+".yml loaded.");
		} catch (IOException | InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			plugin.getLog().error("Error on loaded "+s+".yml.");
		}
		
	}
	
	public void add(String path,String value){
		config.addDefault(path,value);
	}
	
	public void add(String path,long value){
		config.addDefault(path,value);
	}
	public void add(String path,double value){
		config.addDefault(path,value);
	}
	
	public void add(String path,boolean value){
		config.addDefault(path,value);
	}
	
	public void add(String path,List<String> value){
		config.addDefault(path,value);
	}
	
	public void add(String path,int value){
		config.addDefault(path,value);
	}
	
	public boolean getBoolean(String path){
		return config.getBoolean(path);
	}
	
	public String getString(String path){
		return config.getString(path);
		
	}
	
	public int getInt(String path){
		return config.getInt(path);
	}
	public List<String> getStringList(String path){
		return config.getStringList(path);
	}
	
	public ConfigurationSection getConfigurationSection(String path){
		return config.getConfigurationSection(path);
	}
	
	public boolean isSet(String path){
		return config.isSet(path);
	}
	

	public void set(String path,String value){
		 config.set(path, value);
	}
	
	public void set(String path,int value){
		 config.set(path, value);
	}
	public void set(String path,double value){
		 config.set(path, value);
	}
	
	public void set(String path,boolean value){
		 config.set(path, value);
	}
	
	public Set<String> getKeys(){
		 return config.getKeys(false);
	}
	
	
	public boolean contains(String path){
		 return config.contains(path);
	}
}
