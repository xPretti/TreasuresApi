package dev.pretti.treasuresapi.loaders;

import dev.pretti.treasuresapi.conditions.Conditions;
import dev.pretti.treasuresapi.conditions.InvalidCondition;
import dev.pretti.treasuresapi.conditions.interfaces.ICondition;
import dev.pretti.treasuresapi.conditions.interfaces.IConditionsBuilder;
import dev.pretti.treasuresapi.enums.EnumAccessType;
import dev.pretti.treasuresapi.enums.EnumConditionType;
import dev.pretti.treasuresapi.result.TreasureResult;
import dev.pretti.treasuresapi.result.errors.types.TreasureErrorResult;
import dev.pretti.treasuresapi.result.errors.types.TreasureErrorsResult;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ConditionsLoader
{
  private final IConditionsBuilder conditionsBuilder;
  private final Conditions         conditions;
  private final TreasureResult     treasureResult;

  public ConditionsLoader(@NotNull IConditionsBuilder conditionsBuilder, @Nullable Conditions conditions, @NotNull TreasureResult treasureResult)
  {
    this.conditionsBuilder = conditionsBuilder;
    this.conditions        = conditions;
    this.treasureResult    = treasureResult;
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
                        String identifier = section.getCurrentPath() + "." + inputNames;
                        treasureResult.addErrors(new TreasureErrorsResult(identifier, invalidCondition.getErrorMessage(), invalidCondition.getInvalidValues()));
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
                        String identifier = section.getCurrentPath() + "." + inputNames;
                        treasureResult.addErrors(new TreasureErrorsResult(identifier, invalidCondition.getErrorMessage(), invalidCondition.getInvalidValues()));
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
            String identifier = conditionSection.getCurrentPath() + "." + inputType;
            treasureResult.addErrors(new TreasureErrorResult(identifier, typeValue));
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
            String identifier = conditionSection.getCurrentPath() + "." + inputMethod;
            treasureResult.addErrors(new TreasureErrorResult(identifier, accessValue));
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

