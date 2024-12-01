package dev.pretti.treasuresapi;

import dev.pretti.treasuresapi.conditions.interfaces.IConditionsBuilder;
import dev.pretti.treasuresapi.loaders.TreasuresLoader;
import dev.pretti.treasuresapi.processors.TreasuresProcessors;
import dev.pretti.treasuresapi.processors.interfaces.ITreasureBuilder;
import dev.pretti.treasuresapi.result.TreasureProcessorsResult;
import dev.pretti.treasuresapi.result.interfaces.ITreasureResult;
import org.jetbrains.annotations.NotNull;

public class TreasuresApi
{
  /**
   * Carrega e retorna a lista de tesouros
   */
  public static ITreasureResult loader(@NotNull String treasureFolder, @NotNull IConditionsBuilder conditionsBuilder)
  {
    return new TreasuresLoader(conditionsBuilder).loader(treasureFolder);
  }

  /**
   * Carrega e retorna os processadores de tesouros
   */
  @NotNull
  public static TreasureProcessorsResult loader(@NotNull String treasureFolder, @NotNull ITreasureBuilder builder, @NotNull IConditionsBuilder conditionsBuilder)
  {
    TreasuresProcessors treasures      = new TreasuresProcessors();
    ITreasureResult     treasureResult = loader(treasureFolder, conditionsBuilder);

    TreasureProcessorsResult treasureProcessorsResult = new TreasureProcessorsResult(treasures, treasureResult);

    treasures.load(treasureResult.getTreasures(), builder);

    return treasureProcessorsResult;
  }
}
