package dev.pretti.treasuresapi.rewards.Options;

import dev.pretti.treasuresapi.enums.EnumVanillaDropsType;

public class RewardOptions
{
  private boolean              useLooting;
  private boolean              useFortune;
  private EnumVanillaDropsType removeVanillaDrops;

  /**
   * Contrutor da classe
   */
  public RewardOptions()
  {
    this(false, false, EnumVanillaDropsType.IGNORE);
  }

  public RewardOptions(boolean useLooting, boolean useFortune, EnumVanillaDropsType removeVanillaDrops)
  {
    this.useLooting         = useLooting;
    this.useFortune         = useFortune;
    this.removeVanillaDrops = removeVanillaDrops;
  }

  /**
   * Retornos da classe
   */
  public boolean isUseLooting()
  {
    return useLooting;
  }

  public boolean isUseFortune()
  {
    return useFortune;
  }

  public void setUseLooting(boolean useLooting)
  {
    this.useLooting = useLooting;
  }

  public void setUseFortune(boolean useFortune)
  {
    this.useFortune = useFortune;
  }

  public EnumVanillaDropsType getRemoveVanillaDrops()
  {
    return removeVanillaDrops;
  }

  public void setRemoveVanillaDrops(EnumVanillaDropsType removeVanillaDrops)
  {
    this.removeVanillaDrops = removeVanillaDrops;
  }
}
