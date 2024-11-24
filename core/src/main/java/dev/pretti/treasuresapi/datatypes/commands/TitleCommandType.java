package dev.pretti.treasuresapi.datatypes.commands;

import dev.pretti.treasuresapi.enums.EnumParseType;

public class TitleCommandType extends CommandType
{
  private String  title;
  private String  subtitle;
  private Integer fadeInTicks;
  private Integer stayTicks;
  private Integer fadeOutTicks;

  /**
   * Construtor da classe
   */
  public TitleCommandType(EnumParseType type, String title, String subtitle, Integer fadeInTicks, Integer stayTicks,
                          Integer fadeOutTicks)
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

  public Integer getFadeInTicks()
  {
    return fadeInTicks;
  }

  public void setFadeInTicks(Integer fadeInTicks)
  {
    this.fadeInTicks = fadeInTicks;
  }

  public Integer getStayTicks()
  {
    return stayTicks;
  }

  public void setStayTicks(Integer stayTicks)
  {
    this.stayTicks = stayTicks;
  }

  public Integer getFadeOutTicks()
  {
    return fadeOutTicks;
  }

  public void setFadeOutTicks(Integer fadeOutTicks)
  {
    this.fadeOutTicks = fadeOutTicks;
  }
}
