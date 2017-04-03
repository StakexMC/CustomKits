package me.stakexmc.customkits;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomKits extends JavaPlugin {
	
	private CoreLog log;
	public CoreConfig cconfig;
	//para cada archivo una nueva clase CoreConfig prro
	public CoreConfig ckits;
	public static CustomKits intance;
	
	public void onEnable(){
		this.log = new CoreLog(this);
		intance = this;
		log.line("--------------------------------");
		log.info("Hola bebe");
		log.info("Pruebaaaaaaaaaaaa");
		iniConfig();
		iniKits();
		log.line("--------------------------------");
	}
	   
	//iniciamos el archivo config
		public void iniConfig(){
			cconfig = new CoreConfig(intance,"config");
			if (cconfig.Exists()){
				configDefault();
				cconfig.load();
			}else{
				configDefault();
				cconfig.create();
			}
			
		}public void iniKits(){
			ckits = new CoreConfig(intance,"Kits");
			if (ckits.Exists()){
				configKits();
				ckits.load();
			}else{
				configKits();
				ckits.create();
			}
			
		}
		
		//configuracion por defeto del plugin
		public void configDefault(){	
			cconfig.add("messages","ES");
			
			
			cconfig.add("dataStorage.type","YAML");
			cconfig.add("dataStorage.host","localhost");
			cconfig.add("dataStorage.port",3306);
			cconfig.add("dataStorage.database","minecraft");
			cconfig.add("dataStorage.username","root");
			cconfig.add("dataStorage.password","1234");
		}
		//Configuracion default para el archivo Kits
		//Trata de no usar mayuscula en los nodos internos
		public void configKits(){	
			ckits.add("Survival.name","&7Survival");
			ckits.add("Survival.permission","kit.survival");
			//opcional si se quiere poner un precio a los kits
			ckits.add("Survival.value",100);
			ckits.add("Survival.icon","GRASS");
			ckits.add("Survival.description",Arrays.asList(
					"Un azombroso kit para survival",
					"Esto es una jnueva linea"));
			ckits.add("Survival.items",Arrays.asList(
					"DIAMOND_HELMET: 1",
					"DIAMOND_CHESTPLATE: 1, DURABILITY:5",
					"DIAMOND_LEGGINGS: 1",
					"IRON_BOOTS: 1",
					"LAVA_BUCKET: 1",
					"STAINED_GLASS_PANE: 15 ,32"));
			//Explicacion
			// "<material>|<data> , <cantida> , <encantamiento>|<nivel>"
			
		}
	
	public CoreLog getLog(){
		return this.log;
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String Stringlabel, String [] args){
    	if(!(sender instanceof Player)){
    		log.info("&aCommand don't available in Console");
			return true;
		}
    	Player p = (Player)sender;	
    	if(cmd.getName().equalsIgnoreCase("CustomKits")){
    		if(args.length >= 0){
    			log.Message(p, "Command dont exist");
    		}else{
    			
    		}
    		
    	}
    	return true;
	}
	
}
