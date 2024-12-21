package dev.pretti.treasuresapi.enums;

import java.util.HashMap;

public enum EnumConditionType
{
  WORLD,
  BIOME,
  BLOCK,
  ITEM,
  NOT_ITEM,
  PLACED,
  CONTAINS,
  EQUALS,
  EQUALS_IGNORE_CASE,
  NOT_CONTAINS,
  NOT_EQUALS,
  NOT_EQUALS_IGNORE_CASE,
  NUMBER_EQUALS("=="),
  NUMBER_NOT_EQUALS("!="),
  NUMBER_GREATER(">"),
  NUMBER_GREATER_OR_EQUALS(">="),
  NUMBER_LESS("<"),
  NUMBER_LESS_OR_EQUALS("<=");

  public static final  EnumConditionType[]                VALUES       = values();
  private static final HashMap<String, EnumConditionType> VALUES_NAMES = new HashMap<>();

  private String  typeName = null;
  private boolean invert   = false;

  /**
   * Inicializador
   */
  static
    {
      for(EnumConditionType enumValue : VALUES)
        {
          VALUES_NAMES.put(enumValue.getTypeName(), enumValue);
        }
    }

  /**
   * Contrutor da classe
   */
  EnumConditionType()
  {
  }

  EnumConditionType(String typeName)
  {
    this.typeName = typeName;
  }

  /**
   * Métodos de retornos
   */
  public String getTypeName()
  {
    return typeName == null ? this.name() : typeName;
  }

  public boolean isItem()
  {
    return this.equals(EnumConditionType.ITEM) || this.equals(EnumConditionType.NOT_ITEM);
  }

  public boolean isComparator()
  {
    switch(this)
      {
        case CONTAINS:
        case EQUALS:
        case EQUALS_IGNORE_CASE:
        case NOT_CONTAINS:
        case NOT_EQUALS:
        case NOT_EQUALS_IGNORE_CASE:
          return true;
        default:
          return false;
      }
  }

  public boolean isNumberComparator()
  {
    switch(this)
      {
        case NUMBER_EQUALS:
        case NUMBER_NOT_EQUALS:
        case NUMBER_GREATER:
        case NUMBER_GREATER_OR_EQUALS:
        case NUMBER_LESS:
        case NUMBER_LESS_OR_EQUALS:
          return true;
        default:
          return false;
      }
  }



  public static EnumConditionType getFromString(String text)
  {
    return VALUES_NAMES.get(text.toUpperCase());
  }

  public static EnumConditionType getFromInt(int number)
  {
    if(number >= 0 && number < VALUES.length)
      {
        return VALUES[number];
      }
    return null;
  }
}
