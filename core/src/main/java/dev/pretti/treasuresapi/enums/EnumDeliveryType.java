package dev.pretti.treasuresapi.enums;

import java.util.Arrays;
import java.util.HashMap;

public enum EnumDeliveryType
{
  INVENTORY,
  DROP,
  STORED;

  public static final  EnumDeliveryType[]                VALUES       = values();
  private static final HashMap<String, EnumDeliveryType> VALUES_NAMES = new HashMap<>();

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
  public static EnumDeliveryType getFromString(String text)
  {
    return VALUES_NAMES.get(text.toUpperCase());
  }

  public static EnumDeliveryType getFromInt(int number)
  {
    if(number >= 0 && number < VALUES.length)
      {
        return VALUES[number];
      }
    return null;
  }
}
