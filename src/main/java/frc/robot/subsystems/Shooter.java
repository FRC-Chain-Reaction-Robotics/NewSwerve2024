package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;


import frc.robot.Constants;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.AbsoluteEncoder;


/* Github testing */
public class Shooter extends SubsystemBase{



  CANSparkMax shooterCANSparkMax;
  CANSparkMax shooterCANSparkMaxTwo;
 
  CANSparkMax shooterCANSparkMaxThree;
  CANSparkMax shooterCANSparkMaxFour;
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
 




  public Shooter(Swerve m_Swerve) {
   this.m_Swerve = m_Swerve;


   //TODO: Update all of the Constant
   shooterCANSparkMax = new CANSparkMax(18, MotorType.kBrushless);
   shooterCANSparkMaxTwo = new CANSparkMax(19, MotorType.kBrushless);
   shooterCANSparkMaxThree = new CANSparkMax(21, MotorType.kBrushless);
   shooterCANSparkMaxFour = new CANSparkMax(22, MotorType.kBrushless);
   throughBoreEncoder = shooterCANSparkMaxThree.getAbsoluteEncoder(SparkAbsoluteEncoder.Type.kDutyCycle);

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
   m_XPidController.setTolerance(Constants.Shooter.xTolerance);
   m_YPidController.setTolerance(Constants.Shooter.yTolerance);
   m_AreaPidController.setTolerance(Constants.Shooter.areaTolerance);
   //m_shooterPidController.setTolerance(.2);
   }
   


  public void off()
  {
     shooterCANSparkMax.set(0);
     shooterCANSparkMaxThree.set(0);
  }

  public void groundIntake(){
     boolean withinYTolerance = (throughBoreEncoder.getPosition()<Constants.Shooter.yTolerance&&throughBoreEncoder.getPosition()>Constants.Shooter.yTolerance);
     if(!withinYTolerance){
          shooterCANSparkMaxThree.set(m_YPidController.calculate(throughBoreEncoder.getPosition(), yTarget[0]));
     }
  }

  public void cherryBomb() {
    if(m_Apriltags.getV()!=0){
         //TODO: Add the launch speed
         boolean withinXTolerance = (m_Apriltags.getX()<Constants.Shooter.xTolerance&&m_Apriltags.getX()>Constants.Shooter.xTolerance);
         boolean withinYTolerance = (throughBoreEncoder.getPosition()<Constants.Shooter.yTolerance&&throughBoreEncoder.getPosition()>Constants.Shooter.yTolerance);
         boolean withinAreaTolerance = (m_Apriltags.getArea()-Constants.Shooter.targetArea<Constants.Shooter.areaTolerance&&m_Apriltags.getArea()-Constants.Shooter.targetArea>Constants.Shooter.areaTolerance);
         shooterCANSparkMax.set(Constants.Shooter.launchSpeedLimit);
         while(shooterCANSparkMax.getEncoder().getVelocity()<0.6||!Constants.Shooter.onTarget){
              Constants.Shooter.onTarget = withinXTolerance&&withinYTolerance;//&&withinAreaTolerance
              if(!withinXTolerance){
                   m_Swerve.drive(0, 0, m_XPidController.calculate(m_Apriltags.getX(), 0/*TODO:Might need to change*/ ), false);
              }
              if(!withinYTolerance){
                   shooterCANSparkMaxThree.set(m_YPidController.calculate(throughBoreEncoder.getPosition(), yTarget[1]));
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
