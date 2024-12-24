package dev.pretti.treasuresapi.datatypes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

import java.util.List;
import java.util.Set;

public class ItemType
{
  private Material           type;
  private Byte               data;
  private int                amount;
  private String             itemName;
  private List<String>       lores;
  private List<EnchantType>  enchants;
  private Set<ItemFlag>      flags;
  private List<MetadataType> metadata;

  /**
   * Construtor da classe
   */
  public ItemType()
  {
    type   = Material.STONE;
    data   = 0;
    amount = 1;
  }

  public ItemType(Material type, Byte data, int amount, String itemName, List<String> lores,
                  List<EnchantType> enchants, Set<ItemFlag> flags, List<MetadataType> metadata)
  {
    this.type     = type;
    this.data     = data;
    this.amount   = amount;
    this.itemName = itemName;
    this.lores    = lores;
    this.enchants = enchants;
    this.flags    = flags;
    this.metadata = metadata;
  }

  /**
   * Retornos da classe
   */
  public Material getType()
  {
    return type;
  }

  public void setType(Material type)
  {
    this.type = type;
  }

  public Byte getData()
  {
    return data;
  }

  public void setData(Byte data)
  {
    this.data = data;
  }

  public int getAmount()
  {
    return amount;
  }

  public void setAmount(int amount)
  {
    this.amount = amount;
  }

  public String getItemName()
  {
    return itemName;
  }

  public void setItemName(String itemName)
  {
    this.itemName = itemName;
  }

  public List<String> getLores()
  {
    return lores;
  }

  public void setLores(List<String> lores)
  {
    this.lores = lores;
  }

  public List<EnchantType> getEnchants()
  {
    return enchants;
  }

  public void setEnchants(List<EnchantType> enchants)
  {
    this.enchants = enchants;
  }

  public Set<ItemFlag> getFlags()
  {
    return flags;
  }

  public void setFlags(Set<ItemFlag> flags)
  {
    this.flags = flags;
  }

  public List<MetadataType> getMetadata()
  {
    return metadata;
  }

  public void setMetadata(List<MetadataType> metadata)
  {
    this.metadata = metadata;
  }
}
