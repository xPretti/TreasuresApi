package dev.pretti.treasuresapi.conditions.invalids;

import dev.pretti.treasuresapi.conditions.interfaces.IInvalidCondition;
import org.jetbrains.annotations.Nullable;

public class ComparatorInvalidCondition implements IInvalidCondition
{
  private final String errorMessage;
  private final String outputErrorMessage;
  private final String invalidInput;
  private final String invalidOutput;

  public ComparatorInvalidCondition(String errorMessage, String outputErrorMessage, @Nullable String invalidInput, @Nullable String invalidOutput)
  {
    this.errorMessage       = errorMessage;
    this.outputErrorMessage = outputErrorMessage;
    this.invalidInput       = invalidInput;
    this.invalidOutput      = invalidOutput;
  }

  @Override
  public String getErrorMessage()
  {
    return errorMessage;
  }

  public String getOutputErrorMessage()
  {
    return outputErrorMessage;
  }

  @Nullable
  public String getInvalidInput()
  {
    return invalidInput;
  }

  @Nullable
  public String getInvalidOutput()
  {
    return invalidOutput;
  }
}
