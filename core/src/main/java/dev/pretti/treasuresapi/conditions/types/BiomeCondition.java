package dev.pretti.treasuresapi.conditions.types;

import dev.pretti.treasuresapi.enums.EnumConditionType;
import dev.pretti.treasuresapi.conditions.types.base.AccessCondition;

public class BiomeCondition extends AccessCondition
{
  /**
   * Construtor da classe
   */
  public BiomeCondition()
  {
    super(EnumConditionType.BIOME);
  }

}
