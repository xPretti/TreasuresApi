package dev.pretti.treasuresapi.options;

import dev.pretti.treasuresapi.enums.EnumDeliveryType;
import dev.pretti.treasuresapi.enums.EnumVanillaDropsType;
import org.jetbrains.annotations.Nullable;

public class RewardOptions
{
  private boolean              useLooting;
  private boolean              useFortune;
  private EnumVanillaDropsType removeVanillaDrops;
  private EnumDeliveryType     deliveryType;

  /**
   * Contrutor da classe
   */
  public RewardOptions()
  {
    this(false, false, null, null);
  }

  public RewardOptions(boolean useLooting, boolean useFortune, @Nullable EnumVanillaDropsType removeVanillaDrops, @Nullable EnumDeliveryType deliveryType)
  {
    this.useLooting         = useLooting;
    this.useFortune         = useFortune;
    this.removeVanillaDrops = removeVanillaDrops;
    this.deliveryType       = deliveryType;
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

  @Nullable
  public EnumVanillaDropsType getRemoveVanillaDrops()
  {
    return removeVanillaDrops;
  }

  @Nullable
  public EnumDeliveryType getDeliveryType()
  {
    return deliveryType;
  }

  public void setUseLooting(boolean useLooting)
  {
    this.useLooting = useLooting;
  }

  public void setUseFortune(boolean useFortune)
  {
    this.useFortune = useFortune;
  }

  public void setRemoveVanillaDrops(@Nullable EnumVanillaDropsType removeVanillaDrops)
  {
    this.removeVanillaDrops = removeVanillaDrops;
  }

  public void setDeliveryType(@Nullable EnumDeliveryType deliveryType)
  {
    this.deliveryType = deliveryType;
  }

  /**
  * Método de clonagem
  */
  @Override
  public RewardOptions clone()
  {
    return new RewardOptions(this.useLooting, this.useFortune, this.removeVanillaDrops, this.deliveryType);
  }
}
