package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class Winch extends SubsystemBase {

    CANSparkMax winchCanSparkMax;
    CANSparkMax winchCanSparkMaxTwo;
    RelativeEncoder winchEncoder;
     public Winch(){

     winchCanSparkMax = new CANSparkMax(Constants.Winches.kLeftWinchMotorID, MotorType.kBrushless);
     winchCanSparkMax.setSmartCurrentLimit(60, 40);
     winchCanSparkMax.setIdleMode(IdleMode.kBrake);

     winchEncoder = winchCanSparkMax.getEncoder();
     winchCanSparkMaxTwo = new CANSparkMax(Constants.Winches.kRightWinchMotorID, MotorType.kBrushless);
     winchCanSparkMaxTwo.setSmartCurrentLimit(60, 40);
     winchCanSparkMaxTwo.setIdleMode(IdleMode.kBrake);

     //winchCanSparkMaxTwo.follow(winchCanSparkMax);
     
    }

    public void leftOn(double speed){
        winchCanSparkMax.setInverted(false);
       // winchCanSparkMaxTwo.setInverted(false);
        boolean cantGoUp = winchEncoder.getPosition() > 10;
         if(cantGoUp && speed > 0) {
            winchCanSparkMax.set(0);
            //winchCanSparkMaxTwo.set(0);
        }
        else{
            winchCanSparkMax.set(speed);
         //   winchCanSparkMaxTwo.set(speed);
        } 
    }

    public void off(){
        winchCanSparkMax.set(0);
        winchCanSparkMaxTwo.set(0);
    }

    public void leftReverse(double speed){
        winchCanSparkMax.setInverted(true);
        boolean cantGoDown = winchEncoder.getPosition() < 0;

        if(cantGoDown && speed < 0) {
            winchCanSparkMax.set(0);
           // winchCanSparkMaxTwo.set(0);
        }
        else{
            winchCanSparkMax.set(speed);
            //winchCanSparkMaxTwo.set(speed);
        }
    }

    

    public void rightOn(double speed){
        winchCanSparkMax.setInverted(false);
       // winchCanSparkMaxTwo.setInverted(false);
        boolean cantGoUp = winchEncoder.getPosition() > 10;
         if(cantGoUp && speed > 0) {
            winchCanSparkMaxTwo.set(0);
        }
        else{
            winchCanSparkMaxTwo.set(speed);
        } 
    }

    public void rightReverse(double speed){
        winchCanSparkMaxTwo.setInverted(false);
        boolean cantGoDown = winchEncoder.getPosition() < 0;

        if(cantGoDown && speed < 0) {
            winchCanSparkMaxTwo.set(0);
        }
        else{
            winchCanSparkMaxTwo.set(speed);
        }
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
