package dev.pretti.treasuresapi.datatypes.commands;

import dev.pretti.treasuresapi.datatypes.commands.base.CommandType;
import dev.pretti.treasuresapi.enums.EnumCommandType;
import dev.pretti.treasuresapi.enums.EnumEffectActionType;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EffectCommandType extends CommandType
{
  private EnumEffectActionType actionType;
  private PotionEffectType     effectType;
  private int                  duration;
  private int                  level;

  /**
   * Construtor da classe
   */
  public EffectCommandType()
  {
    this(EnumEffectActionType.CLEAR, null, 0, 0);
  }

  public EffectCommandType(@NotNull EnumEffectActionType actionType, @Nullable PotionEffectType effectType, int duration, int level)
  {
    super(EnumCommandType.EFFECT);

    this.actionType = actionType;
    this.effectType = effectType;
    this.duration   = duration;
    this.level      = level;
  }

  /**
   * Métodos de retornos
   */
  public EnumEffectActionType getActionType()
  {
    return actionType;
  }

  public void setActionType(EnumEffectActionType actionType)
  {
    this.actionType = actionType;
  }

  public PotionEffectType getEffectType()
  {
    return effectType;
  }

  public void setEffectType(PotionEffectType effectType)
  {
    this.effectType = effectType;
  }

  public int getDuration()
  {
    return duration;
  }

  public void setDuration(Integer duration)
  {
    this.duration = duration;
  }

  public int getLevel()
  {
    return level;
  }

  public void setLevel(Integer level)
  {
    this.level = level;
  }
}
