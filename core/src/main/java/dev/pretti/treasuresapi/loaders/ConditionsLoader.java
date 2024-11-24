package dev.pretti.treasuresapi.loaders;

import dev.pretti.treasuresapi.enums.EnumAccessType;
import dev.pretti.treasuresapi.conditions.Conditions;
import dev.pretti.treasuresapi.conditions.types.BiomeCondition;
import dev.pretti.treasuresapi.conditions.types.WorldCondition;
import dev.pretti.treasuresapi.conditions.types.base.AccessCondition;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class ConditionsLoader
{

  /**
   * Método de carregamento
   */
  public void loader(ConfigurationSection section, Conditions conditions)
  {
    if(section != null)
      {
        ConfigurationSection conditionSection = section.getConfigurationSection("conditions");
        if(conditionSection != null)
          {
            _world(conditionSection, conditions);
            _biome(conditionSection, conditions);
          }
      }
  }

  /**
   * Tipos de carregamentos
   */
  private void _world(ConfigurationSection section, Conditions conditions)
  {
    if(section != null)
      {
        ConfigurationSection conditionSection = section.getConfigurationSection("worlds");
        if(conditionSection != null)
          {
            WorldCondition condition = new WorldCondition();
            _accessCondition(conditionSection, conditions, condition);
          }
      }
  }

  private void _biome(ConfigurationSection section, Conditions conditions)
  {
    if(section != null)
      {
        ConfigurationSection conditionSection = section.getConfigurationSection("biomes");
        if(conditionSection != null)
          {
            BiomeCondition condition = new BiomeCondition();
            _accessCondition(conditionSection, conditions, condition);
          }
      }
  }

  /**
   * Método de carregamento do tipo AccessCondition
   */
  private void _accessCondition(ConfigurationSection conditionSection, Conditions conditions, AccessCondition condition)
  {
    String inputMethod = "method";
    if(conditionSection.contains(inputMethod))
      {
        String acessType = conditionSection.getString(inputMethod).trim();
        condition.setAccessType(EnumAccessType.getFromString(acessType));
      }
    String inputNames = "names";
    if(conditionSection.contains(inputNames))
      {
        List<String> names = conditionSection.getStringList(inputNames);
        int          count = 0;
        for(String name : names)
          {
            condition.getNames().put(name, count);
            count++;
          }
        conditions.addCondition(condition);
      }
  }

}

