package dev.pretti.treasuresapi.mapping;

import dev.pretti.treasuresapi.contexts.BlockConditionMapContex;
import dev.pretti.treasuresapi.mapping.interfaces.IProcessMapping;
import dev.pretti.treasuresapi.mapping.types.BiomeConditionMap;
import dev.pretti.treasuresapi.mapping.types.BlockConditionMap;
import dev.pretti.treasuresapi.mapping.types.ProcessConditionMap;
import dev.pretti.treasuresapi.mapping.types.WorldConditionMap;
import dev.pretti.treasuresapi.processors.TreasuresProcessors;
import dev.pretti.treasuresapi.processors.interfaces.ITreasureProcessor;

import java.util.Collection;

public class BlockProcessMapping implements IProcessMapping<BlockConditionMapContex>
{
  private final TreasuresProcessors treasuresProcessors;

  private final WorldConditionMap<BlockConditionMap<BiomeConditionMap<ProcessConditionMap>>> worldConditionMap =
          WorldConditionMap.instance(
                  () -> BlockConditionMap.instance(
                          () -> BiomeConditionMap.instance(ProcessConditionMap::instance)));

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
    return worldConditionMap.evaluate(context) > 0;
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
