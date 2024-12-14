package dev.pretti.treasuresapi.mapping.interfaces;

import dev.pretti.treasuresapi.contexts.interfaces.IConditionMapContex;

public interface IProcessMapping<T extends IConditionMapContex>
{
  boolean process(T context);
}
