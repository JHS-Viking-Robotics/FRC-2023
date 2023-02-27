// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class linearCommand extends CommandBase {
  /** Creates a new linearCommand. */
  double position;

  public linearCommand() {
    addRequirements(RobotContainer.m_linearACT);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    position = 1;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Robot.m_robotContainer.getRBAButton()){
      position -= 0.001;
      if (position<0){
        position = 0;
      }
    }
    if (Robot.m_robotContainer.getLBAButton()){
      position += 0.001;
      if (position>1){
        position = 1;
      }
    }

    RobotContainer.m_linearACT.setPos(position);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
