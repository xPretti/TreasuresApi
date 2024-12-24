package dev.pretti.treasuresapi.datatypes;

import dev.pretti.treasuresapi.enums.EnumConditionType;

public class MetadataType
{
  private final EnumConditionType type;
  private final String            key;
  private final String            value;

  /**
  * Contrutor da classe
  */
  public MetadataType(EnumConditionType type, String key, String value)
  {
    this.type  = type;
    this.key   = key;
    this.value = value;
  }

  /**
  * Retornos
  */
  public EnumConditionType getType()
  {
    return type;
  }

  public String getKey()
  {
    return key;
  }

  public String getValue()
  {
    return value;
  }
}
