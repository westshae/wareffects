package com.shaewest.wareffects.Tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.shaewest.wareffects.Extra.CustomEffects;

public class NukeTasks extends BukkitRunnable {
  @Override
  public void run() {
    removeChunks();
    handleEffects();
  }

  private void removeChunks() {
    FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("wareffects").getConfig();

    ConfigurationSection chunksSection = config.getConfigurationSection("nuke.chunks");

    if (chunksSection != null) {
      for (String chunkKey : chunksSection.getKeys(false)) {
        Integer lives = config.getInt("nuke.chunks." + chunkKey + ".lives");

        if (lives == 0) {
          config.set("nuke.chunks." + chunkKey, null);
        } else {
          lives -= 1;
          config.set("nuke.chunks." + chunkKey + ".lives", lives);
        }
      }
    }

    Plugin wareffectsPlugin = Bukkit.getServer().getPluginManager().getPlugin("wareffects");
    if (wareffectsPlugin != null) {
      wareffectsPlugin.saveConfig();
    }
  }

  private void handleEffects() {
    FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("wareffects").getConfig();

    ConfigurationSection chunksSection = config.getConfigurationSection("nuke.chunks");

    List<String> coordinatesList = new ArrayList<>();

    if (chunksSection != null) {
      for (String chunkKey : chunksSection.getKeys(false)) {
        String[] coordinates = chunkKey.split(":");
        if (coordinates.length == 2) {
          String coordinateString = coordinates[0] + ":" + coordinates[1];
          coordinatesList.add(coordinateString);
        }
      }
    }
    ArrayList<Entity> entities = new ArrayList<>();

    for (String coordinate : coordinatesList) {

      String[] split = coordinate.split(":");
      int x = Integer.parseInt(split[0]);
      int y = Integer.parseInt(split[1]);

      Chunk chunk = Bukkit.getServer().getWorld("world").getChunkAt(x, y);

      if (!chunk.isLoaded())
        continue;

      entities.addAll(Arrays.asList(chunk.getEntities()));

      chunkParticles(chunk);
    }

    ArrayList<Player> players = new ArrayList<>();
    for (Entity entity : entities) {
      if (entity instanceof Player) {
        players.add((Player) entity);
      }
    }

    for (Player player : players) {
      CustomEffects.handleRadiation(player);
    }

  }

  private void chunkParticles(Chunk chunk) {
    int startX = chunk.getX() << 4; // Multiply by 16 to get the block coordinate of the chunk's origin
    int startZ = chunk.getZ() << 4;

    World world = chunk.getWorld();

    for (int x = startX; x < startX + 16; x += 4) {
      for (int z = startZ; z < startZ + 16; z += 4) {
        // Find the highest grass block in the column
        int highestGrassY = findHighestGrassY(world, x, z);

        // Spawn particles 2 blocks above the highest grass block
        if (highestGrassY != -1) {
          Location particleLocation = new Location(world, x + 0.5, highestGrassY + 2, z + 0.5);
          world.spawnParticle(Particle.SLIME, particleLocation, 10);
        }
      }
    }
  }

  private int findHighestGrassY(World world, int x, int z) {
    int maxY = world.getMaxHeight() - 1;
    for (int y = maxY; y >= 0; y--) {
      Block block = world.getBlockAt(x, y, z);
      if (block.getType() == Material.GRASS_BLOCK) {
        return y;
      }
    }
    return -1; // Return -1 if no grass block is found
  }

}
