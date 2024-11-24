package dev.pretti.treasuresapi;

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
  public static List<Treasure> loader(String treasureFolder)
  {
    return new TreasuresLoader().loader(treasureFolder);
  }

  /**
  * Carrega e retorna os processadores de tesouros
  */
  public static TreasuresProcessors loader(String treasureFolder, @NotNull ITreasureBuilder builder)
  {
    TreasuresProcessors treasures = new TreasuresProcessors();
    treasures.load(loader(treasureFolder), builder);
    return treasures;
  }
}
