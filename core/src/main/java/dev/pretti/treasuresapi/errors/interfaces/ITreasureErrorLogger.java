package dev.pretti.treasuresapi.errors.interfaces;

import java.util.Collection;
import java.util.List;

public interface ITreasureErrorLogger
{
  int getTotal();

  Collection<ITreasureErrors> getErrors();

  ITreasureErrors getSection(String section);

  void add(String section, String value, String message);

  void add(String section, List<String> values, String message);
}
