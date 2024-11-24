package dev.pretti.treasuresapi.enums;

import java.util.HashMap;

public enum EnumAccessType
{
  BLACKLIST,
  WHITELIST;

  public static final  EnumAccessType[]                VALUES       = values();
  private static final HashMap<String, EnumAccessType> VALUES_NAMES = new HashMap<>();

  /**
   * Inicializador
   */
  static
    {
      for(EnumAccessType enumValue : VALUES)
        {
          VALUES_NAMES.put(enumValue.name(), enumValue);
        }
    }

  /**
   * Métodos de retornos
   */
  public static EnumAccessType getFromString(String text)
  {
    return VALUES_NAMES.get(text.toUpperCase());
  }

  public static EnumAccessType getFromInt(int number)
  {
    if(number >= 0 && number < VALUES.length)
      {
        return VALUES[number];
      }
    return null;
  }
}
