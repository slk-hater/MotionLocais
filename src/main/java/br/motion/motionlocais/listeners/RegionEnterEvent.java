package br.motion.motionlocais.listeners;

import br.motion.motionlocais.Core;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class RegionEnterEvent implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void onRegionEnterEvent(com.mewin.WGRegionEvents.events.RegionEnterEvent e) {
        Player player = e.getPlayer();
        String regionname = e.getRegion().getId();
        FileConfiguration config = Core.getInstance().getConfig();
        Core.local.remove(player.getName());
        if(config.getConfigurationSection("regions").getKeys(false).contains(regionname)){ Core.local.put(player.getName(), ChatColor.translateAlternateColorCodes('&', config.getString("regions."+regionname)));
        }else if(!regionname.equals("__global__")) Core.local.put(player.getName(), ChatColor.translateAlternateColorCodes('&', config.getString("local-desconhecido")));
    }
}