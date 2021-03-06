/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorSensor extends SubsystemBase {
  /**
   * Creates a new Subsystem.
   */
  private final I2C.Port i2cPort = I2C.Port.kOnboard; // what is an I2C port

  private final ColorSensorV3 m_colorSensor;

  private final ColorMatch m_colorMatcher;

  public final Color Blue = ColorMatch.makeColor(1, 0, 0);
  public final Color Green = ColorMatch.makeColor(1, 1, 0);
  public final Color Red = ColorMatch.makeColor(0, 1, 1);
  public final Color Yellow = ColorMatch.makeColor(0, 0, 1);

  public ColorSensor() {
    m_colorSensor = new ColorSensorV3(i2cPort);
    m_colorMatcher = new ColorMatch();
    m_colorMatcher.addColorMatch(Blue);
    m_colorMatcher.addColorMatch(Green);
    m_colorMatcher.addColorMatch(Red);
    m_colorMatcher.addColorMatch(Yellow);

  }

  //@Override
  public ColorMatchResult getColor() {
    // This method will be called once per scheduler run
    Color detectedColor = m_colorSensor.getColor();
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    return match;
  }
}
