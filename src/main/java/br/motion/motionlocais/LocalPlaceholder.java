package br.motion.motionlocais;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class LocalPlaceholder extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return "motionlocais";
    }
    @Override
    public String getAuthor() {
        return null;
    }
    @Override
    public String getVersion() {
        return null;
    }
    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
        if (p == null) return null;
        // %motionlocais_local%
        if (identifier.equals("local")){
            return Core.local.get(p.getName());
        }
        return null;
    }
}
