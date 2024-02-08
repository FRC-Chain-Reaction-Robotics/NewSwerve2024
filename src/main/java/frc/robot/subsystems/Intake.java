package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/* Github testing */
public class Intake extends SubsystemBase{

    CANSparkMax intakeCANSparkMax;

    public Intake(){
        intakeCANSparkMax.setInverted(true);
        intakeCANSparkMax.setSmartCurrentLimit(60);
        intakeCANSparkMax.setIdleMode(IdleMode.kBrake);
    }
    public void on(double speed){
        intakeCANSparkMax.setInverted(false);
        intakeCANSparkMax.set(speed);
    }
    public void off(){
        intakeCANSparkMax.set(0);
    }
    public void reverse(double speed){
        intakeCANSparkMax.setInverted(true);
        intakeCANSparkMax.set(speed);
    }
    
    
    
}
