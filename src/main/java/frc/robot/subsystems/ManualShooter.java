package frc.robot.subsystems;




import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;




import frc.robot.Constants;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;




/* Github testing */
public class ManualShooter extends SubsystemBase{








  CANSparkMax shooterCANSparkMax;
  CANSparkMax shooterCANSparkMaxTwo;
 
  CANSparkMax shooterCANSparkMaxThree;
  CANSparkMax shooterCANSparkMaxFour;
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
  shooterCANSparkMaxTwo = new CANSparkMax(12, MotorType.kBrushless);
  shooterCANSparkMaxThree = new CANSparkMax(13, MotorType.kBrushless);
  shooterCANSparkMaxFour = new CANSparkMax(14, MotorType.kBrushless);




  shooterCANSparkMax.setInverted(false);
  shooterCANSparkMax.setSmartCurrentLimit(40);
  shooterCANSparkMax.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);




  shooterCANSparkMaxTwo.setInverted(false);
  shooterCANSparkMaxTwo.setSmartCurrentLimit(40);
  shooterCANSparkMaxTwo.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);



  shooterCANSparkMaxThree.setInverted(true);
  shooterCANSparkMaxThree.setSmartCurrentLimit(40);
  shooterCANSparkMaxThree.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);



  shooterCANSparkMaxFour.setInverted(true);
  shooterCANSparkMaxFour.setSmartCurrentLimit(40);
  shooterCANSparkMaxFour.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);




   //following




//    m_AreaPidController.setTolerance(Constants.Shooter.areaTolerance);
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
  shooterCANSparkMaxFour.set(0);
  shooterCANSparkMaxTwo.set(0);
}


public void ringIntake(double speed) {


  shooterCANSparkMaxFour.setInverted(true);
  shooterCANSparkMaxTwo.setInverted(false);
  shooterCANSparkMaxTwo.set(speed);
  shooterCANSparkMaxFour.set(speed);
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
        shooterCANSparkMaxTwo.setInverted(true);
        shooterCANSparkMaxFour.setInverted(true);
        shooterCANSparkMaxTwo.set(Constants.Shooter.launchSpeed);
        shooterCANSparkMaxFour.set(Constants.Shooter.launchSpeed);
        if(shooterCANSparkMaxTwo.getEncoder().getVelocity()>=Constants.Shooter.launchSpeedLimit){
          // m_PneumaticsSubsystem.toggle();
        }
       
}


}
