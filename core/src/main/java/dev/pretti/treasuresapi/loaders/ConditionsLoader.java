package dev.pretti.treasuresapi.loaders;

import dev.pretti.treasuresapi.conditions.Conditions;
import dev.pretti.treasuresapi.conditions.InvalidCondition;
import dev.pretti.treasuresapi.conditions.interfaces.ICondition;
import dev.pretti.treasuresapi.conditions.interfaces.IConditionsBuilder;
import dev.pretti.treasuresapi.enums.EnumAccessType;
import dev.pretti.treasuresapi.enums.EnumConditionType;
import dev.pretti.treasuresapi.errors.interfaces.ITreasureErrorLogger;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ConditionsLoader
{
  private final IConditionsBuilder     conditionsBuilder;
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
                sucess = _world(conditionSection.getConfigurationSection(key)) && sucess;
                sucess = _biome(conditionSection.getConfigurationSection(key)) && sucess;
                sucess = _block(conditionSection.getConfigurationSection(key)) && sucess;
              }
            return sucess;
          }
      }
    return true;
  }

  /**
   * Tipos de carregamentos
   */
  private boolean _world(ConfigurationSection section)
  {
    if(section != null)
      {
        EnumConditionType condType = getConditionType(section);
        EnumAccessType    type     = getAcessType(section);
        if(condType != null && condType.equals(EnumConditionType.WORLD))
          {
            List<String> names = getNames(section, "values");
            if(names != null)
              {
                if(conditions != null)
                  {
                    ICondition result = conditionsBuilder.buildWorld(type, names);
                    return conditions.addCondition(result);
                  }
              }
          }
      }
    return true;
  }

  private boolean _biome(ConfigurationSection section)
  {
    if(section != null)
      {
        EnumConditionType condType = getConditionType(section);
        EnumAccessType    type     = getAcessType(section);
        if(condType != null && condType.equals(EnumConditionType.BIOME))
          {
            String       inputNames = "values";
            List<String> names      = getNames(section, inputNames);
            if(names != null)
              {
                names.replaceAll(String::toUpperCase);
                if(conditions != null)
                  {
                    ICondition result = conditionsBuilder.buildBiome(type, names);
                    conditions.addCondition(result);
                    InvalidCondition invalidCondition = result.getInvalidCondition();
                    if(invalidCondition != null)
                      {
                        if(treasureErrorsManager != null)
                          {
                            String identifier = section.getCurrentPath() + "." + inputNames;
                            treasureErrorsManager.add(identifier, invalidCondition.getInvalidValues(), invalidCondition.getErrorMessage());
                          }
                        return false;
                      }
                    return true;
                  }
              }
          }
      }
    return true;
  }

  private boolean _block(ConfigurationSection section)
  {
    if(section != null)
      {
        EnumConditionType condType = getConditionType(section);
        EnumAccessType    type     = getAcessType(section);
        if(condType != null && condType.equals(EnumConditionType.BLOCK))
          {
            String       inputNames = "values";
            List<String> names      = getNames(section, inputNames);
            if(names != null)
              {
                names.replaceAll(String::toUpperCase);
                if(conditions != null)
                  {
                    ICondition result = conditionsBuilder.buildBlock(type, names);
                    conditions.addCondition(result);
                    InvalidCondition invalidCondition = result.getInvalidCondition();
                    if(invalidCondition != null)
                      {
                        if(treasureErrorsManager != null)
                          {
                            String identifier = section.getCurrentPath() + "." + inputNames;
                            treasureErrorsManager.add(identifier, invalidCondition.getInvalidValues(), invalidCondition.getErrorMessage());
                          }
                        return false;
                      }
                    return true;
                  }
              }
          }
      }
    return true;
  }

  /**
   * Retornos simplificados
   */
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

  private List<String> getNames(ConfigurationSection conditionSection, String inputNames)
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

