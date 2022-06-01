package fr.kizafox.kmultiworlds.managers;

import org.bukkit.plugin.PluginManager;

import fr.kizafox.kmultiworlds.KMultiWorlds;
import fr.kizafox.kmultiworlds.managers.commands.KMultiWorldsCommand;
import fr.kizafox.kmultiworlds.managers.listeners.PlayerListeners;

public class GlobalManagers {
	
	public GlobalManagers(KMultiWorlds instance){
		/**
		 * Listeners
		 */
		PluginManager pluginManager = instance.getServer().getPluginManager();
		pluginManager.registerEvents(new PlayerListeners(instance), instance);
	
		/**
		 * Commands
		 */
		instance.getCommand("kmultiworlds").setExecutor(new KMultiWorldsCommand(instance));
	}

}
