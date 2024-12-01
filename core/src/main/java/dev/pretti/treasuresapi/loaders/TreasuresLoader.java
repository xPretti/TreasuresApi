package dev.pretti.treasuresapi.loaders;

import dev.pretti.treasuresapi.conditions.interfaces.IConditionsBuilder;
import dev.pretti.treasuresapi.datatypes.commands.CommandType;
import dev.pretti.treasuresapi.dynamics.EnchantDynamic;
import dev.pretti.treasuresapi.dynamics.IntDynamic;
import dev.pretti.treasuresapi.result.TreasureResult;
import dev.pretti.treasuresapi.result.interfaces.ITreasureResult;
import dev.pretti.treasuresapi.rewards.Options.RewardOptions;
import dev.pretti.treasuresapi.rewards.Rewards;
import dev.pretti.treasuresapi.rewards.RewardsGroup;
import dev.pretti.treasuresapi.rewards.Treasure;
import dev.pretti.treasuresapi.rewards.types.CommandReward;
import dev.pretti.treasuresapi.rewards.types.ItemReward;
import dev.pretti.treasuresapi.rewards.types.XpReward;
import dev.pretti.treasuresapi.utils.ConverterUtils;
import dev.pretti.treasuresapi.utils.FileUtils;
import dev.pretti.treasuresapi.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TreasuresLoader
{
  private static final String _treasureSection      = "sections";
  private static final String _randomRewardsSection = "random";
  private static final String _rewardsSection       = "rewards";
  private static final String _chanceSection        = "chance";
  private static final String _permissionSection    = "permission";
  private static final String _limitSection         = "limit";
  private static final String _useLootingSection    = "use-looting";
  private static final String _useFortuneSection    = "use-fortune";
  private static final String _itemSection          = "item";
  private static final String _typeSection          = "type";
  private static final String _nameSection          = "name";
  private static final String _loresSection         = "lores";
  private static final String _amountSection        = "amount";
  private static final String _enchantsSection      = "enchants";
  private static final String _expSection           = "exp";
  private static final String _xpLevelSection       = "level";
  private static final String _commandsSection      = "commands";
  private static final String _flagsSection         = "flags";

  private final IConditionsBuilder conditionsBuilder;

  private final TreasureResult treasureResult = new TreasureResult();

  /**
   * Contrutor da classe
   */
  public TreasuresLoader(IConditionsBuilder conditionsBuilder)
  {
    this.conditionsBuilder = conditionsBuilder;
  }

  /**
   * Método de carregamento dos tesouros
   */
  @NotNull
  public ITreasureResult loader(String folder)
  {
    treasureResult.setTreasures(_convert(folder));
    return (treasureResult);
  }

  /**
   * Métodos de conversão
   */
  @Nullable
  private List<Treasure> _convert(String folder)
  {
    if(FileUtils.FolderExist(folder))
      {
        File dir = new File(folder);
        if(dir.isDirectory())
          {
            File[] files = dir.listFiles();
            if(files != null)
              {
                if(files.length > 0)
                  {
                    List<Treasure> treasures = new ArrayList<>();
                    for(File file : files)
                      {
                        FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);
                        _loadFolder(fileConfig, treasures);
                      }
                    if(!treasures.isEmpty())
                      {
                        return treasures;
                      }
                  }
              }
          }
      }
    return null;
  }

  private void _loadFolder(FileConfiguration file, List<Treasure> treasures)
  {
    ConfigurationSection section = file.getConfigurationSection("");
    if(section != null)
      {
        for(String key : section.getKeys(false))
          {
            ConfigurationSection treasureSection = section.getConfigurationSection(key);
            _createTreasure(treasureSection, treasures);
          }
      }
  }

  private void _createTreasure(ConfigurationSection currentSection, List<Treasure> treasures)
  {
    if(currentSection != null)
      {
        String   treasureName = currentSection.getName();
        Treasure treasure     = new Treasure();

        ConditionsLoader conditionsLoader = new ConditionsLoader(conditionsBuilder, treasure.getConditions(), treasureResult);
        conditionsLoader.loader(currentSection);

        treasure.setName(treasureName);
        if(currentSection.contains(_chanceSection))
          {
            treasure.setChance(currentSection.getDouble(_chanceSection));
          }
        if(currentSection.contains(_permissionSection))
          {
            treasure.setPermission(currentSection.getString(_permissionSection));
          }
        if(currentSection.contains(_randomRewardsSection))
          {
            treasure.setRandom(currentSection.getBoolean(_randomRewardsSection));
          }
        if(currentSection.contains(_limitSection))
          {
            treasure.setLimit(currentSection.getInt(_limitSection));
          }
        if(_loadActions(currentSection, treasure))
          {
            treasures.add(treasure);
          }
      }
  }

  private boolean _loadActions(ConfigurationSection currentSection, Treasure treasure)
  {
    if(treasure != null && currentSection != null)
      {
        ConfigurationSection actionSection = currentSection.getConfigurationSection(_treasureSection);
        if(actionSection != null)
          {
            for(String key : actionSection.getKeys(false))
              {
                ConfigurationSection subActionSection = actionSection.getConfigurationSection(key);
                _createRewards(subActionSection, treasure);
              }
            return (!treasure.getRewardsGroup().isEmpty());
          }
      }
    return false;
  }

  private void _createRewards(ConfigurationSection currentSection, Treasure treasure)
  {
    if(currentSection != null)
      {
        RewardsGroup rewardsGroup = new RewardsGroup();
        if(currentSection.contains(_randomRewardsSection))
          {
            rewardsGroup.setRandomRewards(currentSection.getBoolean(_randomRewardsSection));
          }
        if(currentSection.contains(_limitSection))
          {
            rewardsGroup.setLimit(currentSection.getInt(_limitSection));
          }
        if(currentSection.contains(_permissionSection))
          {
            rewardsGroup.setPermission(currentSection.getString(_permissionSection));
          }
        if(currentSection.contains(_chanceSection))
          {
            rewardsGroup.setChance(currentSection.getDouble(_chanceSection));
          }
        if(_loadReward(currentSection, rewardsGroup))
          {
            treasure.getRewardsGroup().add(rewardsGroup);
          }
      }
  }

  private boolean _loadReward(ConfigurationSection currentSection, RewardsGroup rewardsGroup)
  {
    ConfigurationSection rewardSection = currentSection.getConfigurationSection(_rewardsSection);
    if(rewardSection != null)
      {
        for(String key : rewardSection.getKeys(false))
          {
            ConfigurationSection subRewardSection = rewardSection.getConfigurationSection(key);
            _createReward(subRewardSection, rewardsGroup);
          }
        return (!rewardsGroup.getRewards().isEmpty());
      }
    return false;
  }

  private void _createReward(ConfigurationSection subRewardSection, RewardsGroup rewardsGroup)
  {
    if(subRewardSection != null)
      {
        Rewards rewards = new Rewards();
        rewards.setOptions(_optionsLoader(subRewardSection));
        if(subRewardSection.contains(_chanceSection))
          {
            rewards.setChance(subRewardSection.getDouble(_chanceSection));
          }
        if(subRewardSection.contains(_permissionSection))
          {
            rewards.setPermission(subRewardSection.getString(_permissionSection));
          }
        if(subRewardSection.contains(_expSection))
          {
            String exp = subRewardSection.getString(_expSection);
            rewards.getRewards().add(_expLoader(exp, false));
          }
        else if(subRewardSection.contains(_xpLevelSection))
          {
            String exp = subRewardSection.getString(_xpLevelSection);
            rewards.getRewards().add(_expLoader(exp, true));
          }
        if(subRewardSection.contains(_commandsSection))
          {
            rewards.getRewards().add(_commandLoader(subRewardSection.getStringList(_commandsSection)));
          }
        if(subRewardSection.contains(_itemSection))
          {
            rewards.getRewards().add(_itemLoader(subRewardSection));
          }
        if(!rewards.getRewards().isEmpty())
          {
            rewardsGroup.getRewards().add(rewards);
          }
      }
  }

  @NotNull
  private RewardOptions _optionsLoader(ConfigurationSection subRewardSection)
  {
    if(subRewardSection != null)
      {
        boolean useLooting = false;
        boolean useFortune = false;
        if(subRewardSection.contains(_useLootingSection))
          {
            useLooting = subRewardSection.getBoolean(_useLootingSection);
          }
        if(subRewardSection.contains(_useFortuneSection))
          {
            useFortune = subRewardSection.getBoolean(_useFortuneSection);
          }
        return new RewardOptions(useLooting, useFortune);
      }
    return new RewardOptions();
  }

  @Nullable
  private CommandReward _commandLoader(List<String> values)
  {
    if(values != null)
      {
        if(!values.isEmpty())
          {
            CommandReward commandReward = new CommandReward();
            CommandType   type;
            for(String command : values)
              {
                type = ConverterUtils.getCommandType(command);
                if(type != null)
                  {
                    commandReward.getCommands().add(type);
                  }
              }
            return commandReward;
          }
      }
    return null;
  }

  @Nullable
  private XpReward _expLoader(String value, boolean isLevel)
  {
    if(value != null)
      {
        XpReward   expReward = new XpReward(isLevel);
        IntDynamic dynamic   = new IntDynamic();
        ConverterUtils.setDynamic(dynamic, value);
        expReward.setXp(dynamic);
        return expReward;
      }
    return null;
  }

  @Nullable
  private ItemReward _itemLoader(ConfigurationSection section)
  {
    ConfigurationSection itemSection = section.getConfigurationSection(_itemSection);
    if(itemSection != null)
      {
        ItemReward itemReward = new ItemReward();
        _itemLoaderType(itemSection, itemReward);
        _itemLoaderName(itemSection, itemReward);
        _itemLoaderLore(itemSection, itemReward);
        _itemLoaderAmount(itemSection, itemReward);
        _itemLoaderEnchant(itemSection, itemReward);
        _itemLoaderFlags(itemSection, itemReward);
        return itemReward;
      }
    return null;
  }

  private void _itemLoaderType(ConfigurationSection itemSection, ItemReward itemReward)
  {
    String   name     = _typeSection;
    Material material = Material.STONE;
    byte     data     = 0;
    if(itemSection.contains(name))
      {
        String type = itemSection.getString(name);
        if(type != null)
          {
            String[] typeArray = type.split(":");
            if(typeArray.length > 0)
              {
                material = Material.getMaterial(typeArray[0]);
              }
            if(typeArray.length > 1)
              {
                data = Byte.parseByte(typeArray[1]);
              }
          }
      }
    itemReward.setMaterial(material, data);
  }

  private void _itemLoaderName(ConfigurationSection itemSection, ItemReward itemReward)
  {
    String name = _nameSection;
    if(itemSection.contains(name))
      {
        String displayName = itemSection.getString(name);
        itemReward.setName(displayName);
      }
  }

  private void _itemLoaderLore(ConfigurationSection itemSection, ItemReward itemReward)
  {
    String name = _loresSection;
    if(itemSection.contains(name))
      {
        List<String> lores = itemSection.getStringList(name);
        itemReward.setLore(lores);
      }
  }

  private void _itemLoaderAmount(ConfigurationSection itemSection, ItemReward itemReward)
  {
    String     name    = _amountSection;
    IntDynamic dynamic = new IntDynamic(1, 1);
    if(itemSection.contains(name))
      {
        String amount = itemSection.getString(name);
        ConverterUtils.setDynamic(dynamic, amount);
      }
    itemReward.setAmount(dynamic);
  }

  private void _itemLoaderEnchant(ConfigurationSection itemSection, ItemReward itemReward)
  {
    String name = _enchantsSection;
    if(itemSection.contains(name))
      {
        List<String> enchants = itemSection.getStringList(name);
        if(!enchants.isEmpty())
          {
            List<EnchantDynamic> enchantList = new ArrayList<>();
            for(String value : enchants)
              {
                value = value.trim();
                List<String> values = StringUtils.splitNoEmpty(value, " ");
                if(values != null)
                  {
                    if(!values.isEmpty())
                      {
                        EnchantDynamic enchantDynamic = new EnchantDynamic(Enchantment.getByName(values.get(0)), 1, 1);
                        if(values.size() > 1)
                          {
                            ConverterUtils.setDynamic(enchantDynamic, values.get(1));
                          }
                        enchantList.add(enchantDynamic);
                      }
                  }
              }
            itemReward.setEnchant(enchantList);
          }
      }
  }

  private void _itemLoaderFlags(ConfigurationSection itemSection, ItemReward itemReward)
  {
    String name = _flagsSection;
    if(itemSection.contains(name))
      {
        List<String> flagsText = itemSection.getStringList(name);
        if(!flagsText.isEmpty())
          {
            ItemFlag      newFlag;
            Set<ItemFlag> flags = new HashSet<>();
            for(String flagName : flagsText)
              {
                flagName = flagName.trim().toUpperCase();
                newFlag  = ItemFlag.valueOf(flagName);
                flags.add(newFlag);
              }
            itemReward.setFlags(flags);
          }
      }
  }
}
