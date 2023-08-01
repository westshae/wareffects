package com.shaewest.wareffects;

import java.io.File;

import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.shaewest.wareffects.Tasks.GasTasks;
import com.shaewest.wareffects.Tasks.NukeTasks;
import com.shaewest.wareffects.Commands.RegionalWeapons;
import com.shaewest.wareffects.Events.UseBeacon;

import net.milkbowl.vault.economy.Economy;

public class App extends JavaPlugin {
    public static Economy economy = null;

    @Override
    public void onEnable() {
        loadConfig();//Loads .yml

        setupEconomy();

        //Commands
        this.getCommand("regionalweapons").setExecutor(new RegionalWeapons()); 

        //Events
        this.getServer().getPluginManager().registerEvents(new UseBeacon(), this);

        //Tasks
        new NukeTasks().runTaskTimer(this, 0, 100);
        new GasTasks().runTaskTimer(this, 0, 100);
    }

    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    public static double getPlayerBalance(Player player) {
        return economy.getBalance(player);
    }

    // Example method to add or subtract funds from a player's balance
    public static void modifyPlayerBalance(Player player, double amount) {
        economy.withdrawPlayer(player, amount);
    }

    public void loadConfig() {
        //Get potential config file
        File configFile = new File(getDataFolder(), "config.yml"); //TODO UPDATE FILENAME

        if(!configFile.exists()){
            //Add new defaults, path might be items.0.modelID
            getConfig().addDefault("nuke.price", 1000.0);
            getConfig().addDefault("gas.price", 1000.0);
            getConfig().addDefault("nuke.seconds", 60);
            getConfig().addDefault("gas.seconds", 60);
        }

        //Load config
        getConfig().options().copyDefaults(true);
        saveConfig();
        
    }

    
}