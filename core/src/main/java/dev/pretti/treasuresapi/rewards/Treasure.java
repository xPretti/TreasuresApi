package dev.pretti.treasuresapi.rewards;

import dev.pretti.treasuresapi.conditions.Conditions;
import dev.pretti.treasuresapi.enums.EnumDeliveryType;
import dev.pretti.treasuresapi.enums.EnumVanillaDropsType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Treasure
{
  private       String               name         = "Default";
  private       double               chance       = 100;
  private       String               permission   = "";
  private       int                  limit        = 0;
  private       boolean              random       = false;
  private       EnumVanillaDropsType removeVanillaDrops;
  private       EnumDeliveryType     deliveryType;
  private final Conditions           conditions   = new Conditions();
  private final List<RewardsGroup>   rewardsGroup = new ArrayList<>();

  /**
   * Definições e retornos
   */
  @NotNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getChance() {
    return chance;
  }

  public void setChance(double chance) {
    this.chance = chance;
  }

  @NotNull
  public String getPermission() {
    return permission;
  }

  public void setPermission(String permission) {
    this.permission = permission;
  }

  @NotNull
  public List<RewardsGroup> getRewardsGroup() {
    return rewardsGroup;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public boolean isRandom() {
    return random;
  }

  public void setRandom(boolean random) {
    this.random = random;
  }

  @Nullable
  public EnumVanillaDropsType getRemoveVanillaDrops() {
    return removeVanillaDrops;
  }

  public void setRemoveVanillaDrops(@Nullable EnumVanillaDropsType removeVanillaDrops) {
    this.removeVanillaDrops = removeVanillaDrops;
  }

  @Nullable
  public EnumDeliveryType getDeliveryType() {
    return deliveryType;
  }

  public void setDeliveryType(@Nullable EnumDeliveryType deliveryType) {
    this.deliveryType = deliveryType;
  }

  @NotNull
  public Conditions getConditions() {
    return conditions;
  }
}
