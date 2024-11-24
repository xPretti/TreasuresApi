package dev.pretti.treasuresapi.dynamics;

import dev.pretti.treasuresapi.utils.MathUtils;

public class DoubleDynamic extends Dynamic<Double>
{
  /**
   * Construtor da classe
   */
  public DoubleDynamic()
  {
    super.setMin(0d);
    super.setMax(0d);
  }

  public DoubleDynamic(Double min, Double max)
  {
    super(min, max);
  }

  // retorna o valor aleatório entre min e max definidos
  @Override
  public Double getValue()
  {
    double min = getMin();
    double max = getMax();
    return MathUtils.getRandom(min, max);
  }
}
