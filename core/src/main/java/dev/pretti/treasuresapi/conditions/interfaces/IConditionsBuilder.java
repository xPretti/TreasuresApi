package dev.pretti.treasuresapi.conditions.interfaces;

import dev.pretti.treasuresapi.conditions.types.IBiomeCondition;
import dev.pretti.treasuresapi.conditions.types.IBlockCondition;
import dev.pretti.treasuresapi.conditions.types.IComparatorCondition;
import dev.pretti.treasuresapi.conditions.types.IWorldCondition;
import dev.pretti.treasuresapi.enums.EnumAccessType;
import dev.pretti.treasuresapi.enums.EnumConditionType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IConditionsBuilder
{
  IWorldCondition buildWorld(@NotNull EnumAccessType accessType, @NotNull List<String> worlds);

  IBiomeCondition buildBiome(@NotNull EnumAccessType accessType, @NotNull List<String> biomes);

  IBlockCondition buildBlock(@NotNull EnumAccessType accessType, @NotNull List<String> blocks);

  IComparatorCondition buildComparator(@NotNull EnumConditionType condType, @NotNull String input, @NotNull String output);
}
