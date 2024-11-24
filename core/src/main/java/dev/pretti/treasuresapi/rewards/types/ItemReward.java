package dev.pretti.treasuresapi.rewards.types;

import dev.pretti.treasuresapi.dynamics.EnchantDynamic;
import dev.pretti.treasuresapi.dynamics.IntDynamic;
import dev.pretti.treasuresapi.enums.EnumRewardType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

import java.util.List;
import java.util.Set;

public class ItemReward extends Reward
{
  private Material             material;
  private Byte                 data;
  private String               name;
  private List<String>         lore;
  private IntDynamic           amount;
  private List<EnchantDynamic> enchant;
  private Set<ItemFlag>        flags;

  /**
   * Construtores da classe
   */
  public ItemReward()
  {
    super(EnumRewardType.ITEM);

    this.material = Material.STONE;
    this.data     = 0;
  }

  public ItemReward(Material material, Byte data, String name, List<String> lores, IntDynamic amount,
                    List<EnchantDynamic> enchantment, Set<ItemFlag> flags)
  {
    super(EnumRewardType.ITEM);

    this.material = material;
    this.data     = data;
    this.name     = name;
    this.lore     = lores;
    this.amount   = amount;
    this.enchant  = enchantment;
    this.flags    = flags;
  }

  /**
   * Definições e retornos
   */
  public Material getMaterial()
  {
    return material;
  }

  public void setMaterial(Material type, Byte data)
  {
    this.material = type;
    this.data     = data;
  }

  public Byte getData()
  {
    return data;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public List<String> getLore()
  {
    return lore;
  }

  public void setLore(List<String> lore)
  {
    this.lore = lore;
  }

  public IntDynamic getAmount()
  {
    return amount;
  }

  public void setAmount(IntDynamic amount)
  {
    this.amount = amount;
  }

  public List<EnchantDynamic> getEnchant()
  {
    return enchant;
  }

  public void setEnchant(List<EnchantDynamic> enchant)
  {
    this.enchant = enchant;
  }

  public Set<ItemFlag> getFlags()
  {
    return flags;
  }

  public void setFlags(Set<ItemFlag> flags)
  {
    this.flags = flags;
  }
}
