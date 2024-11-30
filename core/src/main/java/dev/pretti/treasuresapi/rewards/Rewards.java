package dev.pretti.treasuresapi.rewards;

import dev.pretti.treasuresapi.rewards.Options.RewardOptions;
import dev.pretti.treasuresapi.rewards.types.Reward;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Rewards
{
  private double        chance;
  private String        permission;
  private RewardOptions options;
  private List<Reward>  rewards = new ArrayList<>();

  /**
   * Contrutor da classe
   */
  public Rewards()
  {
    this(100D, "", new RewardOptions());
  }

  public Rewards(double chance, String permission, RewardOptions options)
  {
    this.chance     = chance;
    this.options    = options;
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

  @NotNull
  public RewardOptions getOptions()
  {
    return options;
  }

  public void setOptions(@NotNull RewardOptions options)
  {
    this.options = options;
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
