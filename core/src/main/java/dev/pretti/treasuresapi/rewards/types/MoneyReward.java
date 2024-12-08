package dev.pretti.treasuresapi.rewards.types;


import dev.pretti.treasuresapi.dynamics.DoubleDynamic;
import dev.pretti.treasuresapi.enums.EnumRewardType;

public class MoneyReward extends Reward
{
  private DoubleDynamic money;

  /**
   * Construtor da classe
   */
  public MoneyReward()
  {
    super(EnumRewardType.MONEY);
  }

  public MoneyReward(DoubleDynamic value)
  {
    super(EnumRewardType.XP);
    this.money = value;
  }

  /**
   * Definição das propiedadas
   */
  public DoubleDynamic getMoney()
  {
    return money;
  }

  public void setMoney(DoubleDynamic money)
  {
    this.money = money;
  }
}
