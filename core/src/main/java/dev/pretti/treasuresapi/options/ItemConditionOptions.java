package dev.pretti.treasuresapi.options;

public class ItemConditionOptions
{
  private boolean nameIgnoreCase;
  private boolean loresIgnoreCase;
  private boolean nameContains;
  private boolean loresContains;
  private boolean inInventory;
  private boolean inHotbar;
  private boolean inArmor;
  private boolean inHand;
  private boolean inOffHand;

  /**
   * Contrutor da classe
   */
  public ItemConditionOptions()
  {
    this(false, false, false, false, false, false, false, true, false);
  }

  public ItemConditionOptions(boolean nameIgnoreCase, boolean loresIgnoreCase, boolean nameContains, boolean loresContains, boolean inInventory, boolean inHotbar, boolean inArmor, boolean inHand,
                              boolean inOffHand)
  {
    this.nameIgnoreCase  = nameIgnoreCase;
    this.loresIgnoreCase = loresIgnoreCase;
    this.nameContains    = nameContains;
    this.loresContains   = loresContains;
    this.inInventory     = inInventory;
    this.inHotbar        = inHotbar;
    this.inArmor         = inArmor;
    this.inHand          = inHand;
    this.inOffHand       = inOffHand;
  }

  /**
   * Definições e retornos
   */
  public boolean isNameIgnoreCase()
  {
    return nameIgnoreCase;
  }

  public void setNameIgnoreCase(boolean nameIgnoreCase)
  {
    this.nameIgnoreCase = nameIgnoreCase;
  }

  public boolean isLoresIgnoreCase()
  {
    return loresIgnoreCase;
  }

  public void setLoresIgnoreCase(boolean loresIgnoreCase)
  {
    this.loresIgnoreCase = loresIgnoreCase;
  }

  public boolean isNameContains()
  {
    return nameContains;
  }

  public void setNameContains(boolean nameContains)
  {
    this.nameContains = nameContains;
  }

  public boolean isLoresContains()
  {
    return loresContains;
  }

  public void setLoresContains(boolean loresContains)
  {
    this.loresContains = loresContains;
  }

  public boolean isInInventory()
  {
    return inInventory;
  }

  public void setInInventory(boolean inInventory)
  {
    this.inInventory = inInventory;
  }

  public boolean isInHotbar()
  {
    return inHotbar;
  }

  public void setInHotbar(boolean inHotbar)
  {
    this.inHotbar = inHotbar;
  }

  public boolean isInArmor()
  {
    return inArmor;
  }

  public void setInArmor(boolean inArmor)
  {
    this.inArmor = inArmor;
  }

  public boolean isInHand()
  {
    return inHand;
  }

  public void setInHand(boolean inHand)
  {
    this.inHand = inHand;
  }

  public boolean isInOffHand()
  {
    return inOffHand;
  }

  public void setInOffHand(boolean inOffHand)
  {
    this.inOffHand = inOffHand;
  }
}
