package dev.pretti.treasuresapi.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IteratorList<T>
{
  private final List<T>       _list = new ArrayList<>();
  private       List<Integer> _iterators;

  /**
   * Métodos de modificação da lista
   */
  public void add(T value)
  {
    _list.add(value);
    update();
  }

  /**
   * Métodos de retornos simples
   */
  public boolean isEmpty()
  {
    return _list.isEmpty();
  }

  /**
   * Métodos de retornos de tabelas
   */
  public List<T> getList()
  {
    return _list;
  }

  public List<Integer> getIterators()
  {
    return _iterators;
  }

  public List<Integer> getNewIterators()
  {
    return new ArrayList<>(_iterators);
  }

  public List<Integer> getNewIteratorsRandom()
  {
    List<Integer> newIteretors = new ArrayList<>(_iterators);
    Collections.shuffle(newIteretors);
    return newIteretors;
  }

  /**
   * Métodos de o iterator
   */
  // cria uma nova lista de iteradores
  private void update()
  {
    int size = _list.size();
    if(size > 0)
      {
        _iterators = new ArrayList<>(size);
        for(int i = 0; i < size; i++)
          {
            _iterators.add(i);
          }
      }
  }
}
