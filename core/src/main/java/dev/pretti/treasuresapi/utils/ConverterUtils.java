package dev.pretti.treasuresapi.utils;

import dev.pretti.treasuresapi.datatypes.EnchantType;
import dev.pretti.treasuresapi.datatypes.ItemType;
import dev.pretti.treasuresapi.datatypes.commands.*;
import dev.pretti.treasuresapi.dynamics.DoubleDynamic;
import dev.pretti.treasuresapi.dynamics.Dynamic;
import dev.pretti.treasuresapi.dynamics.EnchantDynamic;
import dev.pretti.treasuresapi.dynamics.IntDynamic;
import dev.pretti.treasuresapi.enums.EnumParseType;
import dev.pretti.treasuresapi.rewards.types.ItemReward;
import org.bukkit.Sound;
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
        String[] texts   = fixText.split("-");
        if(texts.length > 0)
          {
            if(ref instanceof IntDynamic)
              {
                ((IntDynamic) ref).setMin(Integer.valueOf(texts[0]));
              }
            else if(ref instanceof DoubleDynamic)
              {
                ((DoubleDynamic) ref).setMin(Double.valueOf(texts[0]));
              }
            if(texts.length > 1)
              {
                if(ref instanceof IntDynamic)
                  {
                    ((IntDynamic) ref).setMax(Integer.valueOf(texts[1]));
                  }
                else if(ref instanceof DoubleDynamic)
                  {
                    ((DoubleDynamic) ref).setMax(Double.valueOf(texts[1]));
                  }
              }
            return true;
          }
      }
    return false;
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
        endIndex = endIndex == -1 ? text.length()-1 : endIndex;
        return text.substring(startIndex, endIndex+1);
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
        endIndex = endIndex == -1 ? text.length()-1 : endIndex;
        String type       = text.substring(startIndex, endIndex);
        String action     = text.substring(endIndex + 1).trim();
        type = type.replace("[", "").replace("]", "");
        EnumParseType commandType = EnumParseType.getFromString(type);
        return convertToCommandType(commandType, action);
      }
    return null;
  }

  @Nullable
  private static CommandType convertToCommandType(EnumParseType type, String text)
  {
    CommandType result = null;
    if((result = convertToTextCommandType(type, text)) == null)
      {
        if((result = convertToRangeTextCommandType(type, text)) == null)
          {
            if((result = convertToSoundCommandType(type, text)) == null)
              {
                result = convertToTitleCommandType(type, text);
              }
          }
      }
    return result;
  }

  @Nullable
  private static CommandType convertToTextCommandType(EnumParseType type, String text)
  {
    if(type == EnumParseType.CONSOLE || type == EnumParseType.PLAYER || type == EnumParseType.MESSAGE || type == EnumParseType.BROADCAST || type == EnumParseType.ACTIONBAR ||
       type == EnumParseType.BROADCAST_ACTIONBAR)
      {
        return new TextCommandType(type, text);
      }
    return null;
  }

  @Nullable
  private static CommandType convertToRangeTextCommandType(EnumParseType type, String text)
  {
    if(type == EnumParseType.RANGE_MESSAGE)
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
  private static CommandType convertToSoundCommandType(EnumParseType type, String text)
  {
    if(type == EnumParseType.SOUND || type == EnumParseType.WORLD_SOUND || type == EnumParseType.EVENT_SOUND || type == EnumParseType.WORLD_EVENT_SOUND)
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
  private static CommandType convertToTitleCommandType(EnumParseType type, String text)
  {
    if(type == EnumParseType.TITLE || type == EnumParseType.BROADCAST_TITLE)
      {
        String       title        = "";
        String       subtitle     = "";
        Integer      fadeInTicks  = 10;
        Integer      stayTicks    = 30;
        Integer      fadeOutTicks = 10;
        List<String> params       = StringUtils.splitNoEmpty(text, " ", 4);
        if(params != null)
          {
            for(int i = 0; i < params.size(); i++)
              {
                if(i == 0)
                  {
                    fadeInTicks = Integer.valueOf(params.get(i));
                  }
                else if(i == 1)
                  {
                    stayTicks = Integer.valueOf(params.get(i));
                  }
                else if(i == 2)
                  {
                    fadeOutTicks = Integer.valueOf(params.get(i));
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
}
