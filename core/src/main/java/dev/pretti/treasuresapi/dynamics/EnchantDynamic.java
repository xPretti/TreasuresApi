package dev.pretti.treasuresapi.dynamics;

import org.bukkit.enchantments.Enchantment;

public class EnchantDynamic extends IntDynamic
{
  private Enchantment enchantment;

  /**
   * Construtor da classe
   */
  public EnchantDynamic()
  {
  }

  public EnchantDynamic(Enchantment enchant, Integer min, Integer max)
  {
    super(min, max);
    enchantment = enchant;
  }

  /**
   * Definições e retornos
   */
  public Enchantment getEnchantment()
  {
    return enchantment;
  }

  public void setEnchantment(Enchantment enchantment)
  {
    this.enchantment = enchantment;
  }
}
