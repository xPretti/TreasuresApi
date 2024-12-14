package dev.pretti.treasuresapi.enums;

import java.util.HashMap;

public enum EnumEffectActionType
{
  CLEAR,
  SET,
  ADD,
  REMOVE;

  public static final  EnumEffectActionType[]                VALUES       = values();
  private static final HashMap<String, EnumEffectActionType> VALUES_NAMES = new HashMap<>();

  /**
   * Inicializador
   */
  static
    {
      for(EnumEffectActionType enumValue : VALUES)
        {
          VALUES_NAMES.put(enumValue.name(), enumValue);
        }
    }

  /**
   * Retornos
   */
  public int getMaxParams()
  {
    if(this == CLEAR)
      {
        return 0;
      }
    else if(this == REMOVE)
      {
        return 1;
      }
    return 3;
  }

  /**
   * Métodos de retornos
   */
  public static EnumEffectActionType getFromString(String text)
  {
    return VALUES_NAMES.get(text.toUpperCase());
  }

  public static EnumEffectActionType getFromInt(int number)
  {
    if(number >= 0 && number < VALUES.length)
      {
        return VALUES[number];
      }
    return null;
  }
}
