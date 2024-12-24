package dev.pretti.treasuresapi.utils;

import dev.pretti.treasuresapi.datatypes.EnchantType;
import dev.pretti.treasuresapi.datatypes.ItemType;
import dev.pretti.treasuresapi.datatypes.commands.*;
import dev.pretti.treasuresapi.datatypes.commands.base.CommandType;
import dev.pretti.treasuresapi.dynamics.DoubleDynamic;
import dev.pretti.treasuresapi.dynamics.Dynamic;
import dev.pretti.treasuresapi.dynamics.EnchantDynamic;
import dev.pretti.treasuresapi.dynamics.IntDynamic;
import dev.pretti.treasuresapi.enums.EnumCommandType;
import dev.pretti.treasuresapi.enums.EnumEffectActionType;
import dev.pretti.treasuresapi.rewards.types.ItemReward;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ConverterUtils
{
  private static Sound DEFAULT_SOUND = Sound.values()[0];

  /**
   * Retorna uma estrutura de item simplificada
   */
  @Nullable
  public static ItemType getItemType(ItemReward itemReward)
  {
    if(itemReward != null)
      {
        ItemType item = new ItemType();
        item.setAmount(itemReward.getAmount().getValue());
        item.setType(itemReward.getMaterial());
        item.setData(itemReward.getData());
        item.setItemName(itemReward.getName());
        if(itemReward.getLore() != null)
          {
            item.setLores(new ArrayList<>(itemReward.getLore()));
          }
        item.setEnchants(getEnchantTypes(itemReward.getEnchant()));
        item.setFlags(itemReward.getFlags());
        if(itemReward.getMetadata() != null)
          {
            item.setMetadata(new ArrayList<>(itemReward.getMetadata()));
          }
        return item;
      }
    return null;
  }

  /**
   * Retorna uma lista de encantamentos simplificada
   */
  @Nullable
  public static List<EnchantType> getEnchantTypes(List<EnchantDynamic> enchantDynamicList)
  {
    if(enchantDynamicList != null)
      {
        if(!enchantDynamicList.isEmpty())
          {
            List<EnchantType> enchantTypes = new ArrayList<>();
            int               level;
            for(EnchantDynamic enchant : enchantDynamicList)
              {
                level = enchant.getValue();
                if(level > 0)
                  {
                    enchantTypes.add(new EnchantType(enchant.getEnchantment(), level));
                  }
              }
            if(!enchantTypes.isEmpty())
              {
                return enchantTypes;
              }
          }
      }
    return null;
  }

  /**
   * Métodos de conversão de texto "1" ou "1-100" para uma tipo de dynamic
   */
  //text to IntDynamic
  public static boolean setDynamic(Dynamic ref, String text)
  {
    if(text != null && ref != null)
      {
        String   fixText = text.trim();
        String[] texts   = fixText.split("~");
        if(texts.length > 0)
          {
            double v1 = parseDoubleSafe(texts[0]);
            double v2 = texts.length > 1 ? parseDoubleSafe(texts[1]) : 0;
            if(ref instanceof IntDynamic)
              {
                IntDynamic intDyn = (IntDynamic) ref;
                intDyn.setMinAndMax((int) v1, (int) v2);
              }
            else if(ref instanceof DoubleDynamic)
              {
                DoubleDynamic doubleDyn = (DoubleDynamic) ref;
                doubleDyn.setMinAndMax(v1, v2);
              }
            return true;
          }
      }
    return false;
  }

  private static double parseDoubleSafe(String text)
  {
    try
      {
        return Double.parseDouble(text);
      } catch(NumberFormatException e)
      {
        return 0;
      }
  }

  /**
   * Método de conversão de comandos
   */
  public static String getCommandText(String text)
  {
    if(text != null)
      {
        int startIndex = Math.max(0, text.indexOf("["));
        int endIndex   = text.indexOf("]");
        endIndex = endIndex == -1 ? text.length() - 1 : endIndex;
        return text.substring(startIndex, endIndex + 1);
      }
    return "";
  }

  @Nullable
  public static CommandType getCommandType(String text)
  {
    if(text != null)
      {
        int startIndex = Math.max(0, text.indexOf("["));
        int endIndex   = text.indexOf("]");
        endIndex = endIndex == -1 ? text.length() - 1 : endIndex;
        String type   = text.substring(startIndex, endIndex);
        String action = text.substring(endIndex + 1).trim();
        type = type.replace("[", "").replace("]", "");
        EnumCommandType commandType = EnumCommandType.getFromString(type);
        return convertToCommandType(commandType, action);
      }
    return null;
  }

  @Nullable
  private static CommandType convertToCommandType(EnumCommandType type, String text)
  {
    CommandType result;
    if((result = convertToTextCommandType(type, text)) == null)
      {
        if((result = convertToRangeTextCommandType(type, text)) == null)
          {
            if((result = convertToSoundCommandType(type, text)) == null)
              {
                if((result = convertToTitleCommandType(type, text)) == null)
                  {
                    result = convertToEffectCommandType(type, text);
                  }
              }
          }
      }
    return result;
  }

  @Nullable
  private static CommandType convertToTextCommandType(EnumCommandType type, String text)
  {
    if(type == EnumCommandType.CONSOLE || type == EnumCommandType.PLAYER || type == EnumCommandType.MESSAGE || type == EnumCommandType.BROADCAST || type == EnumCommandType.ACTIONBAR ||
       type == EnumCommandType.BROADCAST_ACTIONBAR)
      {
        return new TextCommandType(type, text);
      }
    return null;
  }

  @Nullable
  private static CommandType convertToRangeTextCommandType(EnumCommandType type, String text)
  {
    if(type == EnumCommandType.RANGE_MESSAGE)
      {
        int firstIndex = text.indexOf(" ");
        if(firstIndex >= 0)
          {
            String rangeParam = text.substring(0, firstIndex).trim();
            text = text.substring(firstIndex + 1).trim();
            double range = Double.parseDouble(rangeParam);
            range = range <= 0 ? 1 : range;
            return new RangeTextCommandType(type, range, text);
          }
      }
    return null;
  }

  @Nullable
  private static CommandType convertToSoundCommandType(EnumCommandType type, String text)
  {
    if(type == EnumCommandType.SOUND || type == EnumCommandType.WORLD_SOUND || type == EnumCommandType.EVENT_SOUND || type == EnumCommandType.WORLD_EVENT_SOUND)
      {
        Sound        sound  = DEFAULT_SOUND;
        float        volume = 1;
        float        pitch  = 1;
        List<String> params = StringUtils.splitNoEmpty(text, " ");
        if(params != null)
          {
            for(int i = 0; i < params.size(); i++)
              {
                if(i == 0)
                  {
                    sound = Sound.valueOf(params.get(i));
                  }
                else if(i == 1)
                  {
                    volume = Float.parseFloat(params.get(i));
                  }
                else if(i == 2)
                  {
                    pitch = Float.parseFloat(params.get(i));
                  }
                else
                  {
                    break;
                  }
              }
          }
        return new SoundCommandType(type, sound, volume, pitch);
      }
    return null;
  }

  @Nullable
  private static CommandType convertToTitleCommandType(EnumCommandType type, String text)
  {
    if(type == EnumCommandType.TITLE || type == EnumCommandType.BROADCAST_TITLE)
      {
        String       title        = "";
        String       subtitle     = "";
        int          fadeInTicks  = 10;
        int          stayTicks    = 30;
        int          fadeOutTicks = 10;
        List<String> params       = StringUtils.splitNoEmpty(text, " ", 4);
        if(params != null)
          {
            for(int i = 0; i < params.size(); i++)
              {
                if(i == 0)
                  {
                    fadeInTicks = Integer.parseInt(params.get(i));
                  }
                else if(i == 1)
                  {
                    stayTicks = Integer.parseInt(params.get(i));
                  }
                else if(i == 2)
                  {
                    fadeOutTicks = Integer.parseInt(params.get(i));
                  }
                else
                  {
                    break;
                  }
              }
            if(params.size() > 3)
              {
                String other = params.get(3);

                //title
                int posStart = other.indexOf("T:") + 2;
                int posEnd   = other.indexOf("S:");
                posEnd = posEnd <= posStart ? other.length() : posEnd;
                title  = other.substring(posStart, posEnd).trim();

                //subtitle
                posStart = other.indexOf("S:") + 2;
                posEnd   = other.indexOf("T:");
                posEnd   = posEnd <= posStart ? other.length() : posEnd;
                subtitle = other.substring(posStart, posEnd).trim();
              }
          }
        return new TitleCommandType(type, title, subtitle, fadeInTicks, stayTicks, fadeOutTicks);
      }
    return null;
  }

  @Nullable
  private static CommandType convertToEffectCommandType(EnumCommandType type, String text)
  {
    if(type == EnumCommandType.EFFECT)
      {
        EnumEffectActionType actionType = EnumEffectActionType.CLEAR;
        PotionEffectType     effectType = null;
        int                  duration   = 0;
        int                  level      = 0;
        List<String>         params     = StringUtils.splitNoEmpty(text, " ", 4);
        if(params != null)
          {
            int size = params.size();
            if(size > 0)
              {
                actionType = EnumEffectActionType.getFromString(params.get(0));
                if(actionType.getMaxParams() > 0 && size > 1)
                  {
                    effectType = PotionEffectType.getByName(params.get(1));
                    if(size > 2)
                      {
                        duration = Integer.parseInt(params.get(2)) * 20;
                        if(size > 3)
                          {
                            level = Integer.parseInt(params.get(3)) - 1;
                          }
                      }
                  }
              }
          }
        return new EffectCommandType(actionType, effectType, duration, level);
      }
    return null;
  }
}
