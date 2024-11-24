package dev.pretti.treasuresapi.rewards;

import dev.pretti.treasuresapi.collections.IteratorList;
import org.jetbrains.annotations.NotNull;

public class RewardsGroup
{
  private       boolean               randomRewards;
  private       int                   limit;
  private       String                permission;
  private       double                chance;
  private final IteratorList<Rewards> rewards = new IteratorList<>();

  public RewardsGroup()
  {
    this.randomRewards = false;
    this.limit         = 0;
    this.permission    = "";
    this.chance        = 100;
  }

  /**
   * Definições e retornos
   */
  @NotNull
  public IteratorList<Rewards> getRewards()
  {
    return rewards;
  }

  public boolean isRandomRewards()
  {
    return randomRewards;
  }

  public void setRandomRewards(boolean randomRewards)
  {
    this.randomRewards = randomRewards;
  }

  public int getLimit()
  {
    return limit;
  }

  public void setLimit(int limit)
  {
    this.limit = limit;
  }

  public String getPermission()
  {
    return permission;
  }

  public void setPermission(String permission)
  {
    this.permission = permission;
  }

  public double getChance()
  {
    return chance;
  }

  public void setChance(double chance)
  {
    this.chance = chance;
  }
}
