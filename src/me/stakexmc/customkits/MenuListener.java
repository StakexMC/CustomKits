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
		if (e.getInventory().getName().equals(CoreUtils.rColor(plugin.cmessages.getString("Menu")))){
		      e.setCancelled(true);
		    }
	}
	
	@EventHandler
	public void MenuClick(InventoryClickEvent e){
		if (e.getInventory().getName().equals(CoreUtils.rColor(plugin.cmessages.getString("Menu")))){
			ItemStack itemInHand = e.getCurrentItem();
			Player p = (Player) e.getWhoClicked();
			for(Kit kit : plugin.kits){
				if(kit.like(itemInHand)){
					for(ItemStack i: kit.getItems()){
						p.getInventory().addItem(i);
					}
					p.closeInventory();
				}
			}
		}
	}
	
}
