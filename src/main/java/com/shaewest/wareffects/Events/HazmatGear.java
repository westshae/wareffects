package com.shaewest.wareffects.Events;


import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import net.md_5.bungee.api.ChatColor;

public class HazmatGear implements Listener{
  @EventHandler 
  public void playerCraftYellowLeather(CraftItemEvent event){
    ItemStack result = event.getCurrentItem();
    ItemMeta meta = result.getItemMeta();

    ArrayList<String> lore = new ArrayList<>();

    lore.add("" + ChatColor.BOLD + ChatColor.YELLOW + "Hazmat Gear protects you from gas and nuclear attacks.");
    lore.add("" + ChatColor.BOLD + ChatColor.YELLOW + "The gas mask alone protects from gas.");
    lore.add("" + ChatColor.BOLD + ChatColor.YELLOW + "The whole set protects you nuclear radiation.");

    if(result.getType().equals(Material.LEATHER_HELMET)){
      LeatherArmorMeta leatherMeta = (LeatherArmorMeta) result.getItemMeta();
      Color color = leatherMeta.getColor();
      if(!color.equals(Color.fromBGR(4053246))) return;
      meta.setDisplayName("" + ChatColor.BOLD + ChatColor.YELLOW + "Gas Mask");
      meta.setLore(lore);
    }
    else if(result.getType().equals(Material.LEATHER_CHESTPLATE)){
      LeatherArmorMeta leatherMeta = (LeatherArmorMeta) result.getItemMeta();
      Color color = leatherMeta.getColor();
      if(!color.equals(Color.fromBGR(4053246))) return;
      meta.setDisplayName("" + ChatColor.BOLD + ChatColor.YELLOW + "Hazmat Jacket");
      meta.setLore(lore);
    }
    else if(result.getType().equals(Material.LEATHER_LEGGINGS)){
      LeatherArmorMeta leatherMeta = (LeatherArmorMeta) result.getItemMeta();
      Color color = leatherMeta.getColor();
      if(!color.equals(Color.fromBGR(4053246))) return;
      meta.setDisplayName("" + ChatColor.BOLD + ChatColor.YELLOW + "Hazmat Pants");
      meta.setLore(lore);
    }
    else if(result.getType().equals(Material.LEATHER_BOOTS)){
      LeatherArmorMeta leatherMeta = (LeatherArmorMeta) result.getItemMeta();
      Color color = leatherMeta.getColor();
      if(!color.equals(Color.fromBGR(4053246))) return;
      meta.setDisplayName("" + ChatColor.BOLD + ChatColor.YELLOW + "Hazmat Boots");
      meta.setLore(lore);
    }
    result.setItemMeta(meta);
  }
}

