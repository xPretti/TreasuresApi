package dev.pretti.treasuresapi.datatypes.commands;

import dev.pretti.treasuresapi.datatypes.commands.base.CommandType;
import dev.pretti.treasuresapi.enums.EnumCommandType;
import org.bukkit.Sound;

public class SoundCommandType extends CommandType
{
  private Sound sound;
  private float volume;
  private float pitch;

  /**
   * Construtor da classe
   */
  public SoundCommandType(EnumCommandType type, Sound sound, float volume, float pitch)
  {
    super(type);
    this.sound  = sound;
    this.volume = volume;
    this.pitch  = pitch;
  }


  /**
   * Métodos de retornos
   */
  public Sound getSound()
  {
    return sound;
  }

  public void setSound(Sound sound)
  {
    this.sound = sound;
  }

  public float getVolume()
  {
    return volume;
  }

  public void setVolume(float volume)
  {
    this.volume = volume;
  }

  public float getPitch()
  {
    return pitch;
  }

  public void setPitch(float pitch)
  {
    this.pitch = pitch;
  }
}
