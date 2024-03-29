package com.shaewest.wareffects.Extra;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;

public class CustomEffects {
  public static void handleRadiation(Player player){

    ItemStack helmet = player.getInventory().getHelmet();
    ItemStack chestplate = player.getInventory().getChestplate();
    ItemStack leggings = player.getInventory().getLeggings();
    ItemStack boots = player.getInventory().getBoots();

    boolean hasLeatherHelmet = false;
    boolean hasLeatherChestplate = false;
    boolean hasLeatherLeggings = false;
    boolean hasLeatherBoots = false;


    if(helmet != null && chestplate != null && leggings != null && boots != null){
      boolean isLeatherHelmet = helmet.getType().equals(Material.LEATHER_HELMET);
      boolean isLeatherChestplate = chestplate.getType().equals(Material.LEATHER_CHESTPLATE);
      boolean isLeatherLeggings = leggings.getType().equals(Material.LEATHER_LEGGINGS);
      boolean isLeatherBoots = boots.getType().equals(Material.LEATHER_BOOTS);
      
      hasLeatherHelmet = isLeatherHelmet && ((LeatherArmorMeta) helmet.getItemMeta()).getColor().equals(Color.fromBGR(4053246));
      hasLeatherChestplate = isLeatherChestplate && ((LeatherArmorMeta) chestplate.getItemMeta()).getColor().equals(Color.fromBGR(4053246));
      hasLeatherLeggings = isLeatherLeggings && ((LeatherArmorMeta) leggings.getItemMeta()).getColor().equals(Color.fromBGR(4053246));
      hasLeatherBoots = isLeatherBoots && ((LeatherArmorMeta) boots.getItemMeta()).getColor().equals(Color.fromBGR(4053246));
    }
    
    boolean hasLeatherArmour = hasLeatherHelmet || hasLeatherChestplate || hasLeatherLeggings || hasLeatherBoots;

    if(hasLeatherArmour) return;
    player.sendMessage("" + ChatColor.BOLD + ChatColor.GREEN + "RADIATION ATTACK");
    player.playSound(player, Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 0);

    switch(Util.randomInt(4)){
      case 0:
        new PotionEffect(PotionEffectType.POISON, 60, 2).apply(player);
        break;
      case 1:
        new PotionEffect(PotionEffectType.HUNGER, 60, 2).apply(player);
        break;
      case 2:
        new PotionEffect(PotionEffectType.SLOW_DIGGING, 60, 3).apply(player);
        break;
      case 3:
        new PotionEffect(PotionEffectType.WITHER, 60, 3).apply(player);
        break;
    }
  }

  public static void handleGas(Player player){
    ItemStack helmet = player.getInventory().getHelmet();
    boolean hasLeatherHelmet = false;
    if(helmet != null){
      boolean isLeatherHelmet = helmet.getType().equals(Material.LEATHER_HELMET);
      hasLeatherHelmet = isLeatherHelmet && ((LeatherArmorMeta) helmet.getItemMeta()).getColor().equals(Color.fromBGR(4053246));
    }

    if(hasLeatherHelmet) return;
    player.sendMessage("" + ChatColor.BOLD + ChatColor.GOLD + "GAS ATTACK");
    player.playSound(player, Sound.ENTITY_CREEPER_PRIMED, 1, 0);

    switch(Util.randomInt(3)){
      case 0:
        new PotionEffect(PotionEffectType.SLOW, 60, 3).apply(player);
        break;
      case 1:
        new PotionEffect(PotionEffectType.BLINDNESS, 60, 3).apply(player);
        break;
      case 2:
        new PotionEffect(PotionEffectType.DARKNESS, 60, 3).apply(player);
        break;
    }
  }

  public static void handlePtsd(Player player, int kills){
    String[] ptsdMessages = {
      "Haunted by the memories of war, I can't shake off the overwhelming regret for the things I did and witnessed.",
      "Each night, as I close my eyes, the echoes of war and the weight of regret take over.",
      "Surviving the battlefield was just the beginning; living with the haunting regret is a never-ending battle.",
      "War leaves scars that no one can see, but the burden of regret is etched deep within the soul.",
      "In the quiet moments, regret grips me tightly, replaying the decisions that led to irreversible consequences.",
      "Wishing I could turn back time, but all I have are the memories of war and the weight of remorse.",
      "The camaraderie forged on the battlefield is overshadowed by the remorse of the actions taken in the name of war.",
      "Long after the war has ended, the battle against regret rages on, tormenting the mind and soul.",
      "The horrors of war have left me grappling with overwhelming guilt and regret, questioning if it was all worth it.",
      "Amidst the flashbacks and nightmares, the one constant companion is regret - a heavy burden I can't escape.",
    };
    int randomIndex = Util.randomInt(10);
    player.sendMessage("" + ChatColor.BOLD + ChatColor.GRAY + ptsdMessages[randomIndex]);
    player.playSound(player, Sound.ENTITY_ENDER_DRAGON_HURT, 1, 0);


    int level = 1;
    if(kills > 10) level = 2;
    if(kills > 25) level = 3;
    if(kills > 50) level = 4;
    if(kills > 100) level = 5;

    new PotionEffect(PotionEffectType.SLOW, level*1200, level).apply(player);
    new PotionEffect(PotionEffectType.HUNGER, level*1200, level).apply(player);
    new PotionEffect(PotionEffectType.DARKNESS, level*1200, level).apply(player);
    new PotionEffect(PotionEffectType.SLOW_DIGGING, level*1200, level).apply(player);

  }

}
