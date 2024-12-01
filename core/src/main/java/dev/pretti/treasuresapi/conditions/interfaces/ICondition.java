package dev.pretti.treasuresapi.conditions.interfaces;

import dev.pretti.treasuresapi.conditions.InvalidCondition;
import dev.pretti.treasuresapi.processors.context.TreasureContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ICondition
{
  boolean evaluate(@NotNull TreasureContext context);

  @Nullable
  default InvalidCondition getInvalidCondition()
  {
    return (null);
  }
}
