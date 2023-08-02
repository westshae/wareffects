package com.shaewest.wareffects.Commands;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import com.shaewest.wareffects.App;
import com.shaewest.wareffects.Extra.Util;

public class RegionalWeapons implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!command.getName().equalsIgnoreCase("regionalweapons")) return true;
    if(args.length != 1) return true;

    String weaponType = args[0].toLowerCase();
    if(weaponType.equals("list")){
      if (!sender.hasPermission("wareffects.regionalweapons.list")) {
        Util.sendErrorMessage((Player)sender, "You have no permission to run this command.");
        return true;
      }

      handleListCommand((Player) sender);
      return true;
    }

    if(weaponType.contains("nuke")) {
      if (!sender.hasPermission("wareffects.regionalweapons.nuke")){
        Util.sendErrorMessage((Player)sender, "You have no permission to buy nukes.");
        return true;
      }

      purchaseNuke((Player) sender);
    }
    if(weaponType.contains("gas")) {
      if (!sender.hasPermission("wareffects.regionalweapons.gas")) {
        Util.sendErrorMessage((Player)sender, "You have no permission to buy gas.");
        return true;
      }

      purchaseGas((Player) sender);
    }

    return true;
  }

  private void handleListCommand(Player player){
    FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("wareffects").getConfig();
    double nukePrice = config.getDouble("nuke.price");
    double gasPrice = config.getDouble("gas.price");

    player.sendMessage("" + ChatColor.BOLD + ChatColor.GREEN + "=====War-Effects=====");
    player.sendMessage("" + ChatColor.BOLD + ChatColor.GREEN + "     Nuke: $" + nukePrice);
    player.sendMessage("" + ChatColor.BOLD + ChatColor.GREEN + "     Gas: $" + gasPrice);
    player.sendMessage("" + ChatColor.BOLD + ChatColor.GREEN + "=====================");
  }

  private boolean playerCanAfford(Player player, double price){
    double playerBalance = App.economy.getBalance(player);
    if(playerBalance < price) return false;
    return true;
  }

  private void playerWithdrawPrice(Player player, double amount){
    App.economy.withdrawPlayer(player, amount);
  }

  private boolean purchaseNuke(Player player){
    FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("wareffects").getConfig();

    double price = config.getDouble("nuke.price");
    if(!playerCanAfford(player, price)) {
      Util.sendErrorMessage(player, "You cannot afford the price of " + price);
      return false;
    }

    if(!Util.hasEmptyInventorySlot(player)){
      Util.sendErrorMessage(player, "You have no inventory space");
      return false;
    }

    playerWithdrawPrice(player, price);

    ItemStack item = new ItemStack(Material.REDSTONE_TORCH);
    ItemMeta meta = item.getItemMeta();

    String[] lore = {
      ("" + ChatColor.RED + "Right click this item to launch a nuke at this location."),
      ("" + ChatColor.RED + "This will cause lingering effects for those within the chunk.")
    };

    ArrayList<String> loreList = new ArrayList<>();
    loreList.addAll(Arrays.asList(lore));

    meta.setDisplayName("" + ChatColor.RED + ChatColor.BOLD + "Nuke Beacon");
    meta.setLore(loreList);
    meta.setUnbreakable(true);
    NamespacedKey customDataKey = new NamespacedKey(Bukkit.getServer().getPluginManager().getPlugin("wareffects"), "nuke");
    meta.getPersistentDataContainer().set(customDataKey, PersistentDataType.STRING, "nuke");

    item.setItemMeta(meta);

    player.getInventory().addItem(item);
    return true;
  }

  private boolean purchaseGas(Player player){
    FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("wareffects").getConfig();

    double price = config.getDouble("gas.price");
    if(!playerCanAfford(player, price)) {
      Util.sendErrorMessage(player, "You cannot afford the price of " + price);
      return false;
    }

    if(!Util.hasEmptyInventorySlot(player)){
      Util.sendErrorMessage(player, "You have no inventory space");
      return false;
    }

    playerWithdrawPrice(player, price);

    ItemStack item = new ItemStack(Material.REDSTONE_TORCH);
    ItemMeta meta = item.getItemMeta();

    String[] lore = {
      ("" + ChatColor.RED + "Right click this item to cause a gas attack at this location."),
      ("" + ChatColor.RED + "This will cause lingering effects for those within the chunk.")
    };

    ArrayList<String> loreList = new ArrayList<>();
    loreList.addAll(Arrays.asList(lore));

    meta.setDisplayName("" + ChatColor.RED + ChatColor.BOLD + "Gas Beacon");
    meta.setLore(loreList);
    meta.setUnbreakable(true);
    NamespacedKey customDataKey = new NamespacedKey(Bukkit.getServer().getPluginManager().getPlugin("wareffects"), "gas");
    meta.getPersistentDataContainer().set(customDataKey, PersistentDataType.STRING, "gas");


    item.setItemMeta(meta);

    player.getInventory().addItem(item);
    return true;
  }
}
