// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.package frc.robot.subsystems;

package frc.robot.subsystems;

import static frc.robot.Constants.Subsystem.Winch.*;
import static com.revrobotics.CANSparkMax.IdleMode.*;
import static com.revrobotics.CANSparkMaxLowLevel.MotorType.*;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Winch extends SubsystemBase {

  private final CANSparkMax winch; // 

  /** Creates a new winch. */
  public  Winch() {
    winch = new CANSparkMax(WINCH_ID, kBrushless);
    winch.restoreFactoryDefaults();
    winch.setInverted(WINCH_INVERTED);
    // Lift mechanism is spring loaded, and will try to lift itself a few inches
    // throughout the match. Using brake mode to counter this
    winch.setIdleMode(kBrake);
  }

  /** Raise the winch */
  public void goUp() {
    winch.set(0.5);
  }

  /** Lower the winch */
  public void goDown() {
    winch.set(-0.5);
  }

  /** Stop the Winch. Note that this will not work after the match when the power
   * is turned off. */
  public void stop() {
    winch.set(0);
  }
}