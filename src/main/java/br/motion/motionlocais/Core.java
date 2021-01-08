package br.motion.motionlocais;

import br.motion.motionlocais.listeners.PlayerJoinEvent;
import br.motion.motionlocais.listeners.PlayerTeleportEvent;
import br.motion.motionlocais.listeners.RegionEnterEvent;
import br.motion.motionlocais.listeners.RegionLeaveEvent;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Core extends JavaPlugin {
    public static Core instance;
    public static Core getInstance() { return instance; }
    private void setInstance(Core instance) { Core.instance = instance; }
    public static Map<String, String> local = new HashMap<>();
    public void onEnable() {
        setInstance(this);
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new PlayerJoinEvent(), this);
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new LocalPlaceholder().register();
            Bukkit.getPluginManager().registerEvents(new RegionEnterEvent(), this);
            Bukkit.getPluginManager().registerEvents(new RegionLeaveEvent(), this);
            Bukkit.getPluginManager().registerEvents(new PlayerTeleportEvent(), this);
        } else {
            getLogger().info("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }
    public WorldGuardPlugin getWorldGuard() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
        if ((!(plugin instanceof WorldGuardPlugin))) {
            return null;
        }
        return (WorldGuardPlugin)plugin;
    }
}