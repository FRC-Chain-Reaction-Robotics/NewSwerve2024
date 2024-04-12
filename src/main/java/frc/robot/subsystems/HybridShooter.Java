package frc.robot.subsystems;
import frc.robot.Constants;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkAbsoluteEncoder.Type;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.AbsoluteEncoder;


/* Github testing */
public class HybridShooter extends SubsystemBase{  
    



 CANSparkMax shooterCANSparkMax;
 CANSparkMax shooterCANSparkMaxThree;

 TalonFX shooterTalonFXTwo;
 TalonFX shooterTalonFXFour;
 AbsoluteEncoder throughBoreEncoder;
  //TODO: update the launch speed


  private PneumaticsSubsystem m_PneumaticsSubsystem;
  private PIDController m_YPidController = new PIDController(.2, 0, 0);
  private double[] yTarget = {0.23,0.04};//put in target shooter angles
  //private double tolerance = 0.3;
  public boolean shoot = false;
  private boolean withinYToleranceGround;
  private boolean withinYToleranceShooter;

 




  public HybridShooter(Swerve m_Swerve, PneumaticsSubsystem m_PneumaticsSubsystem) {
  this.m_PneumaticsSubsystem = m_PneumaticsSubsystem;

     //TODO: Update all of the Constant
     shooterCANSparkMax = new CANSparkMax(15, MotorType.kBrushless);
     shooterTalonFXTwo = new TalonFX(12);
     shooterCANSparkMaxThree = new CANSparkMax(13, MotorType.kBrushless);
     shooterTalonFXFour = new TalonFX(14);
     throughBoreEncoder = shooterCANSparkMaxThree.getAbsoluteEncoder(Type.kDutyCycle);

     withinYToleranceGround = (Math.abs(throughBoreEncoder.getPosition()-yTarget[0])<Constants.Shooter.yTolerance);
     withinYToleranceShooter = (Math.abs(throughBoreEncoder.getPosition()-yTarget[1])<Constants.Shooter.yTolerance);

     shooterCANSparkMax.setInverted(false);
     shooterCANSparkMax.setSmartCurrentLimit(40);
     shooterCANSparkMax.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);

     shooterCANSparkMaxThree.setInverted(true);
     shooterCANSparkMaxThree.setSmartCurrentLimit(40);
     shooterCANSparkMaxThree.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);


     shooterTalonFXTwo.setInverted(false);
     //TODO: This is replacing setSmartCurrentLimit so watch out for it
     shooterTalonFXTwo.setNeutralMode(NeutralModeValue.Brake);
     //shooterTalonFXTwo.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);

     shooterCANSparkMax.follow(shooterCANSparkMaxThree);

     shooterTalonFXFour.setInverted(true);
     shooterTalonFXFour.setNeutralMode(NeutralModeValue.Brake);
     //shooterTalonFXFour.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);

   //tolerance
    m_YPidController.setTolerance(Constants.Shooter.yTolerance);
    
   //m_shooterPidController.setTolerance(.2);
   }





     public double getVelocity() {
     //Find a replacement for getEncoder();
          return shooterTalonFXTwo.getVelocity().getValueAsDouble();
     }

  public void groundIntake(){
   if(withinYToleranceGround){
      shooterTalonFXFour.setInverted(true);
      shooterTalonFXTwo.setInverted(true);
      shooterTalonFXTwo.set(Constants.Shooter.intakeSpeedLimit);
      shooterTalonFXFour.set(Constants.Shooter.intakeSpeedLimit);
   }

     shooterCANSparkMaxThree.setInverted(false);
     shooterCANSparkMax.setInverted(true);

     if(!withinYToleranceGround){
          shooterCANSparkMaxThree.set(m_YPidController.calculate(throughBoreEncoder.getPosition(), yTarget[0]));
     }
  }

  public void returnToUp(){
     if(!withinYToleranceShooter){
          shooterCANSparkMax.setInverted(true);
          shooterCANSparkMaxThree.setInverted(true);
          shooterCANSparkMax.set(m_YPidController.calculate(throughBoreEncoder.getPosition(), yTarget[1]));
          shooterCANSparkMaxThree.set(m_YPidController.calculate(throughBoreEncoder.getPosition(), yTarget[1]));
     }
  }

  public void shootOff(){
     if(shooterTalonFXTwo.getVelocity().getValueAsDouble()!=0||shooterTalonFXFour.getVelocity().getValueAsDouble()!=0){
          shooterTalonFXTwo.set(0);
          shooterTalonFXFour.set(0);
     }
  }

  public void cherryBomb() {
         //TODO: Add the launch speed
         shooterTalonFXTwo.set(Constants.Shooter.launchSpeedLimit);
         shooterTalonFXFour.set(Constants.Shooter.launchSpeedLimit);
          m_PneumaticsSubsystem.toggle();
         
  } 
 
     /*public boolean shootBAM() {
     double speedDifference = m_shooterPidController.calculate(shooterCANSparkMax.getEncoder().getVelocity(), Constants.Shooter.launchSpeedLimit);
     shooterCANSparkMax.set(Constants.Shooter.launchSpeedLimit - speedDifference);
     return m_shooterPidController.atSetpoint();
   } */
 }
