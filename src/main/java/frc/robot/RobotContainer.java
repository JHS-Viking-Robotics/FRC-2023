// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.MecanumDrive;
import frc.robot.commands.autonomous.GetOffLine;
import frc.robot.commands.autonomous.Charge;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final XboxController m_driveController = new XboxController(0);
  Trigger ydButton = new JoystickButton(m_driveController, XboxController.Button.kY.value);
   // Creates a new JoystickButton object for the `Y` button on m_driveController
  Trigger xdButton = new JoystickButton(m_driveController, XboxController.Button.kX.value);
   // Creates a new JoystickButton object for the `X` button on m_driveController
  Trigger adButton = new JoystickButton(m_driveController, XboxController.Button.kA.value);
   // Creates a new JoystickButton object for the `A` button on m_driveController
  Trigger bdButton = new JoystickButton(m_driveController, XboxController.Button.kB.value);
   // Creates a new JoystickButton object for the `B` button on m_driveController
  Trigger RBdButton = new JoystickButton(m_driveController, XboxController.Button.kRightBumper.value);
   // Creates a new JoystickButton object for the `right bummper' on m_driveController
  Trigger LBdButton = new JoystickButton(m_driveController, XboxController.Button.kLeftBumper.value);
   // Creates a new JoystickButton object for the `left bummper' on m_driveController
  private final XboxController m_armController = new XboxController(1);
 Trigger yAButton = new JoystickButton(m_armController, XboxController.Button.kY.value);
  // Creates a new JoystickButton object for the `Y` button on m_armController
 Trigger xAButton = new JoystickButton(m_armController, XboxController.Button.kX.value);
  // Creates a new JoystickButton object for the `X` button on m_armController
 Trigger aAButton = new JoystickButton(m_armController, XboxController.Button.kA.value);
  // Creates a new JoystickButton object for the `A` button on m_armController
 Trigger bAButton = new JoystickButton(m_armController, XboxController.Button.kB.value);
  // Creates a new JoystickButton object for the `B` button on m_armController
 Trigger RBAButton = new JoystickButton(m_armController, XboxController.Button.kRightBumper.value);
  // Creates a new JoystickButton object for the `right bummper' on m_armController
 Trigger LBAButton = new JoystickButton(m_armController, XboxController.Button.kLeftBumper.value);
  // Creates a new JoystickButton object for the `left bummper' on m_armController

  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Winch m_Winch = new Winch();
  private final Angler m_Angler = new Angler();
  
  // A chooser for autonomous commands
  SendableChooser<Command> m_autonSelector = new SendableChooser<>();

  private final MecanumDrive m_mecanumDrive
      = new MecanumDrive(
          m_drivetrain,
          () -> m_driveController.getLeftY(),
          () -> m_driveController.getLeftX(),
          () -> m_driveController.getRightX()
          );

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Configure the autonomous mode selector
    m_autonSelector.setDefaultOption(
        "Get Off The Line",
        new GetOffLine(m_drivetrain, 0.2, true));
    m_autonSelector.addOption(
        "Charge",
        new Charge(m_drivetrain, 0.2, true));

    // Set arcade drive as default, and also set lift.stop as a safety
    m_drivetrain.setDefaultCommand( m_mecanumDrive);
    m_Winch.setDefaultCommand(new RunCommand(m_Winch::stop, m_Winch));
    m_Angler.setDefaultCommand(new RunCommand(m_Angler::stop, m_Angler));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /**SmartDashboard.putData(
        "Initialize Intake",
        new InstantCommand(m_intake::toggleDrop, m_intake));
    */

     Command goUp = new RunCommand(m_Angler::goUp, m_Angler);
     Command goDown = new RunCommand(m_Angler::goDown, m_Angler);
     Command setTurboSpeed = new InstantCommand(m_drivetrain::setTurboSpeed, m_drivetrain);
     Command setSlowSpeed = new InstantCommand(m_drivetrain::setSlowSpeed, m_drivetrain);
     Command setMaxSpeed = new InstantCommand(m_drivetrain::setMaxSpeed, m_drivetrain);
     yAButton.whileTrue(goUp);
     aAButton.whileTrue(goDown);
     LBdButton.onTrue(setTurboSpeed);
     LBdButton.onFalse(setMaxSpeed);
     RBdButton.onTrue(setSlowSpeed);
     RBdButton.onFalse(setMaxSpeed);
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