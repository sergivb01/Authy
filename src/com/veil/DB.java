package com.veil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class DB
{
    private File dbFile = new File(Core.getInstance().getDataFolder() + File.separator + "config.yml");
    private YamlConfiguration config = YamlConfiguration.loadConfiguration(this.dbFile);

    public List<String> getResetQueue()
    {
        return this.config.getStringList("resetqueue");
    }

    public void addToResetQueue(Player p)
    {
        List<String> rq = this.config.getStringList("resetqueue");
        if (!rq.contains(p.getUniqueId().toString())) {
            rq.add(p.getUniqueId().toString());
        }
        this.config.set("resetqueue", rq);
        try
        {
            this.config.save(this.dbFile);
        }
        catch (IOException localIOException) {}
    }

    public void delFromResetQueue(Player p)
    {
        List<String> rq = this.config.getStringList("resetqueue");
        if (rq.contains(p.getUniqueId().toString())) {
            rq.remove(p.getUniqueId().toString());
        }
        this.config.set("resetqueue", rq);
        try
        {
            this.config.save(this.dbFile);
        }
        catch (IOException localIOException) {}
    }

    public void addToResetQueue(OfflinePlayer p)
    {
        List<String> rq = this.config.getStringList("resetqueue");
        if (!rq.contains(p.getUniqueId().toString())) {
            rq.add(p.getUniqueId().toString());
        }
        this.config.set("resetqueue", rq);
        try
        {
            this.config.save(this.dbFile);
        }
        catch (IOException localIOException) {}
    }

    public void delFromResetQueue(OfflinePlayer p)
    {
        List<String> rq = this.config.getStringList("resetqueue");
        if (rq.contains(p.getUniqueId().toString())) {
            rq.remove(p.getUniqueId().toString());
        }
        this.config.set("resetqueue", rq);
        try
        {
            this.config.save(this.dbFile);
        }
        catch (IOException localIOException) {}
    }

    public void setIp(Player p, String ip)
    {
        this.config.set(p.getUniqueId().toString(), ip);
        try
        {
            this.config.save(this.dbFile);
        }
        catch (IOException localIOException) {}
    }

    public String getIp(Player p)
    {
        return this.config.getString(p.getUniqueId().toString());
    }
}