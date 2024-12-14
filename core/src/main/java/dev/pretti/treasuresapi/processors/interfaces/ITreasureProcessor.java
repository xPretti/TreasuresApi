package dev.pretti.treasuresapi.processors.interfaces;


import dev.pretti.treasuresapi.contexts.TreasureContext;
import dev.pretti.treasuresapi.rewards.Treasure;
import org.jetbrains.annotations.NotNull;

public interface ITreasureProcessor
{
  boolean process(@NotNull TreasureContext context);

  Treasure getTreasure();

}
