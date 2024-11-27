package dev.pretti.treasuresapi.conditions;

import dev.pretti.treasuresapi.conditions.interfaces.ICondition;
import dev.pretti.treasuresapi.processors.context.TreasureContext;

import java.util.ArrayList;
import java.util.List;

public class Conditions
{
  private final List<ICondition> conditions = new ArrayList<>();

  /**
   * Método de atualização
   */
  public boolean evaluate(TreasureContext context)
  {
    for(ICondition condition : conditions)
      {
        if(!condition.evaluate(context))
          {
            return false;
          }
      }
    return true;
  }

  /**
   * Métodos de definições
   */
  public boolean addCondition(ICondition condition)
  {
    if(condition == null)
      {
        return false;
      }
    return conditions.add(condition);
  }
}
