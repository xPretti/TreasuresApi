package dev.pretti.treasuresapi.processors.interfaces.outputs;

import dev.pretti.treasuresapi.conditions.types.base.Condition;
import dev.pretti.treasuresapi.processors.context.TreasureContext;
import org.jetbrains.annotations.NotNull;

public interface IConditionOutput
{
  boolean process(@NotNull TreasureContext context, Condition condition);
}
