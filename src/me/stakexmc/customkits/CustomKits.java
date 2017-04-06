package me.stakexmc.customkits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;










public class CustomKits extends JavaPlugin {
	
	private CoreLog log;
	public CoreConfig cconfig;
	//para cada archivo una nueva clase CoreConfig prro
	public CoreConfig ckits;
	public static CustomKits intance;
	public CoreConfig cmessages;
	
	public  List<Kit> kits = new ArrayList<Kit>();
	
	public void onEnable(){
		this.log = new CoreLog(this);
		intance = this;
		log.line("--------------------------------");
		log.info("CustomKits by PedroJM96 and StakexMC");
		log.info("&7Loading configuration...");
		iniConfig();
		iniKits();
		loadKits();
		PluginManager pm = getServer().getPluginManager();
		MenuListener evenlistener = new MenuListener(this);
		pm.registerEvents(evenlistener, this);
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
			
		}public void iniMessages(){
			cmessages = new CoreConfig(intance,"Messages");
			if (cmessages.Exists()){
				configKits();
				ckits.load();
			}else{
				configKits();
				cmessages.create();
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
					"material:DIAMOND_HELMET, name:Casco de Lujo",
					"material:DIAMOND_CHESTPLATE, name:Traje de Lujo ,enchantment:DURABILITY:5",
					"material:DIAMOND_LEGGINGS",
					"material:IRON_BOOTS",
					"material:LAVA_BUCKET",
					"material:STAINED_GLASS_PANE,data:15,amount:32"));
			//Explicacion
			// "<material>|<data> , <cantida> , <encantamiento>|<nivel>"
			ckits.add("menu.close", "BARRIER");
			
		}
		//Default para archivo Messages
		public void DefaultMessages(){
			cmessages.add("close", "&4Close Inventory");
			cmessages.add("close.lore", Arrays.asList("Click for Closing the Inventory"));
		}
	
	public CoreLog getLog(){
		return this.log;
	}
	
	
	@SuppressWarnings("deprecation")
	public void loadKits(){
		
		Set<String> key = ckits.getKeys();
		for (String nodo : key){
			ConfigurationSection cskit = ckits.getConfigurationSection(nodo);
			if (!cskit.isSet("name"))
		      {
			      log.info("The kit " + nodo + " has no name!");
		      }
		      else if (!cskit.isSet("icon"))
		      {
		    	  log.info("The kit " + nodo + " has no icon!");
		      }
		      else if ((cskit.getInt("id") == 0) || (Material.getMaterial(cskit.getInt("id")) == null))
		      {
		    	  log.info("The kit " + nodo + " has an invalid item icon: " + cskit.getInt("id") + ".");
		      }else{
		    	  Kit kit = new Kit(this.log,Material.getMaterial(cskit.getString("icon")));
		    	  kit.setName(cskit.getString("name"));
		    	  kit.setPermission(cskit.getString("permission"));
		    	  if ((cskit.isSet("description")) && (cskit.isList("description"))) {
			        	List<String> v = cskit.getStringList("description");
			        	kit.setdescription(v);
			      }
		    	  if ((cskit.isSet("items")) && (cskit.isList("items"))) {
			        	List<String> citem = cskit.getStringList("items");
			        	for(String item : citem){
			        		kit.addItem(item);
			        	}
			      }
		    	  this.kits.add(kit);
		      }
		}
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
    			Menu.openMenu(this,p);
    		}
    		
    	}
    	return true;
	}
	
}
