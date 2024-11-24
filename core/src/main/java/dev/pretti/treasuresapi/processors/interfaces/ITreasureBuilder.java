package dev.pretti.treasuresapi.processors.interfaces;

import dev.pretti.treasuresapi.rewards.Treasure;

public interface ITreasureBuilder
{
  ITreasureProcessor build(Treasure treasure);
}
