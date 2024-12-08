package dev.pretti.treasuresapi;

import dev.pretti.treasuresapi.conditions.interfaces.IConditionsBuilder;
import dev.pretti.treasuresapi.loaders.TreasuresLoader;
import dev.pretti.treasuresapi.rewards.Treasure;
import dev.pretti.treasuresapi.throwz.InvalidTreasuresLoaderException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TreasuresApi
{
  /**
   * Carrega e retorna a lista de tesouros
   */
  public static List<Treasure> loader(@NotNull String treasureFolder, @NotNull IConditionsBuilder conditionsBuilder) throws InvalidTreasuresLoaderException
  {
    return new TreasuresLoader(conditionsBuilder).loader(treasureFolder);
  }
}
