package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.linearCommand;
import static frc.robot.Constants.Subsystem.LinearACT.*;

public class LinearACT extends SubsystemBase {
  Servo Claw = new Servo(ChannelC);
  Servo Feet = new Servo (ChannelF);
  public LinearACT() {}

  public void setPos(double position) {
    Claw.set(position);
  }
  public void setStance(double stance) {
    Feet.set(stance);
  }

  @Override
  public void periodic() {
    setDefaultCommand(new linearCommand());
  }

}