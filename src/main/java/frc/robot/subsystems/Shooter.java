package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import frc.robot.subsystems.Apriltags;
/* Github testing */
public class Shooter extends SubsystemBase{

    CANSparkMax shooterCanSparkMax;
    CANSparkMax shooterCanSparkMaxTwo;
    //TODO: update the launch speed 
    private static final int launchSpeedLimit = 60;
    private static final int feedSpeedLimit = 60;
    private Apriltags m_Apriltags = new Apriltags();

    

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
    }

    public void changeAngle() {
        
    }   
    
}
