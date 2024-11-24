package dev.pretti.treasuresapi.rewards;

import dev.pretti.treasuresapi.rewards.types.Reward;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Rewards
{
  private double       chance;
  private boolean      useLooting;
  private String       permission;
  private List<Reward> rewards = new ArrayList<>();

  /**
   * Contrutor da classe
   */
  public Rewards()
  {
    this.chance     = 100;
    this.useLooting = false;
    this.permission = "";
  }

  public Rewards(double chance, boolean useLooting, String permission)
  {
    this.chance     = chance;
    this.useLooting = useLooting;
    this.permission = permission;
  }

  /**
   * Definições e retornos
   */
  public double getChance()
  {
    return chance;
  }

  public void setChance(double chance)
  {
    this.chance = chance;
  }

  public boolean isUseLooting()
  {
    return useLooting;
  }

  public void setUseLooting(boolean useLooting)
  {
    this.useLooting = useLooting;
  }

  public String getPermission()
  {
    return permission;
  }

  public void setPermission(String permission)
  {
    this.permission = permission;
  }

  @NotNull
  public List<Reward> getRewards()
  {
    return rewards;
  }

  public void setRewards(List<Reward> rewards)
  {
    this.rewards = rewards;
  }
}
