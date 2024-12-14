package dev.pretti.treasuresapi.processors;

import dev.pretti.treasuresapi.contexts.TreasureContext;
import dev.pretti.treasuresapi.processors.interfaces.ITreasureBuilder;
import dev.pretti.treasuresapi.processors.interfaces.ITreasureProcessor;
import dev.pretti.treasuresapi.rewards.Treasure;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TreasuresProcessors
{
  private final Map<String, ITreasureProcessor> treasures = new HashMap<>();

  /**
   * Retornos dos processadores
   */
  public Collection<ITreasureProcessor> getProcessors()
  {
    return treasures.values();
  }

  /**
   * Métodos de processamento de tesouros
   */
  public boolean processAll(TreasureContext context, int limit)
  {
    if(treasures.isEmpty())
      {
        return false;
      }
    int count = 0;
    for(ITreasureProcessor processor : treasures.values())
      {
        if(count >= limit)
          {
            break;
          }
        if(processor.process(context))
          {
            count++;
          }
      }
    Bukkit.getLogger().info(" Processados " + count + " tesouros" + " total de tesouros que tentaram processar: " + context.getRemoverDepois());
    return count > 0;
  }

  public boolean process(String treasureName, TreasureContext context)
  {
    ITreasureProcessor processor = treasures.get(treasureName);
    return processor != null && processor.process(context);
  }

  /**
   * Carrega os tesouros e seus processadores.
   */
  public boolean load(List<Treasure> treasuresList, @NotNull ITreasureBuilder builder)
  {
    treasures.clear();
    if(treasuresList == null || treasuresList.isEmpty())
      {
        return false;
      }
    for(Treasure treasure : treasuresList)
      {
        ITreasureProcessor processor = builder.build(treasure);
        if(processor != null)
          {
            treasures.put(treasure.getName(), processor);
          }
      }
    return !treasures.isEmpty();
  }
}
