package dev.pretti.treasuresapi.conditions;

import java.util.List;

public class InvalidCondition
{
  private final String       errorMessage;
  private final List<String> invalidValues;

  public InvalidCondition(String errorMessage, List<String> invalidValues)
  {
    this.errorMessage  = errorMessage;
    this.invalidValues = invalidValues;
  }

  public String getErrorMessage()
  {
    return errorMessage;
  }

  public List<String> getInvalidValues()
  {
    return invalidValues;
  }
}
