package dev.pretti.treasuresapi.contexts;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TreasureContext
{
  private final Player   player;
  private final Location eventLocation;

  // Rewards contexts
  private final RewardContext rewardContext = new RewardContext();

  /**
   * Construtor da classe
   */
  public TreasureContext(Player player, Location eventLocation)
  {
    this.player        = player;
    this.eventLocation = eventLocation;
  }

  /**
   * Retornos da classe
   */
  public Player getPlayer()
  {
    return player;
  }

  public Location getEventLocation()
  {
    return eventLocation;
  }

  @NotNull
  public RewardContext getRewardContext()
  {
    return rewardContext;
  }
}
