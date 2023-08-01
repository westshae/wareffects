package com.shaewest.wareffects.Extra;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CustomEffects {
  public static void handleRadiation(Player player){
    ItemStack[] armour = player.getInventory().getArmorContents();
    boolean safe = true;
    for(ItemStack piece : armour){
      if(piece == null){
        safe = false;
        return;
      }
      boolean isLeatherHelmet = piece.getType() == Material.LEATHER_HELMET;
      boolean isLeatherChestplate = piece.getType() == Material.LEATHER_CHESTPLATE;
      boolean isLeatherLeggings = piece.getType() == Material.LEATHER_LEGGINGS;
      boolean isLeatherBoots = piece.getType() == Material.LEATHER_BOOTS;
      boolean isLeatherArmour = isLeatherBoots || isLeatherChestplate || isLeatherHelmet || isLeatherLeggings;

      if(!isLeatherArmour){
        safe = false;
        return;
      }
    }

    if(!safe) return;

    switch(Util.randomInt(5)){
      case 0:
        new PotionEffect(PotionEffectType.POISON, 40, 1).apply(player);
        break;
      case 1:
        new PotionEffect(PotionEffectType.CONFUSION, 60, 5).apply(player);
        break;
      case 2:
        new PotionEffect(PotionEffectType.HUNGER, 60, 2).apply(player);
        break;
      case 3:
        new PotionEffect(PotionEffectType.SLOW_DIGGING, 60, 3).apply(player);
        break;
      case 4:
        new PotionEffect(PotionEffectType.WITHER, 60, 3).apply(player);
        break;
    }

  }

  public static void handleGas(Player player){
    ItemStack[] armour = player.getInventory().getArmorContents();
    boolean safe = false;
    for(ItemStack piece : armour){
      if(piece == null) continue;
      if(piece.getType() == Material.LEATHER_HELMET){
        safe = true;
        return;
      }
    }



    if(safe) return;


    switch(Util.randomInt(4)){
      case 0:
        new PotionEffect(PotionEffectType.SLOW, 60, 3).apply(player);
        break;
      case 1:
        new PotionEffect(PotionEffectType.BLINDNESS, 60, 3).apply(player);
        break;
      case 2:
        new PotionEffect(PotionEffectType.CONFUSION, 60, 5).apply(player);
        break;
      case 3:
        new PotionEffect(PotionEffectType.DARKNESS, 60, 3).apply(player);
        break;
    }
  }

}
