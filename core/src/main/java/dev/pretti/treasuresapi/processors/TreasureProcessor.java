package dev.pretti.treasuresapi.processors;

import dev.pretti.treasuresapi.conditions.Conditions;
import dev.pretti.treasuresapi.datatypes.ItemType;
import dev.pretti.treasuresapi.datatypes.commands.CommandType;
import dev.pretti.treasuresapi.processors.context.TreasureContext;
import dev.pretti.treasuresapi.processors.interfaces.ITreasureProcessor;
import dev.pretti.treasuresapi.processors.interfaces.outputs.ICommandOutput;
import dev.pretti.treasuresapi.processors.interfaces.outputs.IItemOutput;
import dev.pretti.treasuresapi.processors.interfaces.outputs.IXpOutput;
import dev.pretti.treasuresapi.rewards.Rewards;
import dev.pretti.treasuresapi.rewards.RewardsGroup;
import dev.pretti.treasuresapi.rewards.Treasure;
import dev.pretti.treasuresapi.rewards.types.CommandReward;
import dev.pretti.treasuresapi.rewards.types.ItemReward;
import dev.pretti.treasuresapi.rewards.types.Reward;
import dev.pretti.treasuresapi.rewards.types.XpReward;
import dev.pretti.treasuresapi.utils.CloneableUtils;
import dev.pretti.treasuresapi.utils.ConverterUtils;
import dev.pretti.treasuresapi.utils.MathUtils;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TreasureProcessor implements ITreasureProcessor
{
  private final Treasure       treasure;
  private final IXpOutput      xpOutput;
  private final ICommandOutput commandOutput;
  private final IItemOutput    itemOutput;

  /**
   * Construtor da classe
   */
  public TreasureProcessor(@NotNull Treasure treasure,
                           @Nullable IXpOutput xpOutput,
                           @Nullable ICommandOutput commandOutput,
                           @Nullable IItemOutput itemOutput)
  {
    this.treasure      = treasure;
    this.xpOutput      = xpOutput;
    this.commandOutput = commandOutput;
    this.itemOutput    = itemOutput;
  }

  /**
   * Retornos protegidos
   */
  protected Treasure getTreasure()
  {
    return treasure;
  }

  /**
   * Verificações
   */
  protected boolean hasPermission(Player player, String permission)
  {
    if(permission.isEmpty())
      {
        return (true);
      }
    return (player.hasPermission(permission));
  }

  protected boolean hasCondition(TreasureContext process, Conditions conditions)
  {
    if(conditions != null)
      {
        return conditions.evaluate(process);
      }
    return true;
  }

  /**
   * Evento de processamento
   */
  public boolean process(@NotNull TreasureContext context)
  {
    return _treasureProcess(context);
  }

  /**
   * Métodos de processamentos privados
   */
  public boolean _treasureProcess(TreasureContext context)
  {
    if(MathUtils.isChance(treasure.getChance()))
      {
        Player player = context.getPlayer();
        if(hasPermission(player.getPlayer(), treasure.getPermission()))
          {
            List<RewardsGroup> values = treasure.getRewardsGroup().getList();
            if(values != null)
              {
                if(hasCondition(context, treasure.getConditions()))
                  {
                    boolean       wasRewarded = false;
                    int           limit       = treasure.getLimit();
                    boolean       random      = treasure.isRandom();
                    List<Integer> iterators   = random ? treasure.getRewardsGroup().getNewIteratorsRandom() : treasure.getRewardsGroup().getIterators();
                    int           count       = 0;
                    RewardsGroup  rewardsGroup;
                    limit = limit <= 0 ? iterators.size() : limit;
                    for(Integer index : iterators)
                      {
                        if(count < limit)
                          {
                            rewardsGroup = values.get(index);
                            if(rewardsGroup != null)
                              {
                                if(_processActions(context, rewardsGroup))
                                  {
                                    wasRewarded = true;
                                  }
                                count++;
                              }
                          }
                        else
                          {
                            break;
                          }
                      }
                    return wasRewarded;
                  }
              }
          }
      }
    return false;
  }

  private boolean _processActions(TreasureContext context, RewardsGroup rewardsGroup)
  {
    if(rewardsGroup != null)
      {
        if(MathUtils.isChance(rewardsGroup.getChance()))
          {
            Player player = context.getPlayer();
            if(hasPermission(player.getPlayer(), rewardsGroup.getPermission()))
              {
                boolean isRondom = rewardsGroup.isRandomRewards();
                int     limit    = rewardsGroup.getLimit();
                return _processRewards(context, rewardsGroup, limit, isRondom);
              }
          }
      }
    return false;
  }

  private boolean _processRewards(TreasureContext context, RewardsGroup rewardsGroup, int limit, boolean random)
  {
    if(rewardsGroup != null)
      {
        List<Rewards> values = rewardsGroup.getRewards().getList();
        if(!values.isEmpty())
          {
            boolean       wasRewarded = false;
            List<Integer> iterators   = random ? rewardsGroup.getRewards().getNewIteratorsRandom() : rewardsGroup.getRewards().getIterators();
            int           count       = 0;
            Rewards       rewards;
            limit = limit <= 0 ? iterators.size() : limit;
            Player player = context.getPlayer();
            for(Integer index : iterators)
              {
                if(count < limit)
                  {
                    rewards = values.get(index);
                    if(rewards != null)
                      {
                        if(MathUtils.isChance(rewards.getChance()))
                          {
                            if(hasPermission(player, rewards.getPermission()))
                              {
                                count++;
                                for(Reward reward : rewards.getRewards())
                                  {
                                    if(!_processXp(context, reward))
                                      {
                                        if(!_processItem(context, reward, rewards.isUseLooting()))
                                          {
                                            if(!_processCommands(context, reward))
                                              {
                                                continue;
                                              }
                                          }
                                      }
                                    wasRewarded = true;
                                  }
                              }
                          }
                      }
                  }
                else
                  {
                    break;
                  }
              }
            return wasRewarded;
          }
      }
    return false;
  }

  private boolean _processCommands(TreasureContext context, Reward reward)
  {
    if(reward != null)
      {
        if(reward instanceof CommandReward)
          {
            if(commandOutput == null)
              {
                return true; // Retorna true porque era do tipo comando
              }
            CommandReward commandReward = (CommandReward) reward;
            if(commandReward.getCommands() != null)
              {
                for(CommandType command : commandReward.getCommands())
                  {
                    commandOutput.process(context, CloneableUtils.clone(command));
                  }
                return true;
              }
          }
      }
    return false;
  }

  private boolean _processXp(TreasureContext context, Reward reward)
  {
    if(reward != null)
      {
        if(reward instanceof XpReward)
          {
            if(xpOutput == null)
              {
                return true;
              }
            XpReward xpReward = (XpReward) reward;
            if(xpReward.getXp() != null)
              {
                int amount = xpReward.getXp().getValue();
                if(amount != 0)
                  {
                    xpOutput.process(context, amount, xpReward.isLevel());
                    return true;
                  }
              }
          }
      }
    return false;
  }

  private boolean _processItem(TreasureContext context, Reward reward, boolean useLooting)
  {
    if(reward != null)
      {
        if(reward instanceof ItemReward)
          {
            if(itemOutput == null)
              {
                return true;
              }
            ItemReward itemReward = (ItemReward) reward;
            ItemType   itemType   = ConverterUtils.getItemType(itemReward);
            if(itemType != null)
              {
                itemOutput.process(context, itemType, useLooting);
                return true;
              }
          }
      }
    return false;
  }
}
