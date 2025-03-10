package dev.pretti.treasuresapi.results;

import dev.pretti.treasuresapi.enums.EnumVanillaDropsType;
import org.jetbrains.annotations.Nullable;

public class ProcessResult
{
  private EnumVanillaDropsType removeVanillaDrops;

  @Nullable
  public EnumVanillaDropsType getRemoveVanillaDrops()
  {
    return removeVanillaDrops;
  }

  public void setRemoveVanillaDrops(@Nullable EnumVanillaDropsType removeVanillaDrops)
  {
    this.removeVanillaDrops = removeVanillaDrops;
  }
}
