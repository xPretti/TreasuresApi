package dev.pretti.treasuresapi.utils;

import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Cake;
import org.bukkit.block.data.type.Snow;
import org.jetbrains.annotations.NotNull;

public class BlockDataUtils
{
  /**
   * Método de retorno universal da data
   */
  public static int getData(@NotNull Block block)
  {
    if(!SystemUtils.isNewVersion())
      {
        return getLegacyData(block);
      }
    BlockData data = block.getBlockData();
    if(data instanceof Ageable)
      {
        return ((Ageable) data).getAge();
      }
    else if(data instanceof Snow)
      {
        return ((Snow) data).getLayers();
      }
    else if(data instanceof AnaloguePowerable)
      {
        return ((AnaloguePowerable) data).getPower();
      }
    else if(data instanceof Cake)
      {
        return ((Cake) data).getBites();
      }
    return 0;
  }

  @SuppressWarnings("deprecation")
  private static int getLegacyData(@NotNull Block block)
  {
    return block.getData();
  }
}
