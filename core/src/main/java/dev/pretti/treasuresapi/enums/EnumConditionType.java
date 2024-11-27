package dev.pretti.treasuresapi.enums;

import java.util.HashMap;

public enum EnumConditionType
{
  WORLD,
  BIOME,
  BLOCK;

  public static final  EnumConditionType[]                VALUES       = values();
  private static final HashMap<String, EnumConditionType> VALUES_NAMES = new HashMap<>();

  /**
   * Inicializador
   */
  static
    {
      for(EnumConditionType enumValue : VALUES)
        {
          VALUES_NAMES.put(enumValue.name(), enumValue);
        }
    }

  /**
   * Métodos de retornos
   */
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
