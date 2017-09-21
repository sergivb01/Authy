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

import com.customhcf.base.BasePlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinHandler
        implements Listener
{
    @EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
    public void join(PlayerJoinEvent event) {
        if (!event.getPlayer().hasPermission("auth.required")) {
            return;
        }

        if (Core.getInstance().getDB().getIp(event.getPlayer()) == null) {
            try {

                URL url = new URL("http://ip-api.com/line/" + event.getPlayer().getAddress().getHostName());
                URLConnection connection = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line1 = in.readLine();
                String line2 = in.readLine();
                String line3 = in.readLine();
                String line4 = in.readLine();
                String line5 = in.readLine();
                String city = in.readLine();
                String line7 = in.readLine();
                String line8 = in.readLine();
                String line9 = in.readLine();
                String line10 = in.readLine();
                String line11 = in.readLine();
                String organisation = in.readLine();

                if (line1 != null) {
                    if (line1 == "fail") {
                        event.getPlayer().kickPlayer(Core.getInstance().PREFIX + ChatColor.RED + "An error occured, please contact developer with " + ChatColor.DARK_RED + "#1");
                        return;
                    }
                    Core.getInstance().getDB().setIp(event.getPlayer(), city);
                    event.getPlayer().sendMessage(Core.getInstance().PREFIX + ChatColor.GREEN + "You have been authenticated for the first time.");
                    return;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (Core.getInstance().getDB().getResetQueue().contains(event.getPlayer().getUniqueId().toString())) {
            try {

                URL url = new URL("http://ip-api.com/line/" + event.getPlayer().getAddress().getHostName());
                URLConnection connection = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line1 = in.readLine();
                String line2 = in.readLine();
                String line3 = in.readLine();
                String line4 = in.readLine();
                String line5 = in.readLine();
                String city = in.readLine();
                String line7 = in.readLine();
                String line8 = in.readLine();
                String line9 = in.readLine();
                String line10 = in.readLine();
                String line11 = in.readLine();
                String organisation = in.readLine();


                if (line1 != null) {
                    if (line1 == "fail") {
                        event.getPlayer().kickPlayer(Core.getInstance().PREFIX + "An error occured, please contact developer with " + ChatColor.DARK_RED + "#2");
                        return;
                    }
                    event.getPlayer().sendMessage(
                            Core.getInstance().PREFIX + ChatColor.GREEN + "An admin has authenticated you since last login.");
                    Core.getInstance().getDB().setIp(event.getPlayer(), city);
                    Core.getInstance().getDB().delFromResetQueue(event.getPlayer());
                    return;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        try {

            URL url = new URL("http://ip-api.com/line/" + event.getPlayer().getAddress().getHostName());
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line1 = in.readLine();
            String line2 = in.readLine();
            String line3 = in.readLine();
            String line4 = in.readLine();
            String line5 = in.readLine();
            String city = in.readLine();
            String line7 = in.readLine();
            String line8 = in.readLine();
            String line9 = in.readLine();
            String line10 = in.readLine();
            String line11 = in.readLine();
            String organisation = in.readLine();

            if (line1 != null) {
                if (line1 == "fail") {
                    event.getPlayer().kickPlayer(ChatColor.RED + "An error occured, please contact developer with " + ChatColor.DARK_RED + "#3");
                    return;
                }
                if (!Core.getInstance().getDB().getIp(event.getPlayer()).equals(city)) {
                    event.getPlayer().kickPlayer(
                            Core.getInstance().PREFIX + "\n" + ChatColor.GRAY + "(" + event.getPlayer().getName() + ")" + "\n" + ChatColor.RED + "You have failed to pass VeilMC authentication protocols." + "\n" + ChatColor.YELLOW + "This system is to prevent un-authorised access to accounts.");
                    for (Player abc : Bukkit.getOnlinePlayers()) {
                        if(abc.hasPermission("auth.required")) {
                            abc.sendMessage(" ");
                            abc.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + event.getPlayer().getName() + ChatColor.RED + "" + ChatColor.BOLD + " failed login security authentication");
                            abc.sendMessage(" ");

                        }
                    }
                    return;
                }
                event.getPlayer().sendMessage(Core.getInstance().PREFIX + ChatColor.GREEN + "Authentication successful");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}