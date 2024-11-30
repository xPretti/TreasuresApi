package dev.pretti.treasuresapi.rewards.Options;

public class RewardOptions
{
  private final boolean useLooting;
  private final boolean useFortune;

  /**
   * Contrutor da classe
   */
  public RewardOptions()
  {
    this(false, false);
  }

  public RewardOptions(boolean useLooting, boolean useFortune)
  {
    this.useLooting = useLooting;
    this.useFortune = useFortune;
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

}
