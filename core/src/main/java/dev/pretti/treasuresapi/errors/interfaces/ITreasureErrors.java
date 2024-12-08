package dev.pretti.treasuresapi.errors.interfaces;

import java.util.List;

public interface ITreasureErrors
{
  String getSection();

  List<ITreasureError> getErrors();

  void add(String value, String message);

  void add(List<String> values, String message);
}
