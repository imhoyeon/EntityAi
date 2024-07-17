package me.shy.entityai.Entity;

import me.shy.entityai.EntityAi;
import me.shy.entityai.Sound.SoundPlayer;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vex;
import org.bukkit.entity.WitherSkeleton;

import java.util.ArrayList;
import java.util.List;

public class EntitySummon {

    private EntityAi plugin;

    public static List<WitherSkeleton> list = new ArrayList<>();

    public EntitySummon(EntityAi plugin) {
        this.plugin = plugin;
    }

    public static void summonVex(Player player, Location location) {
        World world = player.getWorld();
        WitherSkeleton ws = world.spawn(location, org.bukkit.entity.WitherSkeleton.class, entity -> {
            SoundPlayer soundPlayer = new SoundPlayer();
            SoundPlayer.soundPlayer(player, location);

        });
        ws.setAI(false);
        player.sendMessage(String.valueOf(list.size()));
        list.add(ws);

    }
}
