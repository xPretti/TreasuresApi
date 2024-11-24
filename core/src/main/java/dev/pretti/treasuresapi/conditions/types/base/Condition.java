package dev.pretti.treasuresapi.conditions.types.base;


import dev.pretti.treasuresapi.enums.EnumConditionType;

public class Condition
{
  private EnumConditionType type;

  /**
  * Construtor da classe
  */
  public Condition(EnumConditionType type)
  {
    this.type = type;
  }

  /**
   * Métodos de definições
   */
  public EnumConditionType getType()
  {
    return type;
  }

}
