package com.altoya.wareffects;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;
public class App extends JavaPlugin {
    @Override
    public void onEnable() {
        loadConfig();//Loads .yml

        //How to register commands
        //this.getCommand("commandNameInYml").setExecutor(new ObjectWithOnCommandMethod()); 

        //How to register eventListeners
        //this.getServer().getPluginManager().registerEvents(new ObjectWith@EventHandlers(), this);
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