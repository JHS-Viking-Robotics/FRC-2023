// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class linearCommand extends CommandBase {
  /** Creates a new linearCommand. */
  double position;
  /**double stance;*/

  public linearCommand() {
    addRequirements(RobotContainer.m_linearACT);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    position = 0;
   /** stance = 0;*/
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Position", position);   
    if (Robot.m_robotContainer.getXDButton()){
      position -= 0.02;
      if (position<0){
        position = 0;
      }
    }
    if (Robot.m_robotContainer.getBDButton()){
      position += 0.02;
      if (position>1){
        position = 1;
      }
    }
    /**if (Robot.m_robotContainer.getRATrigger()){
      stance -= 0.005;
      if (stance<0){
        stance = 0;
      }
    }
    if (Robot.m_robotContainer.getLATrigger()){
      stance += 0.005;
      if (stance>1){
        stance = 1;
      }
    }*/

    RobotContainer.m_linearACT.setPos(position);
    /**RobotContainer.m_linearACT.setStance(stance);*/
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