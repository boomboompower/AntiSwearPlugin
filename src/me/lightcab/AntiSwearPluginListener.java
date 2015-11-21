package me.lightcab;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AntiSwearPluginListener implements Listener {


    AntiSwearPluginMain plugin;

    public AntiSwearPluginListener(AntiSwearPluginMain instance) {
        this.plugin = instance;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        boolean playerhasCursed = false;
        String message = e.getMessage().toLowerCase();
        for (String x : plugin.getConfig().getStringList("bannedWords")) {
            if (message.contains(x.toLowerCase())) {
                playerhasCursed = true;
            }

        }
        if (playerhasCursed) {
            e.setMessage(plugin.getConfig().getString("replaceMessage"));
            if (plugin.getConfig().getBoolean("warnPlayer")) {
                p.sendMessage(ChatColor.GREEN + "AntiSwear> " + ChatColor.WHITE + plugin.getConfig().getString("warnMessage"));
            }
            if (plugin.getConfig().getBoolean("setFire")) {
                p.setFireTicks(plugin.getConfig().getInt("fireSecond") * 20);
            }
            if (plugin.getConfig().getBoolean("strikeLightning")) {
                for (int loopNumber = 0; loopNumber < plugin.getConfig().getInt("lightningNumber"); loopNumber++) {
                    p.getLocation().getWorld().strikeLightning(p.getLocation());
                }
            }
        }

    }
}
