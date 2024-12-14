package dev.pretti.treasuresapi.mapping.interfaces;

import dev.pretti.treasuresapi.contexts.interfaces.IConditionMapContex;
import dev.pretti.treasuresapi.processors.interfaces.ITreasureProcessor;
import org.jetbrains.annotations.NotNull;

public interface IConditionMap
{
  IConditionMap load(ITreasureProcessor processor);

  int evaluate(@NotNull IConditionMapContex context);

  int evaluate(@NotNull IConditionMapContex context, int count);
}
