package dev.pretti.treasuresapi.utils;

import java.util.concurrent.ThreadLocalRandom;

public class MathUtils
{
  /**
   * Métodos de conversão de chances
   */
  public static boolean isChance(double chance)
  {
    return chance >= (Math.random() * 100);
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
