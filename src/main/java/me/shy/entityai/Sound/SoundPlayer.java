package me.shy.entityai.Sound;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundPlayer  {
    private static ProtocolManager protocolManager = null;
    public SoundPlayer() {
        this.protocolManager = ProtocolLibrary.getProtocolManager();
    }

    public static void soundPlayer(Player player, Location location){
        try {
            PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.NAMED_SOUND_EFFECT);

            packet.getSoundCategories().write(0, EnumWrappers.SoundCategory.MASTER);

            packet.getSoundEffects().write(0, Sound.ENTITY_PIG_DEATH);


            packet.getIntegers()
                    .write(0, (int) (location.getX() * 8.0))
                    .write(1, (int) (location.getY() * 8.0))
                    .write(2, (int) (location.getZ() * 8.0));


            packet.getFloat()
                    .write(0, 1.0f)
                    .write(1, 1.0f);

            packet.getLongs()
                    .write(0, 0L);
            protocolManager.sendServerPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}