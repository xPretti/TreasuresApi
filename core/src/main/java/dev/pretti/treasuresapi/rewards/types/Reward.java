package dev.pretti.treasuresapi.rewards.types;


import dev.pretti.treasuresapi.enums.EnumRewardType;
import org.jetbrains.annotations.NotNull;

public abstract class Reward
{
  private final EnumRewardType type;

  /**
   * Construtor da classe
   */
  public Reward(@NotNull EnumRewardType type)
  {
    this.type = type;
  }

  /**
   * Métodos de retornos
   */
  @NotNull
  public EnumRewardType getType()
  {
    return type;
  }
}
