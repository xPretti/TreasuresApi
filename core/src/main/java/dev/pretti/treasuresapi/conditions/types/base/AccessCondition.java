package dev.pretti.treasuresapi.conditions.types.base;

import dev.pretti.treasuresapi.enums.EnumAccessType;
import dev.pretti.treasuresapi.enums.EnumConditionType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class AccessCondition extends Condition
{
  private       EnumAccessType           accessType;
  private final HashMap<String, Integer> names = new HashMap<>();

  /**
   * Construtor da classe
   */
  public AccessCondition(EnumConditionType type)
  {
    super(type);
    this.accessType = EnumAccessType.WHITELIST;
  }

  /**
   * Métodos de verificações
   */
  public boolean IsPassed(String name)
  {
    if(!names.isEmpty())
      {
        boolean contains = names.containsKey(name);
        switch(accessType)
          {
            case BLACKLIST:
              return !contains;
            case WHITELIST:
              return contains;
            default:
              return false;
          }
      }
    return true;
  }

  /**
   * Métodos de definições
   */
  public EnumAccessType getAccessType()
  {
    return accessType;
  }

  public void setAccessType(EnumAccessType accessType)
  {
    this.accessType = accessType;
  }

  @NotNull
  public HashMap<String, Integer> getNames()
  {
    return names;
  }
}
