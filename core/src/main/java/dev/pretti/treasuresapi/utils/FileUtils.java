package dev.pretti.treasuresapi.utils;

import dev.pretti.treasuresapi.enums.EnumFileExistType;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils
{
  /**
   * Retorna o tipo do diretório
   */
  public static EnumFileExistType Exist(String dir)
  {
    if(!dir.isEmpty())
      {
        Path    directory = Paths.get(dir);
        boolean exist     = Files.exists(directory);
        if(exist)
          {
            boolean isDir = Files.isDirectory(directory);
            if(isDir)
              {
                return (EnumFileExistType.IS_DIRECTORY);
              } else
              {
                return (EnumFileExistType.IS_FILE);
              }
          }
      }
    return (EnumFileExistType.NOT_EXIST);
  }

  /**
   * Verifica se uma pasta ou arquivoexiste
   */
  public static boolean FolderExist(String folder)
  {
    return (Exist(folder) == EnumFileExistType.IS_DIRECTORY);
  }

  public static boolean FileExist(String folder)
  {
    return (Exist(folder) == EnumFileExistType.IS_FILE);
  }
}
