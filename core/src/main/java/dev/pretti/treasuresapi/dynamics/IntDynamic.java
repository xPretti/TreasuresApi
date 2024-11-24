package dev.pretti.treasuresapi.dynamics;

import dev.pretti.treasuresapi.utils.MathUtils;

public class IntDynamic extends Dynamic<Integer>
{
  /**
  * Contrutor da classe
  */
  public IntDynamic()
  {
    super.setMin(0);
    super.setMax(0);
  }
  public IntDynamic(Integer min, Integer max)
  {
    super(min, max);
  }

  // retorna o valor aleatório entre min e max definidos
  @Override
  public Integer getValue()
  {
    int min = getMin();
    int max = getMax();
    return MathUtils.getRandom(min, max);
  }
}
