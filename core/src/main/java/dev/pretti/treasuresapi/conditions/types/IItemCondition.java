package dev.pretti.treasuresapi.conditions.types;

import dev.pretti.treasuresapi.conditions.interfaces.ICondition;
import dev.pretti.treasuresapi.datatypes.MaterialType;
import dev.pretti.treasuresapi.options.ItemConditionOptions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IItemCondition extends ICondition
{
  @Nullable
  MaterialType getMaterial();

  int getAmount();

  @Nullable
  String getName();

  @Nullable
  List<String> getLores();

  @NotNull
  ItemConditionOptions getOptions();
}
