package com.shaewest.wareffects.Extra;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class ChunkWeapons {
  public static void runNuke(PlayerInteractEvent event){
    event.getItem().setAmount(0);
    
    Chunk chunk = event.getPlayer().getLocation().getChunk();
    int x = chunk.getX();
    int z = chunk.getZ();

    Bukkit.broadcastMessage("" + ChatColor.BOLD + ChatColor.RED + "A nuke has been dropped at the chunk coordinates " + x + ":" + z);
   
    FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("wareffects").getConfig();

    int lives = config.getInt("nuke.seconds");
    lives = lives/5;

    for(int i = -2; i <= 3; i++){
      for(int j = -2; j <= 3; j++){
        config.set("nuke.chunks." + (x+i) + ":" + (z+j) + ".lives", lives);
      }
    }

    Plugin wareffectsPlugin = Bukkit.getServer().getPluginManager().getPlugin("wareffects");
    if (wareffectsPlugin != null) {
        wareffectsPlugin.saveConfig();
    }
  }

  public static void runGas(PlayerInteractEvent event){
    event.getItem().setAmount(0);
    
    Chunk chunk = event.getPlayer().getLocation().getChunk();
    int x = chunk.getX();
    int z = chunk.getZ();

    Bukkit.broadcastMessage("" + ChatColor.BOLD + ChatColor.RED + "A nuke has been dropped at the chunk coordinates " + x + ":" + z);
   
    FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("wareffects").getConfig();

    int lives = config.getInt("gas.seconds");
    lives = lives/5;

    for(int i = -2; i <= 3; i++){
      for(int j = -2; j <= 3; j++){
        config.set("gas.chunks." + (x+i) + ":" + (z+j) + ".lives", lives);
      }
    }

    Plugin wareffectsPlugin = Bukkit.getServer().getPluginManager().getPlugin("wareffects");
    if (wareffectsPlugin != null) {
        wareffectsPlugin.saveConfig();
    }
  }
}
