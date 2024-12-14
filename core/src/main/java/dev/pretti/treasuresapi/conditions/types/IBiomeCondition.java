package dev.pretti.treasuresapi.conditions.types;

import dev.pretti.treasuresapi.conditions.interfaces.ICondition;
import dev.pretti.treasuresapi.enums.EnumAccessType;
import org.bukkit.block.Biome;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public interface IBiomeCondition extends ICondition
{
  @NotNull
  EnumAccessType getAccessType();

  @NotNull
  HashSet<Biome> getBiomes();
}
