package com.shaewest.wareffects.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

import com.shaewest.wareffects.Tasks.PtsdTasks;

public class KillPtsd implements Listener{
  @EventHandler 
  public void playerKillEvent(PlayerDeathEvent event){
    LivingEntity mob = event.getEntity();
    if(!(mob instanceof Player)) return;

    Player killer = mob.getKiller();
    if(killer == null) return;

    Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("wareffects");

    PtsdTasks task = new PtsdTasks();
    task.playerUUID = killer.getUniqueId();

    task.runTaskLater(plugin, 600);
  }
}

