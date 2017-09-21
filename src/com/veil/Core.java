package com.veil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class Core
        extends JavaPlugin {
    public String PREFIX = ChatColor.DARK_RED + "[SECURITY] ";
    private static Core instance;
    private DB db;

    public static Core getInstance() {
        return instance;
    }

    public DB getDB() {
        return this.db;
    }

    public void onEnable() {
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        instance = this;
        this.db = new DB();
        Bukkit.getServer().getPluginManager().registerEvents(new JoinHandler(), this);
        getCommand("auth").setExecutor(new IpResetCommand());
    }

    public static HashMap<String, String> getLoc(String ip) {
        HashMap<String, String> toReturn = new HashMap<>();


        try {
            URL url = new URL("http://ip-api.com/line/" + ip);
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String verify = in.readLine();
            String line2 = in.readLine();
            String line3 = in.readLine();
            String line4 = in.readLine();
            String line5 = in.readLine();
            String city = in.readLine();
            /*String line7 = in.readLine();
            String line8 = in.readLine();
            String line9 = in.readLine();
            String line10 = in.readLine();
            String line11 = in.readLine();
            String organisation = in.readLine();*/
            toReturn.put("city", city);
            toReturn.put("verify", verify);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }
}