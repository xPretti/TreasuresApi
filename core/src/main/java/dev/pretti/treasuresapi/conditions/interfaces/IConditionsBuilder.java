package dev.pretti.treasuresapi.conditions.interfaces;

import dev.pretti.treasuresapi.conditions.types.*;
import dev.pretti.treasuresapi.datatypes.MaterialType;
import dev.pretti.treasuresapi.datatypes.MetadataConditionType;
import dev.pretti.treasuresapi.enums.EnumAccessType;
import dev.pretti.treasuresapi.enums.EnumConditionType;
import dev.pretti.treasuresapi.options.ItemConditionOptions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IConditionsBuilder
{
  IWorldCondition buildWorld(@NotNull EnumAccessType accessType, @NotNull List<String> worlds);

  IBiomeCondition buildBiome(@NotNull EnumAccessType accessType, @NotNull List<String> biomes);

  IBlockCondition buildBlock(@NotNull EnumAccessType accessType, @NotNull List<String> blocks);

  IComparatorCondition buildComparator(@NotNull EnumConditionType condType, @NotNull String input, @NotNull String output);

  IItemCondition buildItem(@NotNull EnumConditionType condType, @Nullable MaterialType material, int amount, @Nullable String name, @Nullable List<String> lores, @NotNull ItemConditionOptions options,
                           @Nullable List<MetadataConditionType> itemMetadatas);

  IPlacedCondition buildPlaced(boolean ignore);
}
