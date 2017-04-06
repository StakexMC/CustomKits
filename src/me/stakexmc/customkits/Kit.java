package me.stakexmc.customkits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class Kit {
	private String name;
	private String permission;
	private int value;
	private Material icon;
	private List<String> description = new ArrayList<String>();;
	private List<ItemStack> items = new ArrayList<ItemStack>();
	private CoreLog log;
	
	public Kit(CoreLog log,Material icon){
		this.icon = icon;
		this.log = log;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public ItemStack getIcon(){
		ItemStack item = new ItemStack(this.icon,1,(short)0);
		ItemMeta im = item.getItemMeta();
		if(this.name!=null){
			im.setDisplayName(CoreUtils.rColor(this.name));
		}
		if(this.description!=null){
			im.setLore(CoreUtils.rColorList(description));
		}
		item.setItemMeta(im);
		return item;
	}
	
	public boolean like(ItemStack s){
		 if (s == null) {
		      return false;
		    }
		    if (s.getType() != this.icon) {
		      return false;
		    }
		    //y = meta
		    ItemMeta y = s.getItemMeta();
		    if (this.name == null)
		    {
		      if (y.hasDisplayName()) {
		        return false;
		      }
		    }
		    else
		    {
		      if (!y.hasDisplayName()) {
		        return false;
		      }
		      if (!y.getDisplayName().equals(CoreUtils.rColor(this.name) )) {
		        return false;
		      }
		    }
		   
		    return true;
	}
	
	public void setPermission(String permission){
		this.permission = permission;
	}
	
	public void setdescription(List<String> description){
		this.description = description;
	}
	
	public List<ItemStack> getItems(){
		return this.items;
	}
	
	public void addItem(String citem){
		if(citem == null || citem.length() == 0){
			log.info("Item no valid contact an administrator");
			return;
		}  
		//se comprueba si el item tiene definidos parametros como nombre lore o encantamiento
		if(citem.contains(",")){
			//separamos cada uno de los parametros justo donde esta la coma
			String[] parametros = citem.split(",");
			Material paramMaterial = null;
			int paramData = 0;
			int paramAmount = 1;
			String paramName = null;
			String paramLore= null;
			if(parametros.length > 1){
				for(int i=0 ; i<parametros.length ; i++){
					if(parametros[i].trim().startsWith("material:")){
						
						String parametro = parametros[i].trim().substring(9);
						if(Material.getMaterial(parametro.trim()) == null){
							log.info("El material no es valido consulte con un administrador 1");
							return;
						}
						paramMaterial = Material.getMaterial(parametro.trim());
					}
					if(parametros[i].trim().startsWith("data:")){
						
						String parametro = parametros[i].trim().substring(5);
						paramData = Integer.valueOf(parametro.trim());
					}
					if(parametros[i].trim().startsWith("amount:")){
						
						String parametro = parametros[i].trim().substring(7);
						paramAmount = Integer.valueOf(parametro.trim());
					}
					if(parametros[i].trim().startsWith("name:")){
						log.info("nombreeeee");
						String parametro = parametros[i].trim().substring(5);
						paramName = parametro.trim();
						log.info("nombreeeee: "+paramName);
					}
					if(parametros[i].trim().startsWith("lore:")){
						
						String parametro = parametros[i].trim().substring(5);
						paramLore = parametro.trim();
					}
				}
				if(paramMaterial == null){
					log.info("Debe especificar el material consulte con un administrador 2");
					return;
				}
				ItemStack item = new ItemStack(paramMaterial,paramAmount,(short)paramData);
				ItemMeta im = item.getItemMeta();
				if(paramName!=null){
					im.setDisplayName(CoreUtils.rColor(paramName));
				}
				if(paramLore!=null){
					String[] loreArray = paramLore.split("#");
					List<String> loreLits = Arrays.asList(loreArray);
					im.setLore(CoreUtils.rColorList(loreLits));
				}
				item.setItemMeta(im);
				items.add(item);
			}else{
				if(parametros[0].trim().startsWith("material:")){
					
					String parametro = parametros[0].trim().substring(9);
					if(Material.getMaterial(parametro.trim()) == null){
						log.info("El material no es valido consulte con un administrador 3");
						return;
					}
					paramMaterial = Material.getMaterial(parametro.trim());
					ItemStack item = new ItemStack(paramMaterial,paramAmount,(short)paramData);
					items.add(item);
				}else{
					log.info("Debe especificar el material consulte con un administrador 4");
					return;
				}
			}
		}else{
			//si no tiene comas entonces no tiene parametros como nombre lore encantamiento lo cual indica que solo posee el materia la cantida y posoblemente la data
			Material paramMaterial = null;
			if(citem.trim().startsWith("material:")){
				
				String parametro = citem.trim().substring(9);
				if(Material.getMaterial(parametro.trim()) == null){
					log.info("El material no es valido consulte con un administrador 5");
					return;
				}
				paramMaterial = Material.getMaterial(parametro.trim());
				ItemStack item = new ItemStack(paramMaterial,1,(short)0);
				items.add(item);
			}else{
				log.info("Debe especificar el material consulte con un administrador 6");
				return;
			}
		}
	}
	
}
