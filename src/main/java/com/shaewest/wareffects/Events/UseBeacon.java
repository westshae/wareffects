package com.shaewest.wareffects.Events;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

import com.shaewest.wareffects.Extra.ChunkWeapons;

public class UseBeacon implements Listener{
  @EventHandler // Detects player inventory interaction
  public void playerFirstJoinEvent(PlayerInteractEvent event){
    if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
    if(!event.getMaterial().equals(Material.REDSTONE_TORCH))return;
    ItemStack beacon = event.getItem();
    ItemMeta meta = beacon.getItemMeta();
    PersistentDataContainer data = meta.getPersistentDataContainer();
    Set<NamespacedKey> keys = data.getKeys();
    for(Object obj : keys.toArray()){
      if(!(obj instanceof NamespacedKey))continue;
      NamespacedKey key = (NamespacedKey) obj;
      if(key.getKey().equals("nuke")){
        ChunkWeapons.runNuke(event);
        return;
      }
      if(key.getKey().equals("gas")){
        ChunkWeapons.runGas(event);
        return;
      }
    }

  }
}

