package dev.pretti.treasuresapi.mapping.types;

import dev.pretti.treasuresapi.contexts.interfaces.IConditionMapContex;
import dev.pretti.treasuresapi.mapping.interfaces.IConditionMap;
import dev.pretti.treasuresapi.processors.interfaces.ITreasureProcessor;
import dev.pretti.treasuresapi.rewards.Treasure;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ProcessConditionMap implements IConditionMap
{
  private final List<ITreasureProcessor> processors = new ArrayList<>();

  /**
   * Método de carregamento
   */
  @Override
  public IConditionMap load(ITreasureProcessor processor)
  {
    Treasure treasure = processor.getTreasure();
    if(treasure == null)
      {
        return this;
      }
    processors.add(processor);
    return this;
  }

  /**
   * Método de processamento
   */
  @Override
  public int evaluate(@NotNull IConditionMapContex context)
  {
    return evaluate(context, 0);
  }

  @Override
  public int evaluate(@NotNull IConditionMapContex context, int count)
  {
    int limit = context.getEvaluateLimit();
    if(count >= limit)
      {
        return count;
      }
    for(ITreasureProcessor processor : processors)
      {
        if(processor.process(context.getTreasureContext()))
          {
            count++;
            if(count >= limit)
              {
                return count;
              }
          }
      }
    return count;
  }

  /**
   * Retorna a instância da classe
   */
  public static ProcessConditionMap instance()
  {
    return new ProcessConditionMap();
  }
}
