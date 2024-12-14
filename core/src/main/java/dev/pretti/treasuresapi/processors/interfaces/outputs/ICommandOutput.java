package dev.pretti.treasuresapi.processors.interfaces.outputs;

import dev.pretti.treasuresapi.datatypes.commands.*;
import dev.pretti.treasuresapi.contexts.TreasureContext;
import org.jetbrains.annotations.NotNull;

public interface ICommandOutput
{
  boolean process(@NotNull TreasureContext context, CommandType command);

  boolean process(@NotNull TreasureContext context, RangeTextCommandType command);

  boolean process(@NotNull TreasureContext context, TitleCommandType command);

  boolean process(@NotNull TreasureContext context, TextCommandType command);

  boolean process(@NotNull TreasureContext context, SoundCommandType command);
}
