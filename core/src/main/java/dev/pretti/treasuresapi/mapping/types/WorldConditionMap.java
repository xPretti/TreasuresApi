package dev.pretti.treasuresapi.mapping.types;

import dev.pretti.treasuresapi.conditions.interfaces.ICondition;
import dev.pretti.treasuresapi.conditions.types.IWorldCondition;
import dev.pretti.treasuresapi.contexts.interfaces.IConditionMapContex;
import dev.pretti.treasuresapi.enums.EnumAccessType;
import dev.pretti.treasuresapi.mapping.interfaces.IConditionMap;
import dev.pretti.treasuresapi.processors.interfaces.ITreasureProcessor;
import dev.pretti.treasuresapi.rewards.Treasure;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

public class WorldConditionMap<T extends IConditionMap> implements IConditionMap
{
  private final HashMap<String, IConditionMap> whitelist = new HashMap<>();
  private final List<IConditionMap>            forwarder = new ArrayList<>();

  private final Supplier<T> instanceSupplier;

  /**
   * Contrutor da classe
   */
  public WorldConditionMap(Supplier<T> instanceSupplier)
  {
    this.instanceSupplier = instanceSupplier;
  }

  /**
   * Método de carregamento
   */
  @Override
  public IConditionMap load(ITreasureProcessor processor)
  {
    Treasure treasure = processor.getTreasure();
    if(treasure == null)
      {
        return this;
      }

    List<ICondition> conditions = treasure.getConditions().getValues();
    for(ICondition condition : conditions)
      {
        if(condition instanceof IWorldCondition)
          {
            IWorldCondition worldCondition = (IWorldCondition) condition;
            if(worldCondition.getAccessType() == EnumAccessType.WHITELIST)
              {
                for(String value : worldCondition.getWorlds())
                  {
                    if(!whitelist.containsKey(value))
                      {
                        whitelist.put(value, instanceSupplier.get().load(processor));
                      }
                    else
                      {
                        whitelist.get(value).load(processor);
                      }
                  }
                return this;
              }
          }
      }

    forwarder.add(instanceSupplier.get().load(processor));
    return this;
  }

  /**
   * Método de processamento
   */
  @Override
  public int evaluate(@NotNull IConditionMapContex context)
  {
    return evaluate(context, 0);
  }

  @Override
  public int evaluate(@NotNull IConditionMapContex context, int count)
  {
    int limit = context.getEvaluateLimit();
    if(count >= limit)
      {
        return count;
      }
    String worldName = context.getTreasureContext().getPlayer().getWorld().getName();
    if(whitelist.containsKey(worldName))
      {
        count = whitelist.get(worldName).evaluate(context, count);
      }

    if(count < limit)
      {
        for(IConditionMap map : forwarder)
          {
            count = map.evaluate(context, count);
            if(count >= limit)
              {
                break;
              }
          }
      }
    return count;
  }

  /**
  * Retorna a instância da classe
  */
  public static <T extends IConditionMap> WorldConditionMap<T> instance(Supplier<T> instanceSupplier) {
    return new WorldConditionMap<>(instanceSupplier);
  }
}
