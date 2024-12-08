package dev.pretti.treasuresapi.errors.types;

import dev.pretti.treasuresapi.errors.interfaces.ITreasureError;
import dev.pretti.treasuresapi.errors.interfaces.ITreasureErrors;

import java.util.ArrayList;
import java.util.List;

public class TreasureErrors implements ITreasureErrors
{
  private final String               section;
  private final List<ITreasureError> errors = new ArrayList<>();

  /**
   * Construtor da classe
   */
  public TreasureErrors(String section)
  {
    this.section = section;
  }

  /**
   * Retornos
   */
  @Override
  public String getSection()
  {
    return section;
  }

  @Override
  public List<ITreasureError> getErrors()
  {
    return errors;
  }

  @Override
  public void add(String value, String message)
  {
    errors.add(new TreasureError(value, message));
  }

  @Override
  public void add(List<String> values, String message)
  {
    values.stream().map(value -> new TreasureError(value, message)).forEach(errors::add);
  }

}
