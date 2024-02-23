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
     public Winch(){

     winchCanSparkMax = new CANSparkMax(Constants.Winches.kLeftWinchMotorID, MotorType.kBrushless);
     winchCanSparkMax.setSmartCurrentLimit(60, 40);
     winchCanSparkMax.setIdleMode(IdleMode.kBrake);

     winchCanSparkMaxTwo = new CANSparkMax(Constants.Winches.kRightWinchMotorID, MotorType.kBrushless);
     winchCanSparkMaxTwo.setSmartCurrentLimit(60, 40);
     winchCanSparkMaxTwo.setIdleMode(IdleMode.kBrake);

     //winchCanSparkMaxTwo.follow(winchCanSparkMax);
     
    }

    public void on(double speed){
        winchCanSparkMax.setInverted(false);
        winchCanSparkMaxTwo.setInverted(false);
        winchCanSparkMax.set(speed);
        winchCanSparkMaxTwo.set(speed);
          
    }

    public void off(){
        winchCanSparkMax.set(0);
        winchCanSparkMaxTwo.set(0);
    }

    public void reverse(double speed){
        winchCanSparkMax.setInverted(true);
        winchCanSparkMaxTwo.setInverted(true);
        winchCanSparkMax.set(speed);
        winchCanSparkMaxTwo.set(speed);
    }


   /*  private static double deadBand(double value, double deadband) {
        if(Math.abs(value) > deadband) {
            if(value > 0.0) {
                return (value - deadband) / (1 -deadband);
            }
            else {
                return (value + deadband) / (1 - deadband);
            }
        }

        return 0.0;
    }  */

    

}
