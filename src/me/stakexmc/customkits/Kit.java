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
		      if (!y.getDisplayName().equals(this.name)) {
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
					if(parametros[i].startsWith("material:")){
						String[] dele = parametros[i].split(":");
						String parametro = parametros[i].substring(dele[0].length());
						if(Material.getMaterial(parametro.trim()) == null){
							log.info("El material no es valido consulte con un administrador");
							return;
						}
						paramMaterial = Material.getMaterial(parametro.trim());
					}
					if(parametros[i].startsWith("data:")){
						String[] dele = parametros[i].split(":");
						String parametro = parametros[i].substring(dele[0].length());
						paramData = Integer.valueOf(parametro.trim());
					}
					if(parametros[i].startsWith("amount:")){
						String[] dele = parametros[i].split(":");
						String parametro = parametros[i].substring(dele[0].length());
						paramAmount = Integer.valueOf(parametro.trim());
					}
					if(parametros[i].startsWith("name:")){
						String[] dele = parametros[i].split(":");
						String parametro = parametros[i].substring(dele[0].length());
						paramName = parametro.trim();
					}
					if(parametros[i].startsWith("lore:")){
						String[] dele = parametros[i].split(":");
						String parametro = parametros[i].substring(dele[0].length());
						paramLore = parametro.trim();
					}
				}
				if(paramMaterial == null){
					log.info("Debe especificar el material consulte con un administrador");
					return;
				}
				ItemStack item = new ItemStack(paramMaterial,paramAmount,(short)paramData);
				ItemMeta im = item.getItemMeta();
				if(paramName!=null){
					im.setDisplayName(CoreUtils.rColor(paramName));
				}
				if(paramLore!=null){
					String[] loreArray = paramLore.split("|");
					List<String> loreLits = Arrays.asList(loreArray);
					im.setLore(CoreUtils.rColorList(loreLits));
				}
				item.setItemMeta(im);
				items.add(item);
			}else{
				if(parametros[0].startsWith("material:")){
					String[] dele = parametros[0].split(":");
					String parametro = parametros[0].substring(dele[0].length());
					if(Material.getMaterial(parametro.trim()) == null){
						log.info("El material no es valido consulte con un administrador");
						return;
					}
					paramMaterial = Material.getMaterial(parametro.trim());
					ItemStack item = new ItemStack(paramMaterial,paramAmount,(short)paramData);
					items.add(item);
				}else{
					log.info("Debe especificar el material consulte con un administrador");
					return;
				}
			}
		}else{
			//si no tiene comas entonces no tiene parametros como nombre lore encantamiento lo cual indica que solo posee el materia la cantida y posoblemente la data
			Material paramMaterial = null;
			if(citem.startsWith("material:")){
				String[] dele = citem.split(":");
				String parametro = citem.substring(dele[0].length());
				if(Material.getMaterial(parametro.trim()) == null){
					log.info("El material no es valido consulte con un administrador");
					return;
				}
				paramMaterial = Material.getMaterial(parametro.trim());
				ItemStack item = new ItemStack(paramMaterial,1,(short)0);
				items.add(item);
			}else{
				log.info("Debe especificar el material consulte con un administrador");
				return;
			}
		}
	}
	
}
