package dev.pretti.treasuresapi.datatypes.commands;

import dev.pretti.treasuresapi.datatypes.commands.base.CommandType;
import dev.pretti.treasuresapi.enums.EnumCommandType;
import org.jetbrains.annotations.NotNull;

public class InvalidCommandType extends CommandType
{
  private String arguments;
  private String error;

  /**
   * Construtor da classe
   */
  public InvalidCommandType()
  {
    this("", "");
  }

  public InvalidCommandType(@NotNull String arguments, @NotNull String error)
  {
    super(EnumCommandType.INVALID);

    this.arguments = arguments;
    this.error     = error;
  }

  /**
   * Métodos de retornos
   */
  public String getArguments()
  {
    return arguments;
  }

  public void setArguments(String arguments)
  {
    this.arguments = arguments;
  }

  public String getError()
  {
    return error;
  }

  public void setError(String error)
  {
    this.error = error;
  }
}
