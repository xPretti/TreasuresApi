package dev.pretti.treasuresapi.rewards.types;


import dev.pretti.treasuresapi.datatypes.commands.base.CommandType;
import dev.pretti.treasuresapi.enums.EnumRewardType;

import java.util.ArrayList;
import java.util.List;

public class CommandReward extends Reward
{
  private List<CommandType> commands = new ArrayList<>();

  /**
   * Construtor da classe
   */
  public CommandReward()
  {
    super(EnumRewardType.COMMAND);
  }

  public CommandReward(List<CommandType> commands)
  {
    super(EnumRewardType.COMMAND);
    this.commands = commands;
  }

  /**
   * Definições e retornos
   */
  public List<CommandType> getCommands()
  {
    return commands;
  }

  public void setCommands(List<CommandType> commands)
  {
    this.commands = commands;
  }
}
