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


  private PneumaticsSubsystem m_PneumaticsSubsystem = new PneumaticsSubsystem();
  private PIDController m_XPidController = new PIDController(.6, 0, 0);
  private PIDController m_YPidController = new PIDController(.6, 0, 0);
//    private PIDController m_AreaPidController = new PIDController(.6, 0, 0);
  private Swerve m_Swerve;
  //private double tolerance = 0.1;
  //private boolean onTarget = false;
  public boolean shoot = false;
  //private double targetArea = 0.5;//calibrate this
 




  public ManualShooter(Swerve m_Swerve) {
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


  shooterCANSparkMaxThree.setInverted(false);
  shooterCANSparkMaxThree.setSmartCurrentLimit(40);
  shooterCANSparkMaxThree.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);


  shooterCANSparkMaxFour.setInverted(true);
  shooterCANSparkMaxFour.setSmartCurrentLimit(40);
  shooterCANSparkMaxFour.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);


   //following
  shooterCANSparkMaxTwo.follow(shooterCANSparkMax);
  shooterCANSparkMaxFour.follow(shooterCANSparkMaxThree);


  //tolerance
  m_XPidController.setTolerance(Constants.Shooter.tolerance);
  m_YPidController.setTolerance(Constants.Shooter.tolerance);
//    m_AreaPidController.setTolerance(Constants.Shooter.areaTolerance);
  }




  public void shooterAngleUp(double speed){
    shooterCANSparkMaxThree.setInverted(false);
    shooterCANSparkMaxThree.set(speed);
}


public void off(){
    shooterCANSparkMaxThree.set(0);
}

//TODO: Change 
public void shooterAngleDown(double speed){
    shooterCANSparkMaxThree.setInverted(true);
    shooterCANSparkMaxThree.set(speed);
}
public void cherryBomb() {
        //TODO: Add the launch speed
        shooterCANSparkMax.set(Constants.Shooter.launchSpeedLimit);
        while(shooterCANSparkMax.getEncoder().getVelocity()<0.6){
        }
        m_PneumaticsSubsystem.toggle();
}

}
