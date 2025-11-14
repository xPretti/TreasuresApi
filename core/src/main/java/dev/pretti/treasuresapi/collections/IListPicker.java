package dev.pretti.treasuresapi.collections;

public interface IListPicker<T>
{
  boolean hasNext();

  T next();

  void reset();
}
