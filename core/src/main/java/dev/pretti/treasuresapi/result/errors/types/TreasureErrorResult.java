package dev.pretti.treasuresapi.result.errors.types;

import dev.pretti.treasuresapi.result.errors.interfaces.ITreasureErrorResult;

public class TreasureErrorResult implements ITreasureErrorResult
{
  private final String identifier;
  private final String error;

  /**
   * Construtor da classe
   */
  public TreasureErrorResult(String identifier, String error)
  {
    this.identifier = identifier;
    this.error     = error;
  }

  /**
   * Implementações
   */
  public String getIdentifier()
  {
    return identifier;
  }

  public String getError()
  {
    return error;
  }
}
