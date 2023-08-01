package com.altoya.wareffects;

import java.io.File;

import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;
public class App extends JavaPlugin {
    public static Economy economy = null;
    @Override
    public void onEnable() {
        loadConfig();//Loads .yml

        setupEconomy();

        //How to register commands
        //this.getCommand("commandNameInYml").setExecutor(new ObjectWithOnCommandMethod()); 

        //How to register eventListeners
        //this.getServer().getPluginManager().registerEvents(new ObjectWith@EventHandlers(), this);
    }

    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    public double getPlayerBalance(Player player) {
        return economy.getBalance(player);
    }

    // Example method to add or subtract funds from a player's balance
    public void modifyPlayerBalance(Player player, double amount) {
        economy.withdrawPlayer(player, amount);
    }

    public void loadConfig() {
        //Get potential config file
        File configFile = new File(getDataFolder(), "filename.extension"); //TODO UPDATE FILENAME

        if(!configFile.exists()){
            //Add new defaults, path might be items.0.modelID
            // getConfig().addDefault("pathInYml", "valueToSet");
        }

        //Load config
        getConfig().options().copyDefaults(true);
        saveConfig();
        
    }

    
}