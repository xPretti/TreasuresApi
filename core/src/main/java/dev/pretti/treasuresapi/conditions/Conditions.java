package dev.pretti.treasuresapi.conditions;

import dev.pretti.treasuresapi.enums.EnumConditionType;
import dev.pretti.treasuresapi.conditions.types.base.Condition;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class Conditions
{
  private final HashMap<EnumConditionType, Condition> conditions = new HashMap<>();

  /**
   * Métodos de retornos
   */
  @SuppressWarnings("unchecked")
  @Nullable
  public <T extends Condition> T getCondition(EnumConditionType type)
  {
    return (T) conditions.get(type);
  }

  public boolean existCondition(EnumConditionType type)
  {
    return conditions.containsKey(type);
  }

  public HashMap<EnumConditionType, Condition> getConditions()
  {
    return conditions;
  }

  /**
   * Métodos de definições
   */

  public boolean addCondition(Condition condition)
  {
    if(condition != null && !existCondition(condition.getType()))
      {
        this.conditions.put(condition.getType(), condition);
        return true;
      }
    return false;
  }
}
