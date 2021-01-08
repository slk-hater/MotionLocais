package br.motion.motionlocais.listeners;

import br.motion.motionlocais.Core;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerJoinEvent implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoinEvent(org.bukkit.event.player.PlayerJoinEvent e){
        Player player = e.getPlayer();
        FileConfiguration config = Core.getInstance().getConfig();
        WorldGuardPlugin worldGuard = Core.getInstance().getWorldGuard();
        if(worldGuard!=null){
            RegionManager regionManager = worldGuard.getRegionManager(player.getWorld());
            ApplicableRegionSet set = regionManager.getApplicableRegions(player.getLocation());
            Core.local.remove(player.getName());
            for(ProtectedRegion region : set) {
                if(config.getConfigurationSection("regions").getKeys(false).contains(region.getId())){
                    Core.local.put(player.getName(), ChatColor.translateAlternateColorCodes('&', config.getString("regions."+region.getId())));
                    break;
                }else Core.local.put(player.getName(), ChatColor.translateAlternateColorCodes('&', config.getString("local-desconhecido")));
            }if(Core.local.get(player.getName())==null) Core.local.put(player.getName(), ChatColor.translateAlternateColorCodes('&', config.getString("local-desconhecido")));
        }
    }
}