package com.veil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IpResetCommand
        implements CommandExecutor
{
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3)
    {
        if (!arg0.hasPermission("ipmatcher.admin"))
        {
            arg0.sendMessage(ChatColor.RED + "No permission.");
            return true;
        }
        if (arg1.getName().equalsIgnoreCase("auth"))
        {
            if (arg3.length != 1)
            {
                arg0.sendMessage(ChatColor.RED + "Usage: " + arg1.getUsage());
                return true;
            }
            if (arg3[0] == null)
            {
                arg0.sendMessage(ChatColor.RED + "Usage: " + arg1.getUsage());
                return true;
            }
            Player p = Bukkit.getPlayer(arg3[0]);
            OfflinePlayer ofp = Bukkit.getOfflinePlayer(arg3[0]);
            if ((p == null) && (ofp == null))
            {
                arg0.sendMessage(ChatColor.RED + "Usage: " + arg1.getUsage());
                return true;
            }
            if (p != null)
            {
                Core.getInstance().getDB().addToResetQueue(p);
                arg0.sendMessage(ChatColor.YELLOW + "You have authenticated " + p.getName());
            }
            else
            {
                Core.getInstance().getDB().addToResetQueue(ofp);
                arg0.sendMessage(ChatColor.YELLOW + "You have authenticated " + ofp.getName());
            }
            return true;
        }
        return true;
    }
}