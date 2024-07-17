package me.shy.entityai.EventHandler;

import me.shy.entityai.Entity.EntitySummon;
import me.shy.entityai.EntityAi;
import me.shy.entityai.EntityControl.EntityAttack;
import me.shy.entityai.EntityControl.EntityEncirclement;
import me.shy.entityai.EntityControl.EntityMove;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class ClickCheck implements Listener {
    private final Plugin plugin;

    public ClickCheck(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType() == Material.STICK) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
                if (player.isSneaking()) {
                    Location targetLocation = player.getTargetBlockExact(30).getLocation().add(0, 1, 0);
                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        EntitySummon.summonVex(player, targetLocation);
                    }, 40);
                } else {
                    EntityAttack.stop();
                    Location targetLocation = player.getTargetBlockExact(30).getLocation().add(0, 1, 0);
                    EntityMove.move(targetLocation);
                }
            }
            if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR) {
                if (player.isSneaking()) {
                    EntityEncirclement.encirclement(player);

                } else {
                    EntityAttack.stop();
                    EntityAttack.attack(player);
                }
            }
        }
    }
}
