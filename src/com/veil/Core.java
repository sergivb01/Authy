package com.veil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Core
        extends JavaPlugin
{
    public String PREFIX = ChatColor.DARK_RED + "[SECURITY] ";
    private static Core instance;
    private DB db;

    public static Core getInstance()
    {
        return instance;
    }

    public DB getDB()
    {
        return this.db;
    }

    public void onEnable()
    {
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        instance = this;
        this.db = new DB();
        Bukkit.getServer().getPluginManager().registerEvents(new JoinHandler(), this);
        getCommand("auth").setExecutor(new IpResetCommand());
    }
}
