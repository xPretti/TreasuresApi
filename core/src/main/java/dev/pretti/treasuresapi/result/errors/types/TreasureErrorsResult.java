package dev.pretti.treasuresapi.result.errors.types;

import java.util.List;

public class TreasureErrorsResult extends TreasureErrorResult
{
  private final List<String> errors;

  /**
  * Construtor da classe
  */
  public TreasureErrorsResult(String identifier, String error, List<String> errors)
  {
    super(identifier, error);
    this.errors     = errors;
  }

  /**
  * Implementações
  */
  public List<String> getErrors()
  {
    return errors;
  }
}
