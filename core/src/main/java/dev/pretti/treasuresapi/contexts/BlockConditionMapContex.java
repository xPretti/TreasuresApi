package dev.pretti.treasuresapi.contexts;

import dev.pretti.treasuresapi.contexts.interfaces.IConditionMapContex;
import org.jetbrains.annotations.NotNull;

public class BlockConditionMapContex implements IConditionMapContex
{
  private final TreasureContext treasureContext;
  private final int             evaluateLimit;

  /**
   * Contrutor da classe
   */
  public BlockConditionMapContex(@NotNull TreasureContext treasureContext, int evaluateLimit)
  {
    this.treasureContext = treasureContext;
    this.evaluateLimit   = evaluateLimit;
  }

  /**
   * Retornos
   */
  @Override
  public @NotNull TreasureContext getTreasureContext()
  {
    return treasureContext;
  }

  @Override
  public int getEvaluateLimit()
  {
    return evaluateLimit;
  }

}
