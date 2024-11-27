package dev.pretti.treasuresapi;

import dev.pretti.treasuresapi.conditions.interfaces.IConditionsBuilder;
import dev.pretti.treasuresapi.loaders.TreasuresLoader;
import dev.pretti.treasuresapi.processors.TreasuresProcessors;
import dev.pretti.treasuresapi.processors.interfaces.ITreasureBuilder;
import dev.pretti.treasuresapi.rewards.Treasure;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TreasuresApi
{
  /**
   * Carrega e retorna a lista de tesouros
   */
  public static List<Treasure> loader(@NotNull String treasureFolder, @NotNull IConditionsBuilder conditionsBuilder) throws IllegalArgumentException
  {
    return new TreasuresLoader(conditionsBuilder).loader(treasureFolder);
  }

  /**
   * Carrega e retorna os processadores de tesouros
   */
  public static TreasuresProcessors loader(@NotNull String treasureFolder, @NotNull ITreasureBuilder builder, @NotNull IConditionsBuilder conditionsBuilder) throws IllegalArgumentException
  {
    TreasuresProcessors treasures = new TreasuresProcessors();
    treasures.load(loader(treasureFolder, conditionsBuilder), builder);
    return treasures;
  }
}
