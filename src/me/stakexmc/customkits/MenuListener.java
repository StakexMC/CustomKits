package me.stakexmc.customkits;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;




public class MenuListener implements Listener {
	
	private CustomKits plugin;
	
	 public MenuListener(CustomKits plugin)
	  {
	    this.plugin = plugin;
	  }
	
	@EventHandler
	public void InventoryClick(InventoryClickEvent e)
	  {
		if (e.getInventory().getName().equals(CoreUtils.rColor(plugin.cmessages.getString("menu-title")))){
		      e.setCancelled(true);
		    }
	}
	 
	@EventHandler
	public void MenuClick(InventoryClickEvent e){
		if (e.getInventory().getName().equals(CoreUtils.rColor(plugin.cmessages.getString("menu-title")))){
			ItemStack itemInHand = e.getCurrentItem();
			Player p = (Player) e.getWhoClicked();
			for(Kit kit : plugin.kits){
				plugin.getLog().info("for de kit");
				if(kit.like(itemInHand)){
					plugin.getLog().info("igual");
					for(ItemStack i: kit.getItems()){
						p.getInventory().addItem(i);
						plugin.getLog().info("se da un item");
					}
					p.closeInventory();
					break;
					
				}
			} 
			
		}
	}
	
}
