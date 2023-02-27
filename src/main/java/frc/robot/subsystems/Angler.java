package frc.robot.subsystems;

import static frc.robot.Constants.Subsystem.Angler.*;
import static com.revrobotics.CANSparkMax.IdleMode.*;
import static com.revrobotics.CANSparkMaxLowLevel.MotorType.*;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Angler extends SubsystemBase {

  private final CANSparkMax Angler; // 

  /** Creates a new Angler. */
  public  Angler() {
    Angler = new CANSparkMax(ANGLER_ID, kBrushless);
    Angler.restoreFactoryDefaults();
    Angler.setInverted(ANGLER_INVERTED);
    // Lift mechanism is spring loaded, and will try to lift itself a few inches
    // throughout the match. Using brake mode to counter this
    Angler.setIdleMode(kBrake);
  }
  
  /** Raise the Arm */
  public void goUp1() {
    Angler.set(0.15);
  }

  /** Lower the winch */
  public void goDown1() {
    Angler.set(-0.15);
  }

  /** Stop the Arm. Note that this will not work after the match when the power
   * is turned off. */
  public void stop() {
    Angler.set(0);
  }
}