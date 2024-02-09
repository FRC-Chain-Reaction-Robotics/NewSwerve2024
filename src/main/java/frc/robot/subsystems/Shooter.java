package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


import frc.robot.subsystems.Apriltags;
import frc.robot.subsystems.PneumaticsSubsystem;
/* Github testing */
public class Shooter extends SubsystemBase{


   CANSparkMax shooterCanSparkMax;
   CANSparkMax shooterCanSparkMaxTwo;
   //TODO: update the launch speed
   private static final int launchSpeedLimit = 60;
   private static final int feedSpeedLimit = 60;
   private Apriltags m_Apriltags = new Apriltags();
   private PneumaticsSubsystem m_PneumaticsSubsystem = new PneumaticsSubsystem();
   private double tolerance = 0.1;
   private double targetArea;
   private boolean onTarget = false;


   public Shooter() {
  
   shooterCanSparkMax.setInverted(true);
   shooterCanSparkMax.setSmartCurrentLimit(launchSpeedLimit);
   shooterCanSparkMax.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);


   shooterCanSparkMaxTwo.setInverted(true);
   shooterCanSparkMaxTwo.setSmartCurrentLimit(launchSpeedLimit);
   shooterCanSparkMaxTwo.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);
   }


   public void cherryBomb() {
   //TODO: Add the launch speed
   shooterCanSparkMax.set(launchSpeedLimit);
   while(shooterCanSparkMax.getEncoder().getVelocity()<0.6||!onTarget){
       onTarget = (m_Apriltags.getX()>tolerance||m_Apriltags.getX()<-tolerance)||(m_Apriltags.getArea()-targetArea>tolerance||m_Apriltags.getArea()-targetArea<-tolerance);
      
   }
   }


   public void changeAngle() {
      
   }  
  
}





