package dev.pretti.treasuresapi.rewards.types;


import dev.pretti.treasuresapi.enums.EnumRewardType;

public abstract class Reward
{
  private final EnumRewardType type;

  /**
  * Construtor da classe
  */
  public Reward(EnumRewardType type)
  {
    this.type = type;
  }

  /**
  * Métodos de retornos
  */
  public EnumRewardType getType()
  {
    return type;
  }
}
