package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
/* Github testing */
public class Shooter extends SubsystemBase{

    CANSparkMax shooterCanSparkMax;
    //TODO: update the launch speed 
    private static final int launchSpeedLimit = 60;

    public Shooter() {
    shooterCanSparkMax.setInverted(true);
    shooterCanSparkMax.setSmartCurrentLimit(launchSpeedLimit);
    shooterCanSparkMax.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);
    }

    public void cherryBomb() {
    //TODO: Add the launch speed
    shooterCanSparkMax.set(launchSpeedLimit);
    }
    
}
