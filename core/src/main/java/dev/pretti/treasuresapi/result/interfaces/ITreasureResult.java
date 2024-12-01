package dev.pretti.treasuresapi.result.interfaces;

import dev.pretti.treasuresapi.result.errors.interfaces.ITreasureErrorResult;
import dev.pretti.treasuresapi.rewards.Treasure;

import java.util.Collection;
import java.util.List;

public interface ITreasureResult
{
  List<Treasure> getTreasures();

  Collection<ITreasureErrorResult> getErrors();
}
