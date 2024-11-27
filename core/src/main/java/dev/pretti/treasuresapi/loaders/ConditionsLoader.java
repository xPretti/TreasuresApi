package dev.pretti.treasuresapi.loaders;

import dev.pretti.treasuresapi.conditions.Conditions;
import dev.pretti.treasuresapi.conditions.interfaces.IConditionsBuilder;
import dev.pretti.treasuresapi.enums.EnumAccessType;
import dev.pretti.treasuresapi.enums.EnumConditionType;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ConditionsLoader
{
  private final IConditionsBuilder conditionsBuilder;
  private final Conditions         conditions;

  public ConditionsLoader(@NotNull IConditionsBuilder conditionsBuilder, @Nullable Conditions conditions)
  {
    this.conditionsBuilder = conditionsBuilder;
    this.conditions        = conditions;
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
        if(condType != null && condType.equals(EnumConditionType.WORLD))
          {
            EnumAccessType type  = getAcessType(section);
            List<String>   names = getNames(section);
            if(names != null)
              {
                if(conditions != null)
                  {
                    try
                      {
                        return (conditions.addCondition(conditionsBuilder.buildWorld(type, names)));
                      } catch(Throwable e)
                      {
                        return false;
                      }
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
        if(condType != null && condType.equals(EnumConditionType.BIOME))
          {
            EnumAccessType type  = getAcessType(section);
            List<String>   names = getNames(section);
            if(names != null)
              {
                names.replaceAll(String::toUpperCase);
                if(conditions != null)
                  {
                    try
                      {
                        return (conditions.addCondition(conditionsBuilder.buildBiome(type, names)));
                      } catch(Throwable e)
                      {
                        return false;
                      }

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
        if(condType != null && condType.equals(EnumConditionType.BLOCK))
          {
            EnumAccessType type  = getAcessType(section);
            List<String>   names = getNames(section);
            if(names != null)
              {
                names.replaceAll(String::toUpperCase);
                if(conditions != null)
                  {
                    try
                      {
                        return (conditions.addCondition(conditionsBuilder.buildBlock(type, names)));
                      } catch(Throwable e)
                      {
                        return false;
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
        return EnumConditionType.getFromString(typeValue);
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
        return EnumAccessType.getFromString(accessValue);
      }
    return EnumAccessType.WHITELIST;
  }

  private List<String> getNames(ConfigurationSection conditionSection)
  {
    String inputNames = "values";
    if(conditionSection.contains(inputNames))
      {
        List<String> values = conditionSection.getStringList(inputNames);
        values.replaceAll(String::trim);
        return values;
      }
    return null;
  }
}

