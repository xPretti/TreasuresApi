package dev.pretti.treasuresapi.datatypes;

import org.bukkit.enchantments.Enchantment;

public class EnchantType
{
  private Enchantment enchantType;
  private int         enchantLevel;

  /**
   * Construtor da classe
   */
  public EnchantType()
  {
    enchantType  = Enchantment.DURABILITY;
    enchantLevel = 1;
  }

  public EnchantType(Enchantment type, int level)
  {
    enchantType  = type;
    enchantLevel = level;
  }

  /**
   * Rotornos da classe
   */
  public Enchantment getEnchantType()
  {
    return enchantType;
  }

  public void setEnchantType(Enchantment enchantType)
  {
    this.enchantType = enchantType;
  }

  public int getEnchantLevel()
  {
    return enchantLevel;
  }

  public void setEnchantLevel(int enchantLevel)
  {
    this.enchantLevel = enchantLevel;
  }
}
