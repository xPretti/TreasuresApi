package dev.pretti.treasuresapi.errors.interfaces;

public interface ITreasureError
{
  String getValue();

  void setValue(String value);

  String getMessage();

  void setMessage(String message);
}
