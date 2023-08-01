package com.altoya.wareffects;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegionalWeapons implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!command.getName().equalsIgnoreCase("regionalweapons")) return false;
    if (sender.hasPermission("wareffects.regionalweapons.use")) return false;
    if(args.length != 1) return false;

    String weaponType = args[0].toLowerCase();
    if(weaponType.equals("list")){
      handleListCommand((Player) sender);
      return true;
    }

    if(weaponType.contains("nuke")) purchaseNuke((Player) sender);
    if(weaponType.contains("gas")) purchaseGas((Player) sender);

    return true;
  }

  private void handleListCommand(Player player){
    player.sendMessage("" + ChatColor.BOLD + ChatColor.GREEN + "=====War-Effects=====");
    player.sendMessage("" + ChatColor.BOLD + ChatColor.GREEN + "Nuke: $1000");
    player.sendMessage("" + ChatColor.BOLD + ChatColor.GREEN + "Gas: $1000");
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
    double price = 1000.0;
    if(!playerCanAfford(player, price)) {
      Util.sendErrorMessage(player, "You cannot afford the price of " + price);
      return false;
    }

    if(!Util.hasEmptyInventorySlot(player)){
      Util.sendErrorMessage(player, "You have no inventory space");
      return false;
    }

    playerWithdrawPrice(player, price);

    //TODO GIVE ITEM
    return true;
  }

  private boolean purchaseGas(Player player){
    double price = 1000.0;
    if(!playerCanAfford(player, price)) {
      Util.sendErrorMessage(player, "You cannot afford the price of " + price);
      return false;
    }

    if(!Util.hasEmptyInventorySlot(player)){
      Util.sendErrorMessage(player, "You have no inventory space");
      return false;
    }

    playerWithdrawPrice(player, price);

    //TODO GIVE ITEM
    return true;
  }
}
