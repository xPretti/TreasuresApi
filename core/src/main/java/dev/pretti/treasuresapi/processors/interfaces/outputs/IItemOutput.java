package dev.pretti.treasuresapi.processors.interfaces.outputs;

import dev.pretti.treasuresapi.datatypes.ItemType;
import dev.pretti.treasuresapi.processors.context.TreasureContext;
import org.jetbrains.annotations.NotNull;

public interface IItemOutput
{
  boolean process(@NotNull TreasureContext context, ItemType itemType, boolean useLooting);
}
