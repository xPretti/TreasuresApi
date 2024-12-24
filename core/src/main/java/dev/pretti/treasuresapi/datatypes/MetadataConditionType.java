package dev.pretti.treasuresapi.datatypes;

import dev.pretti.treasuresapi.enums.EnumConditionType;

public class MetadataConditionType extends MetadataType
{
  private final EnumConditionType type;

  /**
  * Contrutor da classe
  */
  public MetadataConditionType(EnumConditionType type, String key, String value)
  {
    super(key, value);
    this.type  = type;
  }

  /**
  * Retornos
  */
  public EnumConditionType getType()
  {
    return type;
  }
}
