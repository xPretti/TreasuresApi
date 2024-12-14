package dev.pretti.treasuresapi.utils;

import dev.pretti.treasuresapi.datatypes.commands.*;
import dev.pretti.treasuresapi.datatypes.commands.base.CommandType;
import org.jetbrains.annotations.Nullable;

public class CloneableUtils
{
  /**
   * Métodos de clonagem de classes
   */
  @Nullable
  public static CommandType clone(CommandType source)
  {
    if(source instanceof RangeTextCommandType)
      {
        RangeTextCommandType type = (RangeTextCommandType) source;
        return new RangeTextCommandType(type.getType(), type.getRange(), type.getText());
      }
    else if(source instanceof TextCommandType)
      {
        TextCommandType type = (TextCommandType) source;
        return new TextCommandType(type.getType(), type.getText());
      }
    else if(source instanceof TitleCommandType)
      {
        TitleCommandType type = (TitleCommandType) source;
        return new TitleCommandType(type.getType(), type.getTitle(), type.getSubtitle(), type.getFadeInTicks(), type.getStayTicks(), type.getFadeOutTicks());
      }
    else if(source instanceof SoundCommandType)
      {
        SoundCommandType type = (SoundCommandType) source;
        return new SoundCommandType(type.getType(), type.getSound(), type.getVolume(), type.getPitch());
      }
    else if(source instanceof EffectCommandType)
      {
        EffectCommandType type = (EffectCommandType) source;
        return new EffectCommandType(type.getActionType(), type.getEffectType(), type.getDuration(), type.getLevel());
      }
    return null;
  }
}
