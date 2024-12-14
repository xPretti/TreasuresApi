package dev.pretti.treasuresapi.conditions.types;

import dev.pretti.treasuresapi.conditions.interfaces.ICondition;
import dev.pretti.treasuresapi.enums.EnumConditionType;
import org.jetbrains.annotations.NotNull;

public interface IComparatorCondition extends ICondition
{
  @NotNull
  EnumConditionType getCondType();

  @NotNull
  String getInput();

  @NotNull
  String getOutput();
}
