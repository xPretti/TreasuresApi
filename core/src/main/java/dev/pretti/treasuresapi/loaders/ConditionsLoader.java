package dev.pretti.treasuresapi.loaders;

import dev.pretti.treasuresapi.conditions.Conditions;
import dev.pretti.treasuresapi.conditions.interfaces.ICondition;
import dev.pretti.treasuresapi.conditions.interfaces.IConditionsBuilder;
import dev.pretti.treasuresapi.conditions.interfaces.IInvalidCondition;
import dev.pretti.treasuresapi.conditions.invalids.ComparatorInvalidCondition;
import dev.pretti.treasuresapi.conditions.invalids.ListInvalidCondition;
import dev.pretti.treasuresapi.datatypes.MaterialType;
import dev.pretti.treasuresapi.datatypes.MetadataConditionType;
import dev.pretti.treasuresapi.enums.EnumAccessType;
import dev.pretti.treasuresapi.enums.EnumConditionType;
import dev.pretti.treasuresapi.errors.interfaces.ITreasureErrorLogger;
import dev.pretti.treasuresapi.options.ItemConditionOptions;
import dev.pretti.treasuresapi.utils.MaterialUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ConditionsLoader
{
  private final IConditionsBuilder   conditionsBuilder;
  private final Conditions           conditions;
  private final ITreasureErrorLogger treasureErrorsManager;

  public ConditionsLoader(@NotNull IConditionsBuilder conditionsBuilder, @Nullable Conditions conditions, @Nullable ITreasureErrorLogger treasureErrorsManager)
  {
    this.conditionsBuilder     = conditionsBuilder;
    this.conditions            = conditions;
    this.treasureErrorsManager = treasureErrorsManager;
  }

  /**
   * Método de carregamento
   */
  public boolean loader(ConfigurationSection section)
  {
    if(section != null && conditions != null)
      {
        ConfigurationSection conditionSection = section.getConfigurationSection("conditions");
        if(conditionSection != null)
          {
            boolean sucess = true;
            for(String key : conditionSection.getKeys(false))
              {
                ConfigurationSection newSection = conditionSection.getConfigurationSection(key);
                if(newSection != null)
                  {
                    EnumConditionType condType = getConditionType(newSection);
                    if(condType != null)
                      {
                        sucess = _world(newSection, condType) && sucess;
                        sucess = _biome(newSection, condType) && sucess;
                        sucess = _block(newSection, condType) && sucess;
                        sucess = _placed(newSection, condType) && sucess;
                        sucess = _item(newSection, condType) && sucess;
                        sucess = _comparator(newSection, condType) && sucess;
                        sucess = _numberComparator(newSection, condType) && sucess;
                      }
                    else
                      {
                        sucess = false;
                      }
                  }
              }
            return sucess;
          }
      }
    return true;
  }

  /**
   * Tipos de carregamentos
   */
  private boolean _world(ConfigurationSection section, EnumConditionType condType)
  {
    if(section != null)
      {
        if(condType != null && condType.equals(EnumConditionType.WORLD))
          {
            EnumAccessType type  = getAcessType(section);
            List<String>   names = getTexts(section, "values");
            if(names != null)
              {
                if(conditions != null)
                  {
                    ICondition result = conditionsBuilder.buildWorld(type, names);
                    if(result != null)
                      {
                        return conditions.addCondition(result);
                      }
                  }
              }
          }
      }
    return true;
  }

  private boolean _biome(ConfigurationSection section, EnumConditionType condType)
  {
    if(section != null)
      {
        if(condType != null && condType.equals(EnumConditionType.BIOME))
          {
            EnumAccessType type       = getAcessType(section);
            String         inputNames = "values";
            List<String>   names      = getTexts(section, inputNames);
            if(names != null)
              {
                names.replaceAll(String::toUpperCase);
                if(conditions != null)
                  {
                    ICondition result = conditionsBuilder.buildBiome(type, names);
                    if(result != null)
                      {
                        conditions.addCondition(result);
                        IInvalidCondition invalidCondition = result.getInvalidCondition();
                        if(invalidCondition instanceof ListInvalidCondition)
                          {
                            ListInvalidCondition listInvalidCondition = (ListInvalidCondition) invalidCondition;
                            if(treasureErrorsManager != null)
                              {
                                String identifier = section.getCurrentPath() + "." + inputNames;
                                treasureErrorsManager.add(identifier, listInvalidCondition.getInvalidValues(), invalidCondition.getErrorMessage());
                              }
                            return false;
                          }
                        return true;
                      }
                  }
              }
          }
      }
    return true;
  }

  private boolean _block(ConfigurationSection section, EnumConditionType condType)
  {
    if(section != null)
      {
        if(condType != null && condType.equals(EnumConditionType.BLOCK))
          {
            EnumAccessType type       = getAcessType(section);
            String         inputNames = "values";
            List<String>   names      = getTexts(section, inputNames);
            if(names != null)
              {
                names.replaceAll(String::toUpperCase);
                if(conditions != null)
                  {
                    ICondition result = conditionsBuilder.buildBlock(type, names);
                    if(result != null)
                      {
                        conditions.addCondition(result);
                        IInvalidCondition invalidCondition = result.getInvalidCondition();
                        if(invalidCondition instanceof ListInvalidCondition)
                          {
                            ListInvalidCondition listInvalidCondition = (ListInvalidCondition) invalidCondition;
                            if(treasureErrorsManager != null)
                              {
                                String identifier = section.getCurrentPath() + "." + inputNames;
                                treasureErrorsManager.add(identifier, listInvalidCondition.getInvalidValues(), invalidCondition.getErrorMessage());
                              }
                            return false;
                          }
                        return true;
                      }
                  }
              }
          }
      }
    return true;
  }

  private boolean _item(ConfigurationSection section, EnumConditionType condType)
  {
    if(section != null)
      {
        if(condType != null && condType.isItem())
          {
            String               materialName = section.getString("material", null);
            MaterialType         material     = materialName == null ? null : MaterialUtils.toMaterialType(materialName);
            int                  amount       = section.getInt("amount", 1);
            String               name         = section.getString("name", null);
            List<String>         lores        = section.getStringList("lores");
            ItemConditionOptions        options   = getItemConditionOptions(section.getConfigurationSection("options"));
            List<MetadataConditionType> metadatas = getMetadatas(section.getConfigurationSection("metadatas"));
            if(conditions != null)
              {
                ICondition result = conditionsBuilder.buildItem(condType, material, amount, name, lores, options, metadatas);
                if(result != null)
                  {
                    conditions.addCondition(result);
                    return true;
                  }
              }
          }
      }
    return true;
  }

  private boolean _placed(ConfigurationSection section, EnumConditionType condType)
  {
    if(section != null)
      {
        if(condType != null && condType.equals(EnumConditionType.PLACED))
          {
            boolean value = section.getBoolean("ignore", false);
            if(conditions != null)
              {
                ICondition result = conditionsBuilder.buildPlaced(value);
                if(result != null)
                  {
                    conditions.addCondition(result);
                    return true;
                  }
              }
          }
      }
    return true;
  }

  private boolean _comparator(ConfigurationSection section, EnumConditionType condType)
  {
    if(section != null)
      {
        if(condType != null && condType.isComparator())
          {
            String inputName  = "input";
            String outputName = "output";
            String input      = section.getString(inputName, null);
            String output     = section.getString(outputName, null);
            if(input != null && output != null)
              {
                if(conditions != null)
                  {
                    ICondition result = conditionsBuilder.buildComparator(condType, input, output);
                    if(result != null)
                      {
                        conditions.addCondition(result);
                        return true;
                      }
                  }
              }
          }
      }
    return true;
  }

  private boolean _numberComparator(ConfigurationSection section, EnumConditionType condType)
  {
    if(section != null)
      {
        if(condType != null && condType.isNumberComparator())
          {
            String inputName  = "input";
            String outputName = "output";
            String input      = section.getString(inputName, null);
            String output     = section.getString(outputName, null);
            if(input != null && output != null)
              {
                if(conditions != null)
                  {
                    ICondition result = conditionsBuilder.buildComparator(condType, input, output);
                    if(result != null)
                      {
                        IInvalidCondition invalidCondition = result.getInvalidCondition();
                        if(invalidCondition instanceof ComparatorInvalidCondition)
                          {
                            ComparatorInvalidCondition listInvalidCondition = (ComparatorInvalidCondition) invalidCondition;
                            if(treasureErrorsManager != null)
                              {
                                String inputError  = listInvalidCondition.getInvalidInput();
                                String outputError = listInvalidCondition.getInvalidOutput();
                                String identifier  = section.getCurrentPath();
                                if(inputError != null)
                                  {
                                    treasureErrorsManager.add(identifier, String.format("%s", listInvalidCondition.getInvalidInput()),
                                                              listInvalidCondition.getErrorMessage());
                                  }
                                if(outputError != null)
                                  {
                                    treasureErrorsManager.add(identifier, String.format("%s", listInvalidCondition.getInvalidOutput()),
                                                              listInvalidCondition.getOutputErrorMessage());
                                  }
                              }
                            return false;
                          }
                        conditions.addCondition(result);
                        return true;
                      }
                  }
              }
          }
      }
    return true;
  }

  /**
   * Retornos simplificados
   */
  @NotNull
  private ItemConditionOptions getItemConditionOptions(ConfigurationSection optionsSection)
  {
    if(optionsSection == null)
      {
        return new ItemConditionOptions();
      }
    boolean nameIgnoreCase  = optionsSection.getBoolean("name_ignorecase", false);
    boolean loresIgnoreCase = optionsSection.getBoolean("lores_ignorecase", false);
    boolean nameContains    = optionsSection.getBoolean("name_contains", false);
    boolean loresContains   = optionsSection.getBoolean("lores_contains", false);
    boolean inInventory     = optionsSection.getBoolean("in_inventory", false);
    boolean inHotbar        = optionsSection.getBoolean("in_hotbar", false);
    boolean inArmor         = optionsSection.getBoolean("in_armor", false);
    return new ItemConditionOptions(nameIgnoreCase, loresIgnoreCase, nameContains, loresContains, inInventory, inHotbar, inArmor);

  }

  @Nullable
  private List<MetadataConditionType> getMetadatas(ConfigurationSection metadatasSection)
  {
    if(metadatasSection == null)
      {
        return null;
      }
    List<MetadataConditionType> metadatas = new ArrayList<>();
    for(String key : metadatasSection.getKeys(false))
      {
        ConfigurationSection subSection = metadatasSection.getConfigurationSection(key);
        if(subSection != null)
          {
            String            name          = subSection.getString("type", null);
            String            input         = subSection.getString("key", null);
            String            value         = subSection.getString("value", null);
            EnumConditionType conditionType = null;
            if(name != null)
              {
                conditionType = EnumConditionType.getFromString(name);
              }
            if(conditionType != null && input != null && value != null)
              {
                metadatas.add(new MetadataConditionType(conditionType, input, value));
              }
            else if(treasureErrorsManager != null)
              {
                String identifier = metadatasSection.getCurrentPath() + "." + key;
                if(conditionType != null)
                  {
                    treasureErrorsManager.add(identifier, name, "Invalid metadata type");
                  }
                if(input != null)
                  {
                    treasureErrorsManager.add(identifier, input, "Invalid metadata key");
                  }
                if(value != null)
                  {
                    treasureErrorsManager.add(identifier, value, "Invalid metadata value");
                  }
              }
          }
      }
    if(metadatas.isEmpty())
      {
        return null;
      }
    return metadatas;
  }

  @Nullable
  private EnumConditionType getConditionType(ConfigurationSection conditionSection)
  {
    String inputType = "type";
    if(conditionSection.contains(inputType))
      {
        String typeValue = conditionSection.getString(inputType);
        if(typeValue != null)
          {
            typeValue = typeValue.trim();
          }
        EnumConditionType value = EnumConditionType.getFromString(typeValue);
        if(value == null)
          {
            if(treasureErrorsManager != null)
              {
                String identifier = conditionSection.getCurrentPath() + "." + inputType;
                treasureErrorsManager.add(identifier, typeValue, "Invalid condition type");
              }
            return null;
          }
        return value;
      }
    return null;
  }

  private EnumAccessType getAcessType(ConfigurationSection conditionSection)
  {
    String inputMethod = "method";
    if(conditionSection.contains(inputMethod))
      {
        String accessValue = conditionSection.getString(inputMethod);
        if(accessValue != null)
          {
            accessValue = accessValue.trim();
          }
        EnumAccessType value = EnumAccessType.getFromString(accessValue);
        if(value == null)
          {
            if(treasureErrorsManager != null)
              {
                String identifier = conditionSection.getCurrentPath() + "." + inputMethod;
                treasureErrorsManager.add(identifier, accessValue, "Invalid access type");
              }
            return EnumAccessType.WHITELIST;
          }
        return value;
      }
    return EnumAccessType.WHITELIST;
  }

  private String getText(ConfigurationSection conditionSection, String inputName)
  {
    if(conditionSection.contains(inputName))
      {
        String value = conditionSection.getString(inputName);
        if(value != null)
          {
            value = value.trim();
          }
        return value;
      }
    return null;
  }

  private List<String> getTexts(ConfigurationSection conditionSection, String inputNames)
  {
    if(conditionSection.contains(inputNames))
      {
        List<String> values = conditionSection.getStringList(inputNames);
        values.replaceAll(String::trim);
        return values;
      }
    return null;
  }
}

