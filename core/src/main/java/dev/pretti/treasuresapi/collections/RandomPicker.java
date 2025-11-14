package dev.pretti.treasuresapi.collections;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomPicker<T> implements IListPicker<T>
{
  private final List<T> pool;
  private       int     size;

  /**
   * Classe utilitária para seleção aleatória de itens de uma lista.
   *
   * <p>Importante: o construtor <b>não realiza cópia</b> da lista fornecida.
   * A lista será modificada internamente durante o processo de seleção
   * (Fisher–Yates in-place shuffle).</p>
   *
   * <p>Se for necessário preservar o conteúdo ou a ordem da lista original,
   * o chamador deve criar uma cópia antes de passá-la para este construtor.</p>
   * @param list lista mutável utilizada como pool interno (não copiada).
   */
  public RandomPicker(@NotNull List<T> list) {
    this.pool = list;
    this.size = pool.size();
  }

  @Override
  public boolean hasNext() {
    return size > 0;
  }

  @Override
  public T next() {
    int idx  = ThreadLocalRandom.current().nextInt(size);
    int last = --size;
    Collections.swap(pool, idx, last);
    return pool.get(last);
  }

  @Override
  public void reset() {
    size = pool.size();
  }
}
