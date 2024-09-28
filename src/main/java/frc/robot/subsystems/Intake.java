package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase{
    
    private CANSparkMax intakeRoller; 
    private CANSparkMax intakeRotation; 

    public Intake() {
        intakeRoller = new CANSparkMax(Constants.Intake.INTAKE_ROLLERS, MotorType.kBrushless);
        intakeRotation = new CANSparkMax(Constants.Intake.INTAKE_ROTATION, MotorType.kBrushless);

        intakeRoller.setInverted(false);
        intakeRoller.setSmartCurrentLimit(20);
        intakeRoller.setIdleMode(IdleMode.kBrake);

        intakeRotation.setInverted(false);
        intakeRotation.setSmartCurrentLimit(20);
        intakeRotation.setIdleMode(IdleMode.kBrake);
    }

    public void grab(double speed) {
        intakeRoller.setInverted(false);
        intakeRoller.set(speed);
    }

    public void spitOut(double speed) {
        intakeRoller.setInverted(true);
        intakeRoller.set(speed);
    }

    public void rotateUp(double speed) {
        intakeRotation.setInverted(true);
        intakeRotation.set(speed);
    }

    public void rotateDown(double speed) {
        intakeRotation.setInverted(false);
        intakeRotation.set(speed);
    }
}
