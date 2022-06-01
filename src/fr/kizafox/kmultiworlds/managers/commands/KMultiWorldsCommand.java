package fr.kizafox.kmultiworlds.managers.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.kizafox.kmultiworlds.KMultiWorlds;
import fr.kizafox.kmultiworlds.utils.inventories.gui.MainGUI;
import fr.kizafox.kmultiworlds.utils.inventories.gui.WorldGUI;

public class KMultiWorldsCommand implements CommandExecutor {

	private KMultiWorlds instance;
	
	public KMultiWorldsCommand(KMultiWorlds instance) {
		this.instance = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) return true;
		
		Player player = (Player) sender;
		
		if(!player.isOp()){
			player.sendMessage(ChatColor.RED + "Tu n'as pas la permission !");
			return true;
		}
		
		if(args.length == 0){
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "KMultiWorlds »");
			player.sendMessage(ChatColor.YELLOW + "/kmw create" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Ouvre un menu pour choisr le monde que tu veux créer !");
			player.sendMessage(ChatColor.YELLOW + "/kmw tp" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Ouvre un menu pour voir la liste des mondes !");
			return true;
		}
		
		switch (args[0]) {
			case "create":
				new MainGUI(player, this.instance).display();
				break;
			case "tp":
				new WorldGUI(player, this.instance).display();
				break;
			default:
				player.sendMessage(ChatColor.RED + "Argument inconnu !");
				break;
		}
		return false;
	}

}
