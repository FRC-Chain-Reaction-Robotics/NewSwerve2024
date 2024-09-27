package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase{

    private CANSparkFlex topRoller1;
    private CANSparkFlex topRoller2;
    private CANSparkFlex bottomRoller1;
    private CANSparkFlex bottomRoller2;

    public Shooter () {
        topRoller1 = new CANSparkFlex(Constants.Shooter.TOP_ROLLER1_ID, MotorType.kBrushless);
        topRoller2 = new CANSparkFlex(Constants.Shooter.TOP_ROLLER2_ID, MotorType.kBrushless);
        bottomRoller1 = new CANSparkFlex(Constants.Shooter.BOTTOM_ROLLER1_ID, MotorType.kBrushless);
        bottomRoller2 = new CANSparkFlex(Constants.Shooter.BOTTOM_ROLLER2_ID, MotorType.kBrushless);

        topRoller1.setInverted(false);
        topRoller1.setSmartCurrentLimit(20);
        topRoller1.setIdleMode(IdleMode.kBrake);

        topRoller2.setInverted(true);
        topRoller2.setSmartCurrentLimit(20);
        topRoller2.setIdleMode(IdleMode.kBrake);

        bottomRoller1.setInverted(false);
        bottomRoller1.setSmartCurrentLimit(20);
        bottomRoller1.setIdleMode(IdleMode.kBrake);

        bottomRoller2.setInverted(true);
        bottomRoller2.setSmartCurrentLimit(20);
        bottomRoller2.setIdleMode(IdleMode.kBrake);
        
        bottomRoller1.follow(topRoller1);
        bottomRoller2.follow(topRoller2);
    }

    public void shoot (double speed) {
        topRoller1.setInverted(false);
        topRoller2.setInverted(true);

        topRoller1.set(speed);
        topRoller2.set(speed);
    }
    

}
