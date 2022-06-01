package fr.kizafox.kmultiworlds.managers.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.kizafox.kmultiworlds.KMultiWorlds;
import fr.kizafox.kmultiworlds.utils.inventories.gui.MainGUI;
import fr.kizafox.kmultiworlds.utils.inventories.gui.WorldGUI;

public class PlayerListeners implements Listener {

	private KMultiWorlds instance;
	
	public PlayerListeners(KMultiWorlds instance) {
		this.instance = instance;
	}
	
	@EventHandler
	public void onLogin(PlayerJoinEvent event){
		if(event.getPlayer().isOp()) event.getPlayer().sendMessage(ChatColor.GOLD + "KMultiWorlds -» /kmultiworlds or /kmw to manage worlds !");
	}
	
	/**
	 * Gui
	 */
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		new MainGUI((Player) event.getWhoClicked(), this.instance).onInventoryClick(event);
		new WorldGUI((Player) event.getWhoClicked(), this.instance).onInventoryClick(event);
	}
}
