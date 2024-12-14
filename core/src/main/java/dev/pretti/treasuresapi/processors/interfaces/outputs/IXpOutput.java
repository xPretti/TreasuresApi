package dev.pretti.treasuresapi.processors.interfaces.outputs;

import dev.pretti.treasuresapi.contexts.TreasureContext;
import org.jetbrains.annotations.NotNull;

public interface IXpOutput
{
  boolean process(@NotNull TreasureContext context, int xp, boolean isLevel);
}
