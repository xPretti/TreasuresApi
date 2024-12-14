package dev.pretti.treasuresapi.contexts.interfaces;

import dev.pretti.treasuresapi.contexts.TreasureContext;

public interface IConditionMapContex
{
  TreasureContext getTreasureContext();

  int getEvaluateLimit();

}
