// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.MecanumDrive;
import frc.robot.commands.autonomous.GetOffLine;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final XboxController m_driveController = new XboxController(0);
  private final XboxController m_armController = new XboxController(1);

  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Winch m_Winch = new Winch();
  
  // A chooser for autonomous commands
  SendableChooser<Command> m_autonSelector = new SendableChooser<>();

  private final MecanumDrive m_mecanumDrive
      = new MecanumDrive(
          m_drivetrain,
          () -> m_driveController.getLeftY(),
          () -> m_driveController.getLeftX(),
          () -> m_driveController.getRightX(),
          false);
  private final MecanumDrive m_mecanumDriveFOD
      = new MecanumDrive(
          m_drivetrain,
          () -> m_driveController.getLeftY(),
          () -> m_driveController.getLeftX(),
          () -> m_driveController.getRightX(),
          true);
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Configure the autonomous mode selector
    m_autonSelector.setDefaultOption(
        "Get Off The Line",
        new GetOffLine(m_drivetrain, 0.2, true));

    // Set arcade drive as default, and also set lift.stop as a safety
    m_drivetrain.setDefaultCommand(m_mecanumDrive);
    m_Winch.setDefaultCommand(new RunCommand(m_Winch::stop, m_Winch));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // Configure the button for turning on FOD
    SmartDashboard.putData("Drive with FOD", m_mecanumDriveFOD);
    /**SmartDashboard.putData(
        "Initialize Intake",
        new InstantCommand(m_intake::toggleDrop, m_intake));
    */
    new JoystickButton(m_armController, Button.kY.value)
        .whenHeld(new RunCommand(m_Winch::goUp, m_Winch));
    new JoystickButton(m_armController, Button.kA.value)
        .whenHeld(new RunCommand(m_Winch::goDown, m_Winch));
    new JoystickButton(m_driveController, Button.kLeftBumper.value)
        .whenPressed(new InstantCommand(m_drivetrain::setTurboSpeed, m_drivetrain))
        .whenReleased(new InstantCommand(m_drivetrain::setMaxSpeed, m_drivetrain));
    new JoystickButton(m_driveController, Button.kRightBumper.value)
        .whenPressed(new InstantCommand(m_drivetrain::setSlowSpeed, m_drivetrain))
        .whenReleased(new InstantCommand(m_drivetrain::setMaxSpeed, m_drivetrain));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_autonSelector.getSelected();
  }
}