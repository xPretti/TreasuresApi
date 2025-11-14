package dev.pretti.treasuresapi.rewards;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RewardsGroup
{
  private       boolean       randomRewards = false;
  private       int           limit         = 0;
  private       String        permission    = "";
  private       double        chance        = 100;
  private final List<Rewards> rewards       = new ArrayList<>();

  /**
   * Definições e retornos
   */
  @NotNull
  public List<Rewards> getRewards() {
    return rewards;
  }

  public boolean isRandomRewards() {
    return randomRewards;
  }

  public void setRandomRewards(boolean randomRewards) {
    this.randomRewards = randomRewards;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public String getPermission() {
    return permission;
  }

  public void setPermission(String permission) {
    this.permission = permission;
  }

  public double getChance() {
    return chance;
  }

  public void setChance(double chance) {
    this.chance = chance;
  }
}
