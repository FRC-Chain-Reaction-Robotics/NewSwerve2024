package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.SparkPIDController.ArbFFUnits;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


/* Github testing */
public class ManualShooter extends SubsystemBase{

 CANSparkMax shooterCANSparkMax;
 CANSparkMax shooterCANSparkMaxThree;

 TalonFX shooterTalonFXTwo;
 TalonFX shooterTalonFXFour;

 CANSparkBase motor;
 SparkPIDController m_PIDController;
 AbsoluteEncoder throughboreEncoder;
 //TODO: update the launch speed

 private PneumaticsSubsystem m_PneumaticsSubsystem;
 private Swerve m_Swerve;
 public boolean shoot = false;


 public ManualShooter(Swerve m_Swerve, PneumaticsSubsystem m_PneumaticsSubsystem) {
  //this.m_Swerve = m_Swerve;
  this.m_PneumaticsSubsystem = m_PneumaticsSubsystem;
  //this.motor = motor;

  //motor.burnFlash();

 //TODO: Update all of the Constant
 shooterCANSparkMax = new CANSparkMax(15, MotorType.kBrushless);
 shooterTalonFXTwo = new TalonFX(16);
 shooterCANSparkMaxThree = new CANSparkMax(13, MotorType.kBrushless);
 shooterTalonFXFour = new TalonFX(14);

 shooterCANSparkMax.setInverted(false);
 shooterCANSparkMax.setSmartCurrentLimit(40);
 shooterCANSparkMax.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);

 shooterCANSparkMaxThree.setInverted(true);
 shooterCANSparkMaxThree.setSmartCurrentLimit(40);
 shooterCANSparkMaxThree.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);


 shooterTalonFXTwo.setInverted(false);
 shooterTalonFXTwo.setNeutralMode(NeutralModeValue.Brake);

 shooterTalonFXFour.setInverted(true);
 shooterTalonFXFour.setNeutralMode(NeutralModeValue.Brake);

 }

 /*public void setPID(){
  this.m_PIDController = motor.getPIDController();
  //this.throughboreEncoder = motor.getAbsoluteEncoder();
  this.m_PIDController.setP(.3); //calibrate this
  this.m_PIDController.setD(.01);
  this.m_PIDController.setI(0);
  
  //Change the min and max values
  m_PIDController.setOutputRange(-.3, .3);
  m_PIDController.setPositionPIDWrappingEnabled(true);
  m_PIDController.setPositionPIDWrappingMinInput(0);

  double conversion = 2 * Math.PI;
  m_PIDController.setPositionPIDWrappingMaxInput(conversion);
  throughboreEncoder.setPositionConversionFactor(conversion);
  throughboreEncoder.setVelocityConversionFactor(conversion);

  m_PIDController.setFeedbackDevice(throughboreEncoder);
 }
 */

 public void shooterAngleUp(double speed){
   //makes the motor move clockwise
    shooterCANSparkMaxThree.setInverted(true);
   //TODO: check inverted
   shooterCANSparkMax.setInverted(true);
   shooterCANSparkMaxThree.set(speed/2);
   shooterCANSparkMax.set(speed/2);
 }

public void angleOff(){
   shooterCANSparkMaxThree.set(0);
   shooterCANSparkMax.set(0);
}

public void shootOff() {
 shooterTalonFXFour.set(0);
 shooterTalonFXTwo.set(0);
}

//MODIFY IT TO WHERE IT WILL EXTEND TO INTAKE POSITION, SUCKS THE RING IN, AND THEN GO BACK TO NORMAL POSITION
public void ringIntake(double speed) {
 
 shooterTalonFXFour.setInverted(false);
 shooterTalonFXTwo.setInverted(false);
 shooterTalonFXTwo.set(speed / 2);
 shooterTalonFXFour.set(speed / 2);
}


//TODO: Change
public void shooterAngleDown(double speed){
   shooterCANSparkMaxThree.setInverted(false);
   //TODO: check inverted
   shooterCANSparkMax.setInverted(false);
   shooterCANSparkMaxThree.set(speed/4);
   shooterCANSparkMax.set(speed/4);
}
public void cherryBomb() {
   shooterTalonFXTwo.setInverted(true);
   shooterTalonFXFour.setInverted(true);
   shooterTalonFXTwo.set(Constants.Shooter.launchSpeedLimit);
   shooterTalonFXFour.set(Constants.Shooter.launchSpeedLimit);
   //if(shooterTalonFXTwo.getVelocity().getValueAsDouble() >= 0.6 && shooterTalonFXFour.getVelocity().getValueAsDouble() >= 0.6){
      m_PneumaticsSubsystem.toggle();
      m_PneumaticsSubsystem.toggle();
      // SmartDashboard.putNumber("shooterTalonFXTwo", shooterTalonFXTwo.getVelocity().getValueAsDouble());
      // SmartDashboard.putNumber("shooterTalonFXFour", shooterTalonFXFour.getVelocity().getValueAsDouble());

  // }
}



}
