package fr.kizafox.kmultiworlds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import fr.kizafox.kmultiworlds.managers.GlobalManagers;

public final class KMultiWorlds extends JavaPlugin {
	
	private static KMultiWorlds instance;
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		instance = this;
		
		new GlobalManagers(this);
		
		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "KMultiWorlds is enabled ! by @KIZAFOX " + ChatColor.GRAY + "(" + ChatColor.GREEN + "Version: " + ChatColor.LIGHT_PURPLE + this.getDescription().getVersion() + ChatColor.GRAY + ")");
	}

	@Override
	public void onDisable() {
		super.onDisable();
		
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "KMultiWorlds is disabled !");
	}
	
	public static KMultiWorlds get(){
		return instance;
	}
}
