package dev.pretti.treasuresapi.processors.context;

import dev.pretti.treasuresapi.datatypes.ItemType;
import org.jetbrains.annotations.Nullable;

public class RewardContext
{
  private ItemType itemType = null;
  private int      xp       = 0;
  private double   money    = 0;

  /**
   * Definições e retornos
   */
  @Nullable
  public ItemType getItemType()
  {
    return itemType;
  }

  public void setItemType(@Nullable ItemType itemType)
  {
    this.itemType = itemType;
  }

  public int getXp()
  {
    return xp;
  }

  public void setXp(int xp)
  {
    this.xp = xp;
  }

  public double getMoney()
  {
    return money;
  }

  public void setMoney(double money)
  {
    this.money = money;
  }

  /**
   * Limpa a classe
   */
  public void reset()
  {
    this.itemType = null;
    this.xp       = 0;
    this.money    = 0;
  }
}
