package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase{
    
    private CANSparkMax intakeRoller; 
    private CANSparkMax intakeRotation; 
    private boolean isUp;
    private double upperBound, lowerBound;
    private RelativeEncoder encoder;

    public Intake() {
        isUp = true;
        intakeRoller = new CANSparkMax(Constants.Intake.INTAKE_ROLLERS, MotorType.kBrushless);
        intakeRotation = new CANSparkMax(Constants.Intake.INTAKE_ROTATION, MotorType.kBrushless);

        intakeRoller.setInverted(false);
        intakeRoller.setSmartCurrentLimit(20);
        intakeRoller.setIdleMode(IdleMode.kBrake);

        intakeRotation.setInverted(false);
        intakeRotation.setSmartCurrentLimit(20);
        intakeRotation.setIdleMode(IdleMode.kBrake);
        intakeRotation.setSmartCurrentLimit(Constants.Intake.ROTATION_CURR_LIMIT);
        encoder = intakeRotation.getEncoder();
        encoder.setPositionConversionFactor(1/25.0);
        upperBound = encoder.getPosition();
        lowerBound = Constants.Intake.BOUND_OFFSET - upperBound;
    }

    public void grab(double speed) {
        intakeRoller.set(speed);
        System.out.println("my dad");
    }

    public void spitOut(double speed) {
        intakeRoller.set(-speed);
    }

    public void rotateUp(double speed) {
        intakeRotation.set(-speed);
    }

    public void rotateDown(double speed) {
        intakeRotation.set(speed);
    }

    public boolean checkPos(boolean isUp) {
        if (isUp) {
            return encoder.getPosition() >= lowerBound;
        } else {
            return encoder.getPosition() <= upperBound;
        }
    }

    public void moveToShooter() {
        if (!isUp) togglePosition();
        Timer timer = new Timer();
        new RunCommand(() -> {
            spitOut(Constants.Intake.INTAKE_SPEED);
        }).until(() -> timer.hasElapsed(Constants.Intake.TIME_TO_SWAP));
    }

    public void togglePosition() {
        if (isUp) {
            new RunCommand(() -> {
                rotateUp(Constants.Intake.ROTATION_SPEED);
            }).until(() -> checkPos(isUp));
        } else {
            new RunCommand(() -> {
                rotateDown(Constants.Intake.ROTATION_SPEED);
            }).until(() -> checkPos(isUp));
        }
        isUp = !isUp;
    }

}
