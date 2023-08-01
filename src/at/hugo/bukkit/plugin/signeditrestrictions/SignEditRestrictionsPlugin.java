package at.hugo.bukkit.plugin.signeditrestrictions;

import io.papermc.paper.event.player.PlayerOpenSignEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SignEditRestrictionsPlugin extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    private boolean isAllowedToEdit(PlayerOpenSignEvent event) {
        if(!event.getPlayer().hasPermission("perm.use")) return false;
        return true;
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onSignOpen(PlayerOpenSignEvent event) {
        if(event.getCause() == PlayerOpenSignEvent.Cause.PLACE) return;
        if(!isAllowedToEdit(event)) event.setCancelled(true);
    }
}