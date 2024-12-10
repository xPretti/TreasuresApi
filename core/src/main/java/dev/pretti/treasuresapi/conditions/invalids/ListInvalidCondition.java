package dev.pretti.treasuresapi.conditions.invalids;

import dev.pretti.treasuresapi.conditions.interfaces.IInvalidCondition;

import java.util.List;

public class ListInvalidCondition implements IInvalidCondition
{
  private final String       errorMessage;
  private final List<String> invalidValues;

  public ListInvalidCondition(String errorMessage, List<String> invalidValues)
  {
    this.errorMessage  = errorMessage;
    this.invalidValues = invalidValues;
  }

  @Override
  public String getErrorMessage()
  {
    return errorMessage;
  }

  public List<String> getInvalidValues()
  {
    return invalidValues;
  }
}
