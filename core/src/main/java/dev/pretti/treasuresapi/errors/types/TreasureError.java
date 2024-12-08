package dev.pretti.treasuresapi.errors.types;

import dev.pretti.treasuresapi.errors.interfaces.ITreasureError;

public class TreasureError implements ITreasureError
{
  private String value;
  private String message;

  /**
   * Contrutor da classe
   */
  public TreasureError()
  {
    this("", "");
  }

  public TreasureError(String value, String message)
  {
    this.value   = value;
    this.message = message;
  }

  /**
   * Retornos
   */
  @Override
  public String getValue()
  {
    return value;
  }

  public void setValue(String value)
  {
    this.value = value;
  }

  @Override
  public String getMessage()
  {
    return message;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }
}
