package dev.pretti.treasuresapi.collections;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Classe utilitária para seleção sequencial (linear) de itens de uma lista.
 *
 * <p>Importante: o construtor <b>não realiza cópia</b> da lista fornecida.
 * Esta classe não modifica a lista, mas depende de sua mutabilidade
 * somente para manter consistência com {@link RandomPicker}. A ordem da lista
 * é preservada.</p>
 *
 * <p>Se for necessário preservar a lista original contra modificações externas,
 * o chamador deve criar uma cópia antes de passá-la ao construtor.</p>
 * @param <T> tipo dos elementos da lista
 */
public class LinearPicker<T> implements IListPicker<T>
{
  private final List<T> pool;
  private       int     index;

  public LinearPicker(@NotNull List<T> list) {
    this.pool  = list;
    this.index = 0;
  }

  @Override
  public boolean hasNext() {
    return index < pool.size();
  }

  @Override
  public T next() {
    return pool.get(index++);
  }

  @Override
  public void reset() {
    index = 0;
  }
}
