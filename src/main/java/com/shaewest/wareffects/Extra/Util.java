package com.shaewest.wareffects.Extra;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Util {
  public static boolean hasEmptyInventorySlot(Player player) {
    for (ItemStack item : player.getInventory().getContents()) {
        if (item == null || item.getType().isAir()) {
            return true;
        }
    }
    return false;
  }

  public static void sendErrorMessage(Player player, String message){
    player.sendMessage("" + ChatColor.BOLD + ChatColor.RED + message);
  }

  public static int randomInt(int max){
    Random random = new Random();

    return random.nextInt(max);
  }
}
