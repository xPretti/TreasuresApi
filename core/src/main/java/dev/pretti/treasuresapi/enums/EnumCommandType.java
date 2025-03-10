package dev.pretti.treasuresapi.enums;

import java.util.Arrays;
import java.util.HashMap;

public enum EnumCommandType
{
  INVALID,
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
  BROADCAST_ACTIONBAR,
  EFFECT;

  public static final  EnumCommandType[]                VALUES       = values();
  private static final HashMap<String, EnumCommandType> VALUES_NAMES = new HashMap<>();

  /**
   * Inicializador
   */
  static
    {
      Arrays.stream(VALUES).forEach(value -> VALUES_NAMES.put(value.name(), value));
    }

  /**
   * Métodos de retornos
   */
  public static EnumCommandType getFromString(String text)
  {
    return VALUES_NAMES.get(text.toUpperCase());
  }

  public static EnumCommandType getFromInt(int number)
  {
    if(number >= 0 && number < VALUES.length)
      {
        return VALUES[number];
      }
    return null;
  }
}
