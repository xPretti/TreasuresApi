package dev.pretti.treasuresapi.contexts;

import dev.pretti.treasuresapi.enums.EnumDeliveryType;
import dev.pretti.treasuresapi.results.ProcessResult;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TreasureContext
{
  // Properties
  private final Player           player;
  private final Location         eventLocation;
  private final EnumDeliveryType deliveryType;

  // Results
  private final ProcessResult processResult = new ProcessResult();

  // Rewards contexts
  private final RewardContext  rewardContext  = new RewardContext();
  private final ProcessContext processContext = new ProcessContext();

  /**
   * Construtor da classe
   */
  public TreasureContext(@NotNull Player player, @NotNull Location eventLocation, @NotNull EnumDeliveryType deliveryType)
  {
    this.player        = player;
    this.eventLocation = eventLocation;
    this.deliveryType  = deliveryType;
  }

  /**
   * Retornos da classe
   */
  @NotNull
  public Player getPlayer()
  {
    return player;
  }

  @NotNull
  public Location getEventLocation()
  {
    return eventLocation;
  }

  @NotNull
  public EnumDeliveryType getDeliveryType()
  {
    return deliveryType;
  }

  @NotNull
  public RewardContext getRewardContext()
  {
    return rewardContext;
  }

  public ProcessContext getProcessContext()
  {
    return processContext;
  }

  /**
  * Retornos dos resultados
  */
  public ProcessResult getProcessResult()
  {
    return processResult;
  }
}
