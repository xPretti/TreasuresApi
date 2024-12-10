package dev.pretti.treasuresapi.conditions.interfaces;

import dev.pretti.treasuresapi.enums.EnumAccessType;
import dev.pretti.treasuresapi.enums.EnumConditionType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IConditionsBuilder
{
  ICondition buildWorld(@NotNull EnumAccessType accessType, @NotNull List<String> worlds);

  ICondition buildBiome(@NotNull EnumAccessType accessType, @NotNull List<String> biomes);

  ICondition buildBlock(@NotNull EnumAccessType accessType, @NotNull List<String> blocks);

  ICondition buildComparator(@NotNull EnumConditionType condType, @NotNull String input, @NotNull String output);
}
