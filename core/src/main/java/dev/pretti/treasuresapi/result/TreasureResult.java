package dev.pretti.treasuresapi.result;

import dev.pretti.treasuresapi.result.errors.interfaces.ITreasureErrorResult;
import dev.pretti.treasuresapi.result.interfaces.ITreasureResult;
import dev.pretti.treasuresapi.rewards.Treasure;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class TreasureResult implements ITreasureResult
{
  private       List<Treasure>                        treasures = null;
  private final HashMap<String, ITreasureErrorResult> errors    = new HashMap<>();

  /**
   * Retornos da classe
   */
  @Nullable
  public List<Treasure> getTreasures()
  {
    return treasures;
  }

  public void setTreasures(List<Treasure> treasures)
  {
    this.treasures = treasures;
  }

  public Collection<ITreasureErrorResult> getErrors()
  {
    return errors.values();
  }

  public void addErrors(@NotNull ITreasureErrorResult error)
  {
    if(errors.containsKey(error.getIdentifier()))
      {
        return;
      }
    errors.put(error.getIdentifier(), error);
  }
}
