package dev.pretti.treasuresapi.rewards.types;


import dev.pretti.treasuresapi.dynamics.IntDynamic;
import dev.pretti.treasuresapi.enums.EnumRewardType;

public class XpReward extends Reward
{
  private boolean    isLevel;
  private IntDynamic xp;

  /**
   * Construtor da classe
   */
  public XpReward()
  {
    super(EnumRewardType.XP);

    isLevel = false;
  }

  public XpReward(boolean level)
  {
    super(EnumRewardType.XP);

    isLevel = level;
  }

  public XpReward(boolean level, IntDynamic xp)
  {
    super(EnumRewardType.XP);

    isLevel = level;
    this.xp = xp;
  }

  /**
   * Definição das propiedadas
   */
  public boolean isLevel()
  {
    return isLevel;
  }

  public void setIsLevel(boolean isLevel)
  {
    this.isLevel = isLevel;
  }

  public IntDynamic getXp()
  {
    return xp;
  }

  public void setXp(IntDynamic xp)
  {
    this.xp = xp;
  }
}
