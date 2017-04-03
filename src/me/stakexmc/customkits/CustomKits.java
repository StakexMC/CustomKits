package me.stakexmc.customkits;

import org.bukkit.plugin.java.JavaPlugin;

public class CustomKits extends JavaPlugin {
	
	private CoreLog log;
	public CoreConfig cconfig;
	public static CustomKits intance;
	
	public void onEnable(){
		this.log = new CoreLog(this);
		log.line("--------------------------------");
		log.info("Hola bebe");
		log.info("Pruebaaaaaaaaaaaa");
		iniConfig();
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
	
	public CoreLog getLog(){
		return this.log;
	}
}
