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
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.Constants;
import frc.robot.subsystems.Swerve; 
import frc.robot.subsystems.Winch;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.ManualShooter;
import frc.robot.subsystems.HybridShooter;


public class RobotContainer{

  private final SendableChooser<Command> chooser = new SendableChooser<Command>();
  private final SendableChooser<BooleanSupplier> orientationChooser = new SendableChooser<BooleanSupplier>();

   // Subsystem creation
  public final PneumaticsSubsystem m_PneumaticsSubsystem = new PneumaticsSubsystem();
  private Swerve m_swerve = new Swerve();
  private Winch m_winch = new Winch();
  // private HybridShooter m_hybridShooter = new HybridShooter(m_swerve, m_PneumaticsSubsystem);
  
  private Intake m_intake = new Intake();
  private ManualShooter m_manShooter = new ManualShooter(m_swerve, m_PneumaticsSubsystem);

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
   // m_shooter.setDefaultCommand(new ShootMech(m_manShooter));
    
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

    // Triggers solenoid on press of button b.
    m_driverController.x().onTrue(new InstantCommand(() -> m_PneumaticsSubsystem.toggle()));
   

    //m_operatorController.x().onTrue(new InstantCommand(() -> m_arm.getExtensionEncoder().setPosition(0), m_arm));
 
    //TODO: Fix Arm Angle Offsets in Arm.java first before uncommenting
    // m_operatorController.a().onTrue(new MoveToGoal(m_arm, Row.BOTTOM))
    // .or(m_operatorController.b().onTrue(new MoveToGoal(m_arm, Row.MIDDLE)))
    // .or(m_operatorController.y().onTrue(new MoveToGoal(m_arm, Row.TOP)));
   
   
   /* try{
     m_winch.setDefaultCommand(new RunCommand(() -> m_winch.winchExtend(m_operatorController)));
   }
   catch(IllegalArgumentException e) {
    
   } */
   
    
    m_operatorController.y().whileTrue(new RunCommand(() -> m_intake.on(Constants.Intake.kIntakeSpeed), m_intake)).or(m_operatorController.b().whileTrue(new RunCommand(() -> m_intake.reverse(Constants.Intake.kIntakeSpeed), m_intake)))
    .onFalse(new RunCommand(() -> m_intake.off(), m_intake));

   // m_driverController.leftBumper().whileTrue(new RunCommand(() -> m_winch.leftOn(Constants.Winches.kWinchSpeed), m_winch)).or(m_driverController.leftTrigger().whileTrue(new RunCommand(() -> m_winch.leftReverse(Constants.Winches.kWinchSpeed), m_winch)))
   // .onFalse(new RunCommand(() -> m_winch.off(), m_winch)); 

    //Winches
     //m_driverController.rightBumper().whileTrue(new RunCommand(() -> m_winch.rightOn(Constants.Winches.kWinchSpeed), m_winch)).or(m_driverController.rightTrigger().whileTrue(new RunCommand(() -> m_winch.rightReverse(Constants.Winches.kWinchSpeed), m_winch)))
//.onFalse(new RunCommand(() -> m_winch.off(), m_winch)); 
    

    //Shooter
  //  m_operatorController.rightTrigger().whileTrue(new RunCommand(() -> m_manShooter.cherryBomb())).or(m_operatorController.leftTrigger().whileTrue(new RunCommand(() -> m_manShooter.ringIntake(Constants.Shooter.launchSpeed))))
  //  .onFalse(new RunCommand(() -> m_manShooter.shootOff()));
  //  m_operatorController.leftTrigger().whileTrue(new StartEndCommand(() -> m_manShooter.ringIntake(Constants.Shooter.intakeSpeedLimit), () -> m_manShooter.shootOff(), m_manShooter));
  //  m_operatorController.rightTrigger().whileTrue(new StartEndCommand(() -> m_manShooter.cherryBomb(), () -> m_manShooter.shootOff(), m_manShooter));
   
    m_operatorController.rightBumper().whileTrue(new RunCommand(() -> m_manShooter.shooterAngleUp(Constants.Shooter.shooterAngleSpeed))).or(m_operatorController.leftBumper().whileTrue(new RunCommand(() -> m_manShooter.shooterAngleDown(Constants.Shooter.shooterAngleSpeed))))
     .onFalse(new RunCommand(() -> m_manShooter.angleOff()));

    //hybrid shooter NEEDS FIXING
    // m_operatorController.leftTrigger().whileTrue(new RunCommand(() -> m_hybridShooter.groundIntake())).whileFalse(new RunCommand(() -> m_hybridShooter.returnToUp()));

    // m_operatorController.rightTrigger().whileTrue(new RunCommand(() -> m_hybridShooter.cherryBomb()));


    
    //slow mode for right bumper, medium slow for left bumper

    //Changed from slow to fast on the first one and changed from medium to fast on the second m_swerve
    m_driverController.rightBumper().onTrue(new InstantCommand(() -> m_swerve.slowMode(), m_swerve))
    .or(m_driverController.leftBumper().onTrue(new InstantCommand(() -> m_swerve.fastMode(), m_swerve)))
    .onFalse(new InstantCommand(() -> m_swerve.mediumMode(), m_swerve));
    
  }

  private void addCommandDropdown()
  {
    chooser.setDefaultOption("Drive To Distance", new DriveToDistance(Units.feetToMeters(15), m_swerve));
    chooser.addOption("Turn To Angle", new TurnToAngle(90, m_swerve));
    chooser.addOption("Move and Amp", new MoveandAmp(m_intake, m_swerve)); 
    //chooser.addOption("Shoot and Move", new ShootAndMove(m_hybridShooter, m_swerve));
    //chooser.addOption("Shoot", new ShootMech(m_manShooter));

    SmartDashboard.putData(chooser);
  }

  public Command getAutonomousCommand() {
     return chooser.getSelected();
  }

  public void disabledInit()
  {}

  /*public void setShooterCommand()
  {
    //m_shooter.setDefaultCommand(new ShootMech(m_shooter));
  }*/
  
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
