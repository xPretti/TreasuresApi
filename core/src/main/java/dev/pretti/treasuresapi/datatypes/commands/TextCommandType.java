package dev.pretti.treasuresapi.datatypes.commands;

import dev.pretti.treasuresapi.datatypes.commands.base.CommandType;
import dev.pretti.treasuresapi.enums.EnumCommandType;

public class TextCommandType extends CommandType
{
  private String text;

  /**
   * Construtor da classe
   */
  public TextCommandType(EnumCommandType type, String text)
  {
    super(type);
    this.text = text;
  }

  /**
   * Métodos de retornos
   */
  public String getText()
  {
    return text;
  }

  public void setText(String text)
  {
    this.text = text;
  }
}
