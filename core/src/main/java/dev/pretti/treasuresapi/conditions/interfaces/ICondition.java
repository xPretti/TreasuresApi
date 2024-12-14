package dev.pretti.treasuresapi.conditions.interfaces;

import dev.pretti.treasuresapi.contexts.TreasureContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ICondition
{
  boolean evaluate(@NotNull TreasureContext context);

  @Nullable
  default IInvalidCondition getInvalidCondition()
  {
    return (null);
  }
}
