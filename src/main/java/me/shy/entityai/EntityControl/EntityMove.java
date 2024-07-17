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

import java.util.List;

import static me.shy.entityai.EntityControl.EntityAttack.cancelAttackTask;
import static me.shy.entityai.EntityControl.EntityEncirclement.cancelSurroundTask;

public class EntityMove {
    private static List<WitherSkeleton> mob = EntitySummon.list;
    private static BukkitTask moveTask = null;
    public static int moveId;

    public static void move(Location targetLocation) {
        cancelMoveTask();
        cancelAttackTask();
        cancelSurroundTask();
        moveId = Bukkit.getScheduler().scheduleSyncRepeatingTask(EntityAi.instance, () -> {
            for (WitherSkeleton ws : mob) {
                ws.setAI(true);
                ws.getPathfinder().moveTo(targetLocation);

                if (ws.getLocation().distance(targetLocation) < 2) {
                    ws.setAI(false);
                }
            }
        }, 0, 20);
    }

    public static void stop() {
        cancelMoveTask();
        for (Entity entity : mob) {
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                livingEntity.setAI(false);
            }
        }
    }

    public static void cancelMoveTask() {
        if (moveId != 0) {
            Bukkit.getScheduler().cancelTask(moveId);
            moveId = 0;
        }
    }
}
