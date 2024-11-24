package dev.pretti.treasuresapi.utils;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

public class MathUtils
{

  /**
   * Conversores de localização para Vector3
   */
  public static Vector toVector(Location location1, Entity location2)
  {
    Location location = location2.getLocation();
    return (toVector(location1, location));
  }

  public static Vector toVector(Location location1, Location location2)
  {
    double d0 = location1.getX() - location2.getX();
    double d1 = location1.getY() - location2.getY();
    double d2 = location1.getZ() - location2.getZ();
    return (new Vector(d0 * 0.1D, d1 * 0.1D + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.08D, d2 * 0.1D));
  }

  /**
   * Métodos de conversão de chances
   */
  public static boolean isChance(double chance)
  {
    return (isChance(0.01, 100, chance));
  }

  public static boolean isChance(double min, double max, double chance)
  {
    double newChance = getChance(min, max);
    return (chance >= newChance);
  }

  public static double getChance(double min, double max)
  {
    return (min + (Math.random() * (max - min)));
  }

  /**
   * Retorna um numero aletório
   */
  public static int getRandom(int start, int end)
  {
    if(++end > start)
      {
        return (ThreadLocalRandom.current().nextInt(start, end));
      }
    return (start);
  }

  public static double getRandom(double start, double end)
  {
    if(end > start)
      {
        return (ThreadLocalRandom.current().nextDouble(start, end));
      }
    return (start);
  }
}
