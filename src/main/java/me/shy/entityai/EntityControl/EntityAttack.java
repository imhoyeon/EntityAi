package me.shy.entityai.EntityControl;

import me.shy.entityai.Entity.EntitySummon;
import me.shy.entityai.EntityAi;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

import static me.shy.entityai.EntityControl.EntityMove.cancelMoveTask;

public class EntityAttack {
    private static List<WitherSkeleton> mob = EntitySummon.list;
    private static BukkitTask attackTask = null;

    public static void attack(Player player) {
        Entity entity = player.getTargetEntity(30, true);
        EntityMove.cancelMoveTask();
        if (entity instanceof LivingEntity) {
            for (WitherSkeleton ws : mob) {
                ws.setAI(true);
                ws.setTarget((LivingEntity) entity);
            }
            cancelAttackTask();
            attackTask = Bukkit.getScheduler().runTaskTimer(EntityAi.instance, () -> {
                for (WitherSkeleton ws : mob) {
                    if (entity.isDead()) {
                        ws.setAI(false);
                        ws.setTarget(null);
                        break;
                    }
                }
            }, 0, 20);
        }

    }

    public static void stop() {
        for (WitherSkeleton ws : mob) {
            ws.setTarget(null);
            ws.setAI(false);
        }
        cancelAttackTask();
    }

    public static void cancelAttackTask() {
        if (attackTask != null) {
            attackTask.cancel();
            attackTask = null;
        }
    }
}
