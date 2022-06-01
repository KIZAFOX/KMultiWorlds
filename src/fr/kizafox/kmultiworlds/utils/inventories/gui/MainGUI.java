package fr.kizafox.kmultiworlds.utils.inventories.gui;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.kizafox.kmultiworlds.KMultiWorlds;
import fr.kizafox.kmultiworlds.utils.ItemBuilder;
import fr.kizafox.kmultiworlds.utils.inventories.AbstractInventory;

public class MainGUI extends AbstractInventory {
	
    protected final KMultiWorlds instance;

    public MainGUI(Player player, KMultiWorlds instance) {
        super(player);

        this.instance = instance;
    }

    @Override
    public void display() {
        Inventory inventory = Bukkit.createInventory(null, 9, "KMultiWorlds - World Management");

        inventory.setItem(2, new ItemBuilder(Material.GRASS).setName(ChatColor.GOLD + "" + ChatColor.BOLD + "Flat World").setLore(Arrays.asList("", ChatColor.GRAY + "» Click here to create the world !")).toItemStack());
        inventory.setItem(4, new ItemBuilder(Material.LOG).setName(ChatColor.GOLD + "" + ChatColor.BOLD + "Normal World").setLore(Arrays.asList("", ChatColor.GRAY + "» Click here to create the world !")).toItemStack());
        inventory.setItem(6, new ItemBuilder(Material.COBBLESTONE).setName(ChatColor.GOLD + "" + ChatColor.BOLD + "Large World").setLore(Arrays.asList("", ChatColor.GRAY + "» Click here to create the world !")).toItemStack());
        
        this.instance.getServer().getScheduler().runTask(this.instance, () -> this.getPlayer().openInventory(inventory));
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        ItemStack itemStack = event.getCurrentItem();

        if(itemStack == null || itemStack.getType() == null || itemStack.getItemMeta() == null) return;

        if(inventory.getName().equalsIgnoreCase("KMultiWorlds - World Management")){
            event.setCancelled(true);
            
            switch (itemStack.getType()) {
			case GRASS:
				this.worldCreator(player.getName() + "_FLAT_" + new Random().nextInt(9999), WorldType.FLAT, false);
				break;
			case LOG:
				this.worldCreator(player.getName() + "NORMAL" + new Random().nextInt(9999), WorldType.NORMAL, true);
				break;
			case COBBLESTONE:
				this.worldCreator(player.getName() + "_LARGE_" + new Random().nextInt(9999), WorldType.LARGE_BIOMES, true);
				break;
			default:
				break;
			}
        }
    }
    
    public void worldCreator(String worldName, WorldType type, boolean structures){
        player.closeInventory();
        player.sendMessage(ChatColor.GOLD + "Creating a world...");
        
        Bukkit.createWorld(new WorldCreator(worldName).type(type).generateStructures(structures));
        
        player.sendMessage(ChatColor.GREEN + "Teleport to the world " + worldName + "!");
        Bukkit.getScheduler().runTaskLater(this.instance, () -> {
        	player.teleport(new Location(Bukkit.getWorld(worldName), 0, 100, 0));
        	player.setGameMode(GameMode.CREATIVE);
        }, 100);
    }
}