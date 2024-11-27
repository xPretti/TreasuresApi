package dev.pretti.treasuresapi.conditions.interfaces;

import dev.pretti.treasuresapi.processors.context.TreasureContext;
import org.jetbrains.annotations.NotNull;

public interface ICondition
{
  boolean evaluate(@NotNull TreasureContext context);
}
