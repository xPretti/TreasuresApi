package dev.pretti.treasuresapi.contexts;

import dev.pretti.treasuresapi.datatypes.ItemType;
import org.jetbrains.annotations.Nullable;

/**
* Classe de contexto para os comandos, esta classe armazena os valores das recompensas para converter em placeholder
*/
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
