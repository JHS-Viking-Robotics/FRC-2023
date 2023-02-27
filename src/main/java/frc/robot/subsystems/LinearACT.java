package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.linearCommand;

public class LinearACT extends SubsystemBase {
  Servo Claw = new Servo(0);

  public LinearACT() {

  }

  public void setPos(double position) {
    Claw.set(position);
  }

  @Override
  public void periodic() {
    setDefaultCommand(new linearCommand());
  }

}