package dev.pretti.treasuresapi.datatypes.commands;

import dev.pretti.treasuresapi.datatypes.commands.base.CommandType;
import dev.pretti.treasuresapi.enums.EnumCommandType;

public class TitleCommandType extends CommandType
{
  private String  title;
  private String  subtitle;
  private int fadeInTicks;
  private int stayTicks;
  private int fadeOutTicks;

  /**
   * Construtor da classe
   */
  public TitleCommandType(EnumCommandType type, String title, String subtitle, int fadeInTicks, int stayTicks,
                          int fadeOutTicks)
  {
    super(type);

    this.title        = title;
    this.subtitle     = subtitle;
    this.fadeInTicks  = fadeInTicks;
    this.stayTicks    = stayTicks;
    this.fadeOutTicks = fadeOutTicks;
  }

  /**
   * Métodos de retornos
   */
  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getSubtitle()
  {
    return subtitle;
  }

  public void setSubtitle(String subtitle)
  {
    this.subtitle = subtitle;
  }

  public int getFadeInTicks()
  {
    return fadeInTicks;
  }

  public void setFadeInTicks(int fadeInTicks)
  {
    this.fadeInTicks = fadeInTicks;
  }

  public int getStayTicks()
  {
    return stayTicks;
  }

  public void setStayTicks(int stayTicks)
  {
    this.stayTicks = stayTicks;
  }

  public int getFadeOutTicks()
  {
    return fadeOutTicks;
  }

  public void setFadeOutTicks(int fadeOutTicks)
  {
    this.fadeOutTicks = fadeOutTicks;
  }
}
