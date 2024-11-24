package dev.pretti.treasuresapi.dynamics;

public class Dynamic<T extends Comparable<T>>
{
  private T min;
  private T max;

  /**
   * Construtores
   */
  public Dynamic()
  {
  }

  public Dynamic(T min, T max)
  {
    setMinAndMax(min, max);
  }

  /**
   * Método de retornos
   */
  // retorna o valor aleatório entre min e max definidos
  public T getValue()
  {
    return min;
  }

  /**
   * Métodos de definição e retorno
   */
  public T getMin()
  {
    return min;
  }

  public T getMax()
  {
    return max;
  }

  public void setMinAndMax(T min, T max)
  {
    this.min = min;
    this.max = (max.compareTo(min) >= 0) ? max : min;
  }

  public void setMin(T min)
  {
    this.min = min;
  }

  public void setMax(T max)
  {
    this.max = (max.compareTo(getMin()) >= 0) ? max : getMin();
  }
}
