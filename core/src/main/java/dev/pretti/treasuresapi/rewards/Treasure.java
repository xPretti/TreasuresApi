package dev.pretti.treasuresapi.rewards;

import dev.pretti.treasuresapi.collections.IteratorList;
import dev.pretti.treasuresapi.conditions.Conditions;

public class Treasure
{
  private       String                     name;
  private       double                     chance;
  private       String                     permission;
  private       int                        limit;
  private       boolean                    random;
  private       Conditions                 conditions   = new Conditions();
  private final IteratorList<RewardsGroup> rewardsGroup = new IteratorList<>();

  /**
   * Construtor da classe
   */
  public Treasure()
  {
    this.name       = "Default";
    this.chance     = 100;
    this.permission = "";
    this.random     = false;
    this.limit      = 0;
  }

  /**
   * Definições e retornos
   */
  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public double getChance()
  {
    return chance;
  }

  public void setChance(double chance)
  {
    this.chance = chance;
  }

  public String getPermission()
  {
    return permission;
  }

  public void setPermission(String permission)
  {
    this.permission = permission;
  }

  public IteratorList<RewardsGroup> getRewardsGroup()
  {
    return rewardsGroup;
  }

  public int getLimit()
  {
    return limit;
  }

  public void setLimit(int limit)
  {
    this.limit = limit;
  }

  public boolean isRandom()
  {
    return random;
  }

  public void setRandom(boolean random)
  {
    this.random = random;
  }

  public Conditions getConditions()
  {
    return conditions;
  }
}
