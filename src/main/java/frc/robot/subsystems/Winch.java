package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class Winch extends SubsystemBase {

    CANSparkMax winchCanSparkMax;
    CANSparkMax winchCanSparkMaxTwo;
    RobotContainer holder;

     public Winch(){

     holder = new RobotContainer();

     winchCanSparkMax = new CANSparkMax(Constants.Winches.kLeftWinchMotorID, MotorType.kBrushless);
     winchCanSparkMax.setInverted(false);
     winchCanSparkMax.setSmartCurrentLimit(60);
     winchCanSparkMax.setIdleMode(IdleMode.kBrake);

     winchCanSparkMaxTwo = new CANSparkMax(Constants.Winches.kRightWinchMotorID, MotorType.kBrushless);
     winchCanSparkMaxTwo.setInverted(false);
     winchCanSparkMaxTwo.setSmartCurrentLimit(60);
     winchCanSparkMaxTwo.setIdleMode(IdleMode.kBrake);
     winchCanSparkMaxTwo.follow(winchCanSparkMax);
     
    }
    
    public void winchExtend() {
     if(deadBand(holder.m_operatorController.getRightY(), .3) > 0) {
        winchCanSparkMax.set(.6);
        //winchCanSparkMaxTwo.set(.6);
     }
     else if(deadBand(holder.m_operatorController.getRightY(), .3) < 0) {
        winchCanSparkMax.set(-.6);
       // winchCanSparkMaxTwo.set(-.6);
     }
     else {
        winchCanSparkMax.set(0);
        //winchCanSparkMaxTwo.set(0);
     }
    }

    private static double deadBand(double value, double deadband) {
        if(Math.abs(value) > deadband) {
            if(value > 0.0) {
                return (value - deadband) / (1 -deadband);
            }
            else {
                return (value + deadband) / (1 - deadband);
            }
        }

        return 0.0;
    }  

    

}
