// Copyright (c) 2023 FRC Team 2881 - The Lady Cans
//
// Open Source Software; you can modify and/or share it under the terms of BSD
// license file in the root directory of this project.
/* Github testing */
package frc.robot;

import java.util.function.BooleanSupplier;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.ShootMech;
import frc.robot.commands.auto.DriveToDistance;
import frc.robot.commands.auto.ShootAndMove;
import frc.robot.commands.auto.MoveandAmp;
import frc.robot.commands.auto.TurnToAngle;
import frc.robot.commands.drive.DriveWithJoysticks;
import frc.robot.lib.Telemetry;
import frc.robot.Constants;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;


public class RobotContainer{

  private final SendableChooser<Command> chooser = new SendableChooser<Command>();
  private final SendableChooser<BooleanSupplier> orientationChooser = new SendableChooser<BooleanSupplier>();

   // Subsystem creation
  private Swerve m_swerve = new Swerve();
  private Intake m_intake = new Intake();
  private Shooter m_shooter = new Shooter();

  private final CommandXboxController m_driverController = new CommandXboxController(Constants.Controllers.kDriverControllerPort);
  public  final CommandXboxController m_operatorController = new CommandXboxController(Constants.Controllers.kOperatorControllerPort);

 
  
  public RobotContainer() {
    orientationChooser.setDefaultOption("Robot Oriented", () -> false);
    orientationChooser.addOption("Field Oriented", () -> true);
    SmartDashboard.putData(orientationChooser);
    
    setupDrive(); 
    configureButtonBindings();
    //Creating a dropdown for autonomous commands to choose from
    addCommandDropdown();
    //m_shooter.setDefaultCommand(new ShootMech(m_manShooter));
    
  }

  

  private void setupDrive() {
    m_swerve.setDefaultCommand(
      new DriveWithJoysticks(
        m_swerve,
        () -> modifyAxis(m_driverController.getLeftY()),
        () -> modifyAxis(-m_driverController.getLeftX()),
        () -> modifyAxis(m_driverController.getRightX()),
        orientationChooser.getSelected()
      )
    );
  }

  private void configureButtonBindings() {
    //DRIVER
    m_driverController.y().onTrue(new InstantCommand(() -> m_swerve.zeroHeading(), m_swerve));
   // m_driverController.a().onTrue(new InstantCommand(() -> m_swerve.resetEncoders(), m_swerve));
    m_driverController.a().onTrue(new InstantCommand(() -> m_swerve.setX(), m_swerve));
   
    //Changed from slow to fast on the first one and changed from medium to fast on the second m_swerve
    m_driverController.rightBumper().onTrue(new InstantCommand(() -> m_swerve.slowMode(), m_swerve))
    .or(m_driverController.leftBumper().onTrue(new InstantCommand(() -> m_swerve.fastMode(), m_swerve)))
    .onFalse(new InstantCommand(() -> m_swerve.mediumMode(), m_swerve));

    //OPERATOR
    //Intake Rotation
    m_operatorController.rightBumper().onTrue(new InstantCommand(() -> m_intake.togglePosition()));

    //Right Trigger grabs the ring, Left Trigger spits it out
    m_operatorController.rightTrigger().whileTrue(new InstantCommand(() -> 
      m_intake.grab(Constants.Intake.INTAKE_SPEED)));
    m_operatorController.leftTrigger().whileTrue(new InstantCommand(() -> 
      m_intake.spitOut(Constants.Intake.INTAKE_SPEED)));
    
    m_operatorController.leftTrigger().and(m_operatorController.rightTrigger())
    .onFalse(new InstantCommand(() -> m_intake.grab(0.0)));

    //shoots the ring
    m_operatorController.a().onTrue(new InstantCommand(() -> m_shooter.shoot(Constants.Shooter.SHOOTING_SPEED)));

    //Intake sends the ring to the Shooter and Shooter grabs it
    m_operatorController.b().onTrue(new InstantCommand(() -> {
      m_intake.moveToShooter();
    }));
  }

  private void addCommandDropdown()
  {
    // chooser.setDefaultOption("Drive To Distance", new DriveToDistance(Units.feetToMeters(15), m_swerve));
    // chooser.addOption("Turn To Angle", new TurnToAngle(90, m_swerve));
    // chooser.addOption("Move and Amp", new MoveandAmp(m_intake, m_swerve)); 
   // chooser.addOption("Shoot and Move", new ShootAndMove(m_hybridShooter, m_swerve));
    //chooser.addOption("Shoot", new ShootMech(m_hybridShooter));

    SmartDashboard.putData(chooser);
  }

  public Command getAutonomousCommand() {
    //  return chooser.getSelected();
    return null;
  }

  public void disabledInit()
  {}

  public void setShooterCommand()
  {
    //m_hybridShooter.setDefaultCommand(new ShootMech(m_hybridShooter));
  }
  
  public void enabledInit()
  { }

  private static double modifyAxis(double value)
  {
    value = deadBand(value, 0.075);

    value = Math.copySign(value * value, value);

    return value;
  }

  private static double deadBand(double value, double deadband)
  {
    if (Math.abs(value) > deadband)
    {
      if (value > 0.0)
        return (value - deadband)/(1.0 - deadband);
      else
        return (value + deadband)/(1.0 - deadband);
    }
    else
      return 0.0;
  }
}
