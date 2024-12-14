package dev.pretti.treasuresapi.mapping;

import dev.pretti.treasuresapi.contexts.BlockConditionMapContex;
import dev.pretti.treasuresapi.mapping.interfaces.IProcessMapping;
import dev.pretti.treasuresapi.mapping.types.ProcessConditionMap;
import dev.pretti.treasuresapi.mapping.types.WorldConditionMap;
import dev.pretti.treasuresapi.processors.TreasuresProcessors;
import dev.pretti.treasuresapi.processors.interfaces.ITreasureProcessor;
import org.bukkit.Bukkit;

import java.util.Collection;

public class BlockProcessMapping implements IProcessMapping<BlockConditionMapContex>
{
  private final TreasuresProcessors treasuresProcessors;

  private final WorldConditionMap<ProcessConditionMap> worldConditionMap = WorldConditionMap.instance(ProcessConditionMap::instance);

  /**
   * Contrutor da classe
   */
  public BlockProcessMapping(TreasuresProcessors treasuresProcessors)
  {
    this.treasuresProcessors = treasuresProcessors;

    load();
  }

  /**
   * Método de processamento
   */
  @Override
  public boolean process(BlockConditionMapContex context)
  {
    int count = worldConditionMap.evaluate(context);

    Bukkit.getLogger().info("Processados " + count + " tesouros" + " total de tesouros que tentaram processar: " + context.getTreasureContext().getRemoverDepois());

    return count > 0;
  }

  /**
   * Método de carregamento
   */
  private void load()
  {
    Collection<ITreasureProcessor> processors = treasuresProcessors.getProcessors();
    for(ITreasureProcessor processor : processors)
      {
        worldConditionMap.load(processor);
      }
  }
}
