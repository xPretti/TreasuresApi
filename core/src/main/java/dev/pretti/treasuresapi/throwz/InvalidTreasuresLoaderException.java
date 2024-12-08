package dev.pretti.treasuresapi.throwz;

import dev.pretti.treasuresapi.errors.interfaces.ITreasureErrorLogger;
import dev.pretti.treasuresapi.rewards.Treasure;

import java.util.List;

public class InvalidTreasuresLoaderException extends Exception
{
  private final List<Treasure>       loadedTreasures;
  private final ITreasureErrorLogger treasureErros;

  /**
   * Contrutor da classe
   */
  public InvalidTreasuresLoaderException(String message, ITreasureErrorLogger treasureErros, List<Treasure> loadedTreasures)
  {
    super(message);
    this.treasureErros   = treasureErros;
    this.loadedTreasures = loadedTreasures;
  }

  /**
   * Retorna a classe de erros
   */
  public ITreasureErrorLogger getTreasureErros()
  {
    return treasureErros;
  }

  public List<Treasure> getLoadedTreasures()
  {
    return loadedTreasures;
  }
}
