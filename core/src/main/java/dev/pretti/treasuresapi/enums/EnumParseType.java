package dev.pretti.treasuresapi.enums;

import java.util.HashMap;

public enum EnumParseType
{
  CONSOLE,
  PLAYER,
  MESSAGE,
  RANGE_MESSAGE,
  BROADCAST,
  SOUND,
  WORLD_SOUND,
  EVENT_SOUND,
  WORLD_EVENT_SOUND,
  TITLE,
  BROADCAST_TITLE,
  ACTIONBAR,
  BROADCAST_ACTIONBAR;

  public static final  EnumParseType[]                VALUES       = values();
  private static final HashMap<String, EnumParseType> VALUES_NAMES = new HashMap<>();

  /**
   * Inicializador
   */
  static
    {
      for(EnumParseType enumValue : VALUES)
        {
          VALUES_NAMES.put(enumValue.name(), enumValue);
        }
    }

  /**
   * Métodos de retornos
   */
  public static EnumParseType getFromString(String text)
  {
    return VALUES_NAMES.get(text.toUpperCase());
  }

  public static EnumParseType getFromInt(int number)
  {
    if(number >= 0 && number < VALUES.length)
      {
        return VALUES[number];
      }
    return null;
  }
}
