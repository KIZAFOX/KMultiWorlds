package fr.kizafox.kmultiworlds.utils.inventories.gui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.kizafox.kmultiworlds.KMultiWorlds;
import fr.kizafox.kmultiworlds.utils.ItemBuilder;
import fr.kizafox.kmultiworlds.utils.inventories.AbstractInventory;

public class WorldGUI extends AbstractInventory{

    protected final KMultiWorlds instance;
    
	private String[] worldNames = new String[Bukkit.getServer().getWorlds().size()];
	private int count = 0;

    public WorldGUI(Player player, KMultiWorlds instance) {
        super(player);

        this.instance = instance;
    }

    @Override
    public void display() {
        Inventory inventory = Bukkit.createInventory(null, 9 * 6, "KMultiWorlds - Management");

		this.instance.getServer().getWorlds().forEach(worlds -> {
			worldNames[count] = worlds.getName();
			count++;
		});
        
        inventory.setItem(0, new ItemBuilder(Material.COMPASS).setName(ChatColor.GOLD + "" + ChatColor.BOLD + "General Information").setLore(Arrays.asList("", ChatColor.GRAY + "» Worlds count: " + count)).toItemStack());
        
		for(String names : worldNames){
			inventory.addItem(new ItemBuilder(Material.GRASS).setName(ChatColor.GOLD + "" + ChatColor.BOLD + names).setLore(Arrays.asList("", ChatColor.GRAY + "» Click to join !")).toItemStack());
		}
        
        this.instance.getServer().getScheduler().runTask(this.instance, () -> this.getPlayer().openInventory(inventory));
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        ItemStack itemStack = event.getCurrentItem();

        if(itemStack == null || itemStack.getType() == null || itemStack.getItemMeta() == null) return;

        if(inventory.getName().equalsIgnoreCase("KMultiWorlds - Management")){
            event.setCancelled(true);
            
            if(itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "General Information")){
            	player.closeInventory();
            }
            
    		this.instance.getServer().getWorlds().forEach(worlds -> {
    			worldNames[count] = worlds.getName();
    			count++;
    		});
            
            for(String names : worldNames){
            	if(itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + names)){
            		player.closeInventory();
            		
    				player.sendMessage(ChatColor.GOLD + "Teleport to " + names + "...");
    				Bukkit.getScheduler().runTaskLater(this.instance, () -> {
    					player.teleport(new Location(Bukkit.getWorld(names), 0, 100, 0));
    				}, 100);
            	}
            }
        }
    }
}
