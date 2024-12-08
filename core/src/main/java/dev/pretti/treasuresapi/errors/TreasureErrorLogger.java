package dev.pretti.treasuresapi.errors;

import dev.pretti.treasuresapi.errors.interfaces.ITreasureErrors;
import dev.pretti.treasuresapi.errors.interfaces.ITreasureErrorLogger;
import dev.pretti.treasuresapi.errors.types.TreasureErrors;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class TreasureErrorLogger implements ITreasureErrorLogger
{
  private final HashMap<String, ITreasureErrors> errors = new HashMap<>();


  /**
   * Retornos
   */
  @Override
  public int getTotal()
  {
    return errors.size();
  }

  @Override
  public Collection<ITreasureErrors> getErrors()
  {
    return errors.values();
  }

  @Override
  public ITreasureErrors getSection(String section)
  {
    return errors.get(section);
  }

  @Override
  public void add(String section, String value, String message)
  {
    ITreasureErrors error = getOrCreateSection(section);
    error.add(value, message);
  }

  @Override
  public void add(String section, List<String> values, String message)
  {
    ITreasureErrors error = getOrCreateSection(section);
    error.add(values, message);
  }

  /**
   * Retornos privados
   */
  private ITreasureErrors getOrCreateSection(String section)
  {
    ITreasureErrors error = getSection(section);
    if(error == null)
      {
        error = new TreasureErrors(section);
        this.errors.put(section, error);
      }
    return error;
  }
}
