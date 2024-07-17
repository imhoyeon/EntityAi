package me.shy.entityai;

import me.shy.entityai.EventHandler.ClickCheck;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class EntityAi extends JavaPlugin {

    public static EntityAi instance;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getLogger().warning("EntityAi 플러그인 활성화");
        getServer().getPluginManager().registerEvents(new ClickCheck(this), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().warning("EntityAi 플러그인 비활성화");
    }
}
