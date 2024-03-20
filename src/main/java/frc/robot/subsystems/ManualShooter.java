package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;





import frc.robot.Constants;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


/* Github testing */
public class ManualShooter extends SubsystemBase{

 CANSparkMax shooterCANSparkMax;
 CANSparkMax shooterCANSparkMaxThree;

 TalonFX shooterTalonFXTwo;
 TalonFX shooterTalonFXFour;
 //TODO: update the launch speed

 private PneumaticsSubsystem m_PneumaticsSubsystem;
 private Swerve m_Swerve;
 //private double tolerance = 0.1;
 //private boolean onTarget = false;
 public boolean shoot = false;
 //private double targetArea = 0.5;//calibrate this


 public ManualShooter(Swerve m_Swerve, PneumaticsSubsystem m_PneumaticsSubsystem) {
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

 shooterTalonFXFour.setInverted(true);
 shooterTalonFXFour.setVoltage(40);
 shooterTalonFXFour.setNeutralMode(NeutralModeValue.Brake);
 //shooterTalonFXFour.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);

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

public void shootOff() {
 shooterTalonFXFour.set(0);
 shooterTalonFXTwo.set(0);
}

//MODIFY IT TO WHERE IT WILL EXTEND TO INTAKE POSITION, SUCKS THE RING IN, AND THEN GO BACK TO NORMAL POSITION
public void ringIntake(double speed) {

 shooterTalonFXFour.setInverted(true);
 shooterTalonFXTwo.setInverted(false);
 shooterTalonFXTwo.set(speed / 2);
 shooterTalonFXFour.set(speed / 2);
}


//TODO: Change
public void shooterAngleDown(double speed){
   shooterCANSparkMaxThree.setInverted(true);
   //TODO: check inverted
   shooterCANSparkMax.setInverted(false);
   shooterCANSparkMaxThree.set(speed);
   shooterCANSparkMax.set(speed);
}
public void cherryBomb() {
       //TODO: Add the launch speed
       shooterTalonFXTwo.setInverted(true);
       shooterTalonFXFour.setInverted(true);
       shooterTalonFXTwo.set(Constants.Shooter.launchSpeed);
       shooterTalonFXFour.set(Constants.Shooter.launchSpeed);
     
}



}
