//package me.shy.entityai.MagicCircle;
//
//import org.bukkit.Location;
//import org.bukkit.plugin.Plugin;
//import org.bukkit.scheduler.BukkitRunnable;
//
//public class CircleCreate {
//    private final Plugin plugin;
//    private int delay = 5;
//
//    public CircleCreate(Plugin plugin) {
//        this.plugin = plugin;
//    }
//
//    public void createCircle(Location center) {
//        new BukkitRunnable() {
//            int radius = 1;
//            int maxRadius = 5;
//
//            @Override
//            public void run() {
//                if (radius <= maxRadius) {
//                    for (double angle = 0; angle < Math.PI * 2; angle += Math.PI / 12) {
//                        double x = center.getX() + radius * Math.cos(angle);
//                        double y = center.getY();
//                        double z = center.getZ() + radius * Math.sin(angle);
//                        Location particleLoc = new Location(center.getWorld(), x, y, z);
//                        center.getWorld().spawnParticle(org.bukkit.Particle.REDSTONE, particleLoc, 1, 0, 0, 0, 1);
//                    }
//                    radius++;
//                } else {
//                    this.cancel();
//                }
//            }
//        }.runTaskTimer(plugin, 0, delay);
//    }
//
//    public void removeCircle() {
//        // 마법진 제거 로직
//    }
//}
