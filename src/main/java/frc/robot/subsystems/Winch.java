package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class Winch extends SubsystemBase {

    CANSparkMax winchCanSparkMax;
    CANSparkMax winchCanSparkMaxTwo;
    RelativeEncoder winchLeftEncoder;
    RelativeEncoder winchRightEncoder;
     public Winch(){

     winchCanSparkMax = new CANSparkMax(Constants.Winches.kLeftWinchMotorID, MotorType.kBrushless);
     winchCanSparkMax.setSmartCurrentLimit(60, 40);
     winchCanSparkMax.setIdleMode(IdleMode.kBrake);

     winchLeftEncoder = winchCanSparkMax.getEncoder();

     winchCanSparkMaxTwo = new CANSparkMax(Constants.Winches.kRightWinchMotorID, MotorType.kBrushless);
     winchCanSparkMaxTwo.setSmartCurrentLimit(60, 40);
     winchCanSparkMaxTwo.setIdleMode(IdleMode.kBrake);

     winchRightEncoder = winchCanSparkMaxTwo.getEncoder();

     winchLeftEncoder.setPosition(0);
     winchRightEncoder.setPosition(0);
     
    }

    public void leftOn(double speed){
        winchCanSparkMax.setInverted(false);
        winchCanSparkMax.set(speed);
        } 

    public void leftReverse(double speed){
        winchCanSparkMax.setInverted(true);
        winchCanSparkMax.set(speed);
            
     }

    public void rightOn(double speed){
        winchCanSparkMaxTwo.setInverted(false);
         winchCanSparkMaxTwo.set(speed);
    }

    public void rightReverse(double speed){
        winchCanSparkMaxTwo.setInverted(true);
        winchCanSparkMaxTwo.set(speed);
        
    }

    public void off(){
        winchCanSparkMax.set(0);
        winchCanSparkMaxTwo.set(0);
    }
    @Override
    public void periodic(){
     SmartDashboard.putNumber("Left Winch Position", getLeftPosition());
     SmartDashboard.putNumber("Right Winch Encoder", getRightPosition());
    }

    public double getRightPosition(){
        return winchRightEncoder.getPosition();
    }

    public double getLeftPosition() {
        return winchLeftEncoder.getPosition();
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
