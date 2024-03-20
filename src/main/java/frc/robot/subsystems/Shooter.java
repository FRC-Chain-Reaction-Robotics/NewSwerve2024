package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;


import frc.robot.Constants;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.AbsoluteEncoder;


/* Github testing */
public class Shooter extends SubsystemBase{  



 CANSparkMax shooterCANSparkMax;
 CANSparkMax shooterCANSparkMaxThree;

 TalonFX shooterTalonFXTwo;
 TalonFX shooterTalonFXFour;
  AbsoluteEncoder throughBoreEncoder;
  //TODO: update the launch speed


  private Apriltags m_Apriltags = new Apriltags();
  private PneumaticsSubsystem m_PneumaticsSubsystem = new PneumaticsSubsystem();
  private PIDController m_XPidController = new PIDController(.6, 0, 0);
  private PIDController m_YPidController = new PIDController(.6, 0, 0);
  private PIDController m_AreaPidController = new PIDController(.6, 0, 0);
  private Swerve m_Swerve;
  private double[] yTarget = {0.2,0.5};//put in target shooter angles
  //private double tolerance = 0.3;
  public boolean shoot = false;
  private boolean withinYToleranceGround = (Math.abs(throughBoreEncoder.getPosition()-yTarget[0])<Constants.Shooter.yTolerance);
  private boolean withinYToleranceShooter = (Math.abs(throughBoreEncoder.getPosition()-yTarget[1])<Constants.Shooter.yTolerance);

 




  public Shooter(Swerve m_Swerve) {
  this.m_Swerve = m_Swerve;
  this.m_PneumaticsSubsystem = m_PneumaticsSubsystem;

     //TODO: Update all of the Constant
     shooterCANSparkMax = new CANSparkMax(15, MotorType.kBrushless);
     shooterTalonFXTwo = new TalonFX(12);
     shooterCANSparkMaxThree = new CANSparkMax(13, MotorType.kBrushless);
     shooterTalonFXFour = new TalonFX(14);

     shooterCANSparkMax.setInverted(false);
     shooterCANSparkMax.setSmartCurrentLimit(40);
     shooterCANSparkMax.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);

     shooterCANSparkMaxThree.setInverted(true);
     shooterCANSparkMaxThree.setSmartCurrentLimit(40);
     shooterCANSparkMaxThree.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);


     shooterTalonFXTwo.setInverted(false);
     //TODO: This is replacing setSmartCurrentLimit so watch out for it
     shooterTalonFXTwo.setVoltage(40);
     shooterTalonFXTwo.setNeutralMode(NeutralModeValue.Brake);
     //shooterTalonFXTwo.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);

     shooterCANSparkMax.follow(shooterCANSparkMaxThree);

     shooterTalonFXFour.setInverted(true);
     shooterTalonFXFour.setVoltage(40);
     shooterTalonFXFour.setNeutralMode(NeutralModeValue.Brake);
     //shooterTalonFXFour.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);

   //tolerance
   m_XPidController.setTolerance(Constants.Shooter.xTolerance);
   m_YPidController.setTolerance(Constants.Shooter.yTolerance);
   m_AreaPidController.setTolerance(Constants.Shooter.areaTolerance);
   //m_shooterPidController.setTolerance(.2);
   }
   

   public void shooterAngleUp(double speed){
     //makes the motor move clockwise
     shooterCANSparkMaxThree.setInverted(false);
     //TODO: check inverted
     shooterCANSparkMax.setInverted(true);
     shooterCANSparkMaxThree.set(speed);
     shooterCANSparkMax.set(speed);
   }


   public void angleOff(){
     shooterCANSparkMaxThree.set(0);
     shooterCANSparkMax.set(0);
  }

     public double getVelocity() {
     //Find a replacement for getEncoder();
          return shooterTalonFXTwo.getVelocity().getValueAsDouble();
     }

  public void groundIntake(){
     shooterTalonFXFour.setInverted(true);
     shooterTalonFXTwo.setInverted(false);
     shooterTalonFXTwo.set(Constants.Shooter.intakeSpeedLimit);
     shooterTalonFXFour.set(Constants.Shooter.intakeSpeedLimit);
     if(!withinYToleranceGround){
          shooterCANSparkMaxThree.set(m_YPidController.calculate(throughBoreEncoder.getPosition(), yTarget[0]));
     }
  }

  public void returnToUp(){
     if(!withinYToleranceShooter){
          shooterCANSparkMaxThree.set(m_YPidController.calculate(throughBoreEncoder.getPosition(), yTarget[1]));
     }
  }

  public void cherryBomb() {
    if(m_Apriltags.getV()!=0){
         //TODO: Add the launch speed
         boolean withinXTolerance = (m_Apriltags.getX()<Constants.Shooter.xTolerance&&m_Apriltags.getX()>-Constants.Shooter.xTolerance);
         boolean withinAreaTolerance = (m_Apriltags.getArea()-Constants.Shooter.targetArea<Constants.Shooter.areaTolerance&&m_Apriltags.getArea()-Constants.Shooter.targetArea>Constants.Shooter.areaTolerance);
         shooterTalonFXTwo.set(Constants.Shooter.launchSpeedLimit);
         shooterTalonFXFour.set(Constants.Shooter.launchSpeedLimit);
         while(shooterTalonFXTwo.getVelocity().getValueAsDouble()<0.6 && shooterTalonFXFour.getVelocity().getValueAsDouble() < 0.6||!Constants.Shooter.onTarget){
              Constants.Shooter.onTarget = withinXTolerance&&withinYToleranceShooter;//&&withinAreaTolerance
              if(!withinXTolerance){
                   m_Swerve.drive(0, 0, m_XPidController.calculate(m_Apriltags.getX(), 0/*TODO:Might need to change */ ), false);
              }
              if(!withinYToleranceShooter){
                   shooterTalonFXTwo.set(m_YPidController.calculate(throughBoreEncoder.getPosition(), yTarget[1]));
                   shooterTalonFXFour.set(m_YPidController.calculate(throughBoreEncoder.getPosition(), yTarget[1]));
              }
              if(!withinAreaTolerance){
                    m_Swerve.drive(m_AreaPidController.calculate(m_Apriltags.getArea(), /* TODO: Change the setpoint */ Constants.Shooter.targetArea), 0, 0, false);
               }
         }
         m_PneumaticsSubsystem.toggle();
    }
  } 
 
     /*public boolean shootBAM() {
     double speedDifference = m_shooterPidController.calculate(shooterCANSparkMax.getEncoder().getVelocity(), Constants.Shooter.launchSpeedLimit);
     shooterCANSparkMax.set(Constants.Shooter.launchSpeedLimit - speedDifference);
     return m_shooterPidController.atSetpoint();
   } */
 }
