package me.stakexmc.customkits;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu {
	
	public static void openMenu(Player p){
		ItemStack close= new ItemStack(Material.getMaterial(CustomKits.intance.ckits.getString("close")));
		ItemMeta closemeta = close.getItemMeta();
		closemeta.setDisplayName(CustomKits.intance.cmessages.getString("close"));
		close.setItemMeta(closemeta);
		
		String title = CoreUtils.rColor(CustomKits.intance.cmessages.getString("Menu"));
		Inventory menu = Bukkit.createInventory(null, 54 , title);
		menu.setItem(40, close);

		p.openInventory(menu);
	}
}
