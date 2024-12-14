package dev.pretti.treasuresapi.datatypes.commands;

import dev.pretti.treasuresapi.enums.EnumCommandType;

public class RangeTextCommandType extends TextCommandType
{
  private double range;

  /**
   * Construtor da classe
   */
  public RangeTextCommandType(EnumCommandType type, double range, String text)
  {
    super(type, text);
    this.range = range;
  }

  /**
   * Métodos de retornos
   */
  public double getRange()
  {
    return range;
  }

  public void setRange(double range)
  {
    this.range = range;
  }
}
