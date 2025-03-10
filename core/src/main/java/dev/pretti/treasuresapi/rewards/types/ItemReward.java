package dev.pretti.treasuresapi.rewards.types;

import dev.pretti.treasuresapi.datatypes.MetadataType;
import dev.pretti.treasuresapi.dynamics.EnchantDynamic;
import dev.pretti.treasuresapi.dynamics.IntDynamic;
import dev.pretti.treasuresapi.enums.EnumRewardType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
  private List<MetadataType>   metadata;

  /**
   * Construtores da classe
   */
  public ItemReward()
  {
    super(EnumRewardType.ITEM);

    this.material = Material.STONE;
    this.data     = 0;
  }

  public ItemReward(@NotNull Material material, @NotNull Byte data, @Nullable String name,
                    @Nullable List<String> lores, @Nullable IntDynamic amount, @Nullable List<EnchantDynamic> enchantment,
                    @Nullable Set<ItemFlag> flags, @Nullable List<MetadataType> metadata)
  {
    super(EnumRewardType.ITEM);

    this.material     = material;
    this.data         = data;
    this.name         = name;
    this.lore         = lores;
    this.amount       = amount;
    this.enchant      = enchantment;
    this.flags        = flags;
    this.metadata     = metadata;
  }

  /**
   * Definições e retornos
   */
  @NotNull
  public Material getMaterial()
  {
    return material;
  }

  public void setMaterial(@NotNull Material type, Byte data)
  {
    this.material = type;
    this.data     = data;
  }

  @NotNull
  public Byte getData()
  {
    return data;
  }

  @Nullable
  public String getName()
  {
    return name;
  }

  public void setName(@Nullable String name)
  {
    this.name = name;
  }

  @Nullable
  public List<String> getLore()
  {
    return lore;
  }

  public void setLore(@Nullable List<String> lore)
  {
    this.lore = lore;
  }

  @Nullable
  public IntDynamic getAmount()
  {
    return amount;
  }

  public void setAmount(@Nullable IntDynamic amount)
  {
    this.amount = amount;
  }

  @Nullable
  public List<EnchantDynamic> getEnchant()
  {
    return enchant;
  }

  public void setEnchant(@Nullable List<EnchantDynamic> enchant)
  {
    this.enchant = enchant;
  }

  @Nullable
  public Set<ItemFlag> getFlags()
  {
    return flags;
  }

  public void setFlags(@Nullable Set<ItemFlag> flags)
  {
    this.flags = flags;
  }

  @Nullable
  public List<MetadataType> getMetadata()
  {
    return metadata;
  }

  public void setMetadata(@Nullable List<MetadataType> metadata)
  {
    this.metadata = metadata;
  }
}
