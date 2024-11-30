package dev.pretti.treasuresapi.processors.interfaces.outputs;

import dev.pretti.treasuresapi.datatypes.ItemType;
import dev.pretti.treasuresapi.processors.context.TreasureContext;
import dev.pretti.treasuresapi.rewards.Options.RewardOptions;
import org.jetbrains.annotations.NotNull;

public interface IItemOutput
{
  boolean process(@NotNull TreasureContext context, @NotNull ItemType itemType, @NotNull RewardOptions options);
}
