package dev.pretti.treasuresapi.conditions.types;

import dev.pretti.treasuresapi.conditions.interfaces.ICondition;
import dev.pretti.treasuresapi.enums.EnumAccessType;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public interface IWorldCondition extends ICondition
{
  @NotNull
  EnumAccessType getAccessType();

  @NotNull
  HashSet<String> getWorlds();
}
