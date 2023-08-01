package com.altoya.wareffects;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Util {
  public static boolean hasEmptyInventorySlot(Player player) {
    for (ItemStack item : player.getInventory().getContents()) {
        if (item == null || item.getType().isAir()) {
            return true; // Found an empty slot
        }
    }
    return false; // No empty slots found
  }

  public static void sendErrorMessage(Player player, String message){
    player.sendMessage("" + ChatColor.BOLD + ChatColor.RED + message);
  }
}
