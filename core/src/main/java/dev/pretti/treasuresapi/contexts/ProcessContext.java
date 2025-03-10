package dev.pretti.treasuresapi.contexts;

import dev.pretti.treasuresapi.enums.EnumDeliveryType;
import org.jetbrains.annotations.Nullable;

public class ProcessContext
{
  private EnumDeliveryType treasureDeliveryType;

  /**
   * Definições e retornos
   */
  @Nullable
  public EnumDeliveryType getTreasureDeliveryType()
  {
    return treasureDeliveryType;
  }

  public void setTreasureDeliveryType(@Nullable EnumDeliveryType treasureDeliveryType)
  {
    this.treasureDeliveryType = treasureDeliveryType;
  }

  /**
   * Limpa a classe
   */
  public void reset()
  {
    this.treasureDeliveryType = null;
  }
}
