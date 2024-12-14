package dev.pretti.treasuresapi.contexts;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TreasureContext
{
  private final Player   player;
  private final Location eventLocation;

  private int removerDepois = 0;

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

  public void removerDepois(int count)
  {
    removerDepois += count;
  }

  public int getRemoverDepois()
  {
    return removerDepois;
  }
}
