package dev.pretti.treasuresapi.conditions.types;

import dev.pretti.treasuresapi.conditions.interfaces.ICondition;

public interface IPlacedCondition extends ICondition
{
  boolean isIgnore();
}
