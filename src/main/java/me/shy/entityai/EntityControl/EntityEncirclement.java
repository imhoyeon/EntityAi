package me.shy.entityai.EntityControl;

import me.shy.entityai.Entity.EntitySummon;
import me.shy.entityai.EntityAi;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.List;

public class EntityEncirclement {
    private static List<WitherSkeleton> mob = EntitySummon.list;
    private static BukkitTask surroundTask = null;
    public static int siegeId;

    private static int distance = 5;

    public static void encirclement(Player player) {
        for (WitherSkeleton ws : mob) {
            ws.setAI(true);
        }
        Entity entity = player.getTargetEntity(30, true);
        EntityMove.cancelMoveTask();
        EntityAttack.cancelAttackTask();
        cancelSurroundTask();

        siegeId = Bukkit.getScheduler().scheduleSyncRepeatingTask(EntityAi.instance, () -> {

            for (int i = 0; i < mob.size(); i++) {
                double angle = i * 2 * Math.PI / mob.size();
                int x = (int) (entity.getLocation().getX() + distance * Math.cos(angle));
                int z = (int) (entity.getLocation().getZ() + distance * Math.sin(angle));
                Location location = new Location(player.getWorld(), x, entity.getY(), z);
                mob.get(i).getPathfinder().moveTo(location);

                if (mob.get(i).getLocation().distance(location) < 2) {
                    mob.get(i).setTarget((LivingEntity) entity);
                    int finalI = i;
                    Bukkit.getScheduler().scheduleSyncDelayedTask(EntityAi.instance, () -> {
                        mob.get(finalI).setTarget(null);
                        mob.get(finalI).setAI(false);
                    }, 5);
                }
            }
        }, 0, 20);
    }

    public static void stop() {
        cancelSurroundTask();
        for (Entity entity : mob) {
            if (entity instanceof WitherSkeleton) {
                WitherSkeleton witherSkeleton = (WitherSkeleton) entity;
                witherSkeleton.setAI(false);
                witherSkeleton.setTarget(null);
            }
        }
    }

    public static void cancelSurroundTask() {
        if (surroundTask != null) {
            surroundTask.cancel();
            surroundTask = null;
        }
    }
}
