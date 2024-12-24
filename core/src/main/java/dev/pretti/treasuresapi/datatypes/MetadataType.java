package dev.pretti.treasuresapi.datatypes;

public class MetadataType
{
  private String key;
  private String value;

  /**
   * Contrutor da classe
   */
  public MetadataType()
  {
    this("", "");
  }

  public MetadataType(String key, String value)
  {
    this.key   = key;
    this.value = value;
  }

  /**
   * Retornos
   */
  public String getKey()
  {
    return key;
  }

  public String getValue()
  {
    return value;
  }

  public void setValue(String value)
  {
    this.value = value;
  }

  public void setKey(String key)
  {
    this.key = key;
  }
}
