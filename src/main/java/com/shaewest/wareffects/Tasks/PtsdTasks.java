package com.shaewest.wareffects.Tasks;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.shaewest.wareffects.Extra.CustomEffects;

public class PtsdTasks extends BukkitRunnable {
  public UUID playerUUID = null;

  @Override
  public void run() {
    if(playerUUID == null) return;
    Player player = Bukkit.getPlayer(playerUUID);    
    int kills = player.getStatistic(Statistic.PLAYER_KILLS);
    CustomEffects.handlePtsd(player, kills);
  }
}
