package dev.pretti.treasuresapi.results;

import dev.pretti.treasuresapi.enums.EnumVanillaDropsType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ProcessResult
{
  private EnumVanillaDropsType removeVanillaDrops;
  private List<ItemStack>      storage;

  @Nullable
  public EnumVanillaDropsType getRemoveVanillaDrops()
  {
    return removeVanillaDrops;
  }

  public void setRemoveVanillaDrops(@Nullable EnumVanillaDropsType removeVanillaDrops)
  {
    this.removeVanillaDrops = removeVanillaDrops;
  }

  @Nullable
  public List<ItemStack> getStorege()
  {
    return storage;
  }

  public List<ItemStack> getOrCreateStorege()
  {
    if(storage == null)
      {
        storage = new ArrayList<>();
      }
    return storage;
  }
}
