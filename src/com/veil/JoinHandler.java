package com.veil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinHandler
        implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void join(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if (!p.hasPermission("auth.required")) {
            return;
        }


        // String city  = Core.getLoc(p.getAddress().getHostName()).getOrDefault("city", "nocity"); //This avoids nullpointer exception if there was an error. U can use this:
        String verify = Core.getLoc(p.getAddress().getHostName()).get("verify");
        String city = Core.getLoc(p.getAddress().getHostName()).get("city");

        p.sendMessage(city);
        p.sendMessage(verify);


        if (Core.getInstance().getDB().getIp(p) == null) {
            if (!verify.equalsIgnoreCase("success")) {
                p.kickPlayer(ChatColor.RED + "Could not connect to API " + ChatColor.GRAY + "[i]");
                return;
            }
            Core.getInstance().getDB().setIp(p, city);
            p.sendMessage(Core.getInstance().PREFIX + "You have been authenticated for the first time.");
            return;

        }
        if (Core.getInstance().getDB().getResetQueue().contains(p.getUniqueId().toString())) {


            if (!verify.equalsIgnoreCase("success")) {
                p.kickPlayer(ChatColor.RED + "Could not connect to API " + ChatColor.GRAY + "[ii]");
                return;
            }
            p.sendMessage(
                    Core.getInstance().PREFIX + "An admin has authenticated you since last login.");
            Core.getInstance().getDB().setIp(p, city);
            Core.getInstance().getDB().delFromResetQueue(p);
            return;

        }


        if (!verify.equalsIgnoreCase("success")) {
            p.kickPlayer(ChatColor.RED + "Could not connect to API " + ChatColor.GRAY + "[iii]");
            return;
        }
        if (!Core.getInstance().getDB().getIp(p).equals(city)) {
            p.kickPlayer(
                    Core.getInstance().PREFIX + "Security" + "\n" + "\n" + ChatColor.YELLOW + "Sorry " + p.getName() + " you have failed " + "\n" + ChatColor.YELLOW + "to pass VeilMC authentication protocols." + "\n" + ChatColor.WHITE + "This system is to prevent un-authorised access to accounts.");
            for (Player abc : Bukkit.getOnlinePlayers()) {

                if (abc.hasPermission("auth.required")) {
                    abc.sendMessage(" ");
                    abc.sendMessage(Core.getInstance().PREFIX + p.getName() + " failed login security authentication");
                    abc.sendMessage(" ");

                }
            }
            return;
        }
        p.sendMessage(Core.getInstance().PREFIX + "Authentication successful");
    }
}
