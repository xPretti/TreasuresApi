package dev.pretti.treasuresapi.processors.interfaces;


import dev.pretti.treasuresapi.processors.context.TreasureContext;
import org.jetbrains.annotations.NotNull;

public interface ITreasureProcessor
{
  boolean process(@NotNull TreasureContext context);

}
