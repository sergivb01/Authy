package com.veil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class IpResetCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (!sender.hasPermission("ipmatcher.admin")) {
            sender.sendMessage(ChatColor.RED + "No permission.");
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("auth")) {
            if (args.length != 1) {
                sender.sendMessage(ChatColor.RED + "Usage: " + cmd.getUsage());
                return true;
            }
            if (args[0] == null) {
                sender.sendMessage(ChatColor.RED + "Usage: " + cmd.getUsage());
                return true;
            }
            Player p = Bukkit.getPlayer(args[0]);
            OfflinePlayer ofp = Bukkit.getOfflinePlayer(args[0]);
            if ((p == null) && (ofp == null)) {
                sender.sendMessage(ChatColor.RED + "Usage: " + cmd.getUsage());
                return true;
            }
            if (p != null) {
                if (!Core.getInstance().getDB().getResetQueue().contains(p.getUniqueId().toString())) {
                    Core.getInstance().getDB().addToResetQueue(p);
                    sender.sendMessage(ChatColor.YELLOW + "You have authenticated " + p.getName());
                } else {
                    sender.sendMessage(ChatColor.RED + "This player is already in the authentication queue.");
                }
            } else {
                if (!Core.getInstance().getDB().getResetQueue().contains(ofp.getUniqueId().toString())) {
                    Core.getInstance().getDB().addToResetQueue(ofp);
                    sender.sendMessage(ChatColor.YELLOW + "You have authenticated " + ofp.getName());
                } else {
                    sender.sendMessage(ChatColor.RED + "This player is already in the authentication queue.");
                }
            }
            return true;
        }
        return true;
    }
}