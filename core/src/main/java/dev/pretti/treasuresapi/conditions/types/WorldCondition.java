package dev.pretti.treasuresapi.conditions.types;

import dev.pretti.treasuresapi.enums.EnumConditionType;
import dev.pretti.treasuresapi.conditions.types.base.AccessCondition;

public class WorldCondition extends AccessCondition
{
  /**
   * Construtor da classe
   */
  public WorldCondition()
  {
    super(EnumConditionType.WORLD);
  }
}
