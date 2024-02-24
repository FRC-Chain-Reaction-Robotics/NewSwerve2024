package frc.robot.subsystems;



import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/* Github testing */
public class Shooter extends SubsystemBase{


   CANSparkMax shooterCANSparkMax;
   CANSparkMax shooterCANSparkMaxTwo;
   
   CANSparkMax shooterCANSparkMaxThree;
   CANSparkMax shooterCANSparkMaxFour;
   //TODO: update the launch speed

   private Apriltags m_Apriltags = new Apriltags();
   private PneumaticsSubsystem m_PneumaticsSubsystem = new PneumaticsSubsystem();
   private PIDController m_XPidController = new PIDController(.6, 0, 0);
   private PIDController m_YPidController = new PIDController(.6, 0, 0);
   private PIDController m_AreaPidController = new PIDController(.6, 0, 0);
   private Swerve m_Swerve;
   //private double tolerance = 0.1;
   //private boolean onTarget = false;
   public boolean shoot = false;
   //private double targetArea = 0.5;//calibrate this
   


   public Shooter(Swerve m_Swerve) {
  
   this.m_Swerve = m_Swerve;


   //TODO: Update all of the Constant
   shooterCANSparkMax = new CANSparkMax(15, MotorType.kBrushless);
   shooterCANSparkMaxTwo = new CANSparkMax(12, MotorType.kBrushless);
   shooterCANSparkMaxThree = new CANSparkMax(13, MotorType.kBrushless);
   shooterCANSparkMaxFour = new CANSparkMax(14, MotorType.kBrushless);

   shooterCANSparkMax.setInverted(true);
   shooterCANSparkMax.setSmartCurrentLimit(40);
   shooterCANSparkMax.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);

   shooterCANSparkMaxTwo.setInverted(false);
   shooterCANSparkMaxTwo.setSmartCurrentLimit(40);
   shooterCANSparkMaxTwo.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);

   shooterCANSparkMaxThree.setInverted(true);
   shooterCANSparkMaxThree.setSmartCurrentLimit(40);
   shooterCANSparkMaxThree.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);

   shooterCANSparkMaxThree.setInverted(false);
   shooterCANSparkMaxThree.setSmartCurrentLimit(40);
   shooterCANSparkMaxThree.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);

    //following
   shooterCANSparkMaxTwo.follow(shooterCANSparkMax);
   shooterCANSparkMaxFour.follow(shooterCANSparkMaxThree);

   //tolerance
   m_XPidController.setTolerance(Constants.Shooter.tolerance);
   m_YPidController.setTolerance(Constants.Shooter.tolerance);
   m_AreaPidController.setTolerance(Constants.Shooter.areaTolerance);
   }


   public void cherryBomb() {
     if(m_Apriltags.getV()!=0){
          //TODO: Add the launch speed
          boolean withinXTolerance = (m_Apriltags.getX()<Constants.Shooter.tolerance&&m_Apriltags.getX()>-Constants.Shooter.tolerance);
          boolean withinYTolerance = (m_Apriltags.getY()<Constants.Shooter.tolerance&&m_Apriltags.getY()>-Constants.Shooter.tolerance);
          boolean withinAreaTolerance = (m_Apriltags.getArea()-Constants.Shooter.targetArea<Constants.Shooter.areaTolerance&&m_Apriltags.getArea()-Constants.Shooter.targetArea>-Constants.Shooter.areaTolerance);
          shooterCANSparkMax.set(Constants.Shooter.launchSpeedLimit);
          if(shooterCANSparkMax.getEncoder().getVelocity()<0.6||!Constants.Shooter.onTarget){
               Constants.Shooter.onTarget = withinXTolerance||withinYTolerance||withinAreaTolerance;
          }
          else {
               m_PneumaticsSubsystem.toggle();
          }
          if(!withinXTolerance){
               m_Swerve.drive(0, 0, m_XPidController.calculate(m_Apriltags.getX(), 0/*TODO:Might need to change */), false);
          }
          if(!withinYTolerance){
               shooterCANSparkMaxThree.set(m_YPidController.calculate(m_Apriltags.getY(), 0));
          }
          if(!withinAreaTolerance){
               m_Swerve.drive(m_AreaPidController.calculate(m_Apriltags.getArea(), /* TODO: Change the setpoint */Constants.Shooter.targetArea), 0, 0, false);
          }
     }
   }
   
  
}





