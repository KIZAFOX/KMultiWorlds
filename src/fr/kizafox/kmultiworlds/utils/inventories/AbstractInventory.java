package fr.kizafox.kmultiworlds.utils.inventories;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public abstract class AbstractInventory {

    protected final Player player;

    public AbstractInventory(Player player) {
        this.player = player;
    }

    public abstract void display();

    public abstract void onInventoryClick(InventoryClickEvent event);

    public Player getPlayer() {
        return player;
    }
}