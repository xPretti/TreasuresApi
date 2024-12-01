package dev.pretti.treasuresapi.result;

import dev.pretti.treasuresapi.processors.TreasuresProcessors;
import dev.pretti.treasuresapi.result.interfaces.ITreasureResult;

public class TreasureProcessorsResult
{
  private final TreasuresProcessors treasuresProcessors;
  private final ITreasureResult     treasureResult;

  public TreasureProcessorsResult(TreasuresProcessors treasuresProcessors, ITreasureResult treasureResult)
  {
    this.treasuresProcessors = treasuresProcessors;
    this.treasureResult      = treasureResult;
  }

  public TreasuresProcessors getTreasuresProcessors()
  {
    return treasuresProcessors;
  }

  public ITreasureResult getTreasureResult()
  {
    return treasureResult;
  }
}
