package dev.pretti.treasuresapi.datatypes.commands;

import dev.pretti.treasuresapi.enums.EnumParseType;

public class CommandType
{
  private EnumParseType type;

  public CommandType(EnumParseType type)
  {
    this.type = type;
  }

  /**
   * Retornos
   */
  public EnumParseType getType()
  {
    return type;
  }

}
