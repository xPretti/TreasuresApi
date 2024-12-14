package dev.pretti.treasuresapi.datatypes.commands.base;

import dev.pretti.treasuresapi.enums.EnumCommandType;

public abstract class CommandType
{
  private final EnumCommandType type;

  public CommandType(EnumCommandType type)
  {
    this.type = type;
  }

  /**
   * Retornos
   */
  public EnumCommandType getType()
  {
    return type;
  }

}
