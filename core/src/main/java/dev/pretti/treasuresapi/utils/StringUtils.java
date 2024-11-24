package dev.pretti.treasuresapi.utils;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StringUtils
{
  /**
   * Métodos de split customizados
   */
  // Converte uma string em pequenos elementos mas sem levar em consideração strings vazias.
  @Nullable
  public static List<String> splitNoEmpty(String source, String regex)
  {
    return splitNoEmpty(source, regex, -1);
  }

  @Nullable
  public static List<String> splitNoEmpty(String source, String regex, int limit)
  {
    String[] params = source.split(regex, limit);
    if(params.length > 0)
      {
        List<String> texts = new ArrayList<>();
        for(String param : params)
          {
            param = param.trim();
            if(!param.isEmpty())
              {
                texts.add(param);
              }
          }
        if(!texts.isEmpty())
          {
            return texts;
          }
      }
    return null;
  }
}
