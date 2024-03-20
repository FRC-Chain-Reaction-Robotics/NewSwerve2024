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
    RelativeEncoder winchEncoder;
     public Winch(){

     winchCanSparkMax = new CANSparkMax(Constants.Winches.kWinchMotorID, MotorType.kBrushless);
     winchCanSparkMax.setSmartCurrentLimit(60, 40);
     winchCanSparkMax.setIdleMode(IdleMode.kBrake);

     winchEncoder = winchCanSparkMax.getEncoder();



     winchEncoder.setPosition(0);
     
    }

    public void on(double speed){
        winchCanSparkMax.setInverted(false);
        winchCanSparkMax.set(speed);
        } 

    public void reverse(double speed){
        winchCanSparkMax.setInverted(true);
        winchCanSparkMax.set(speed);
            
     }

    public void off(){
        winchCanSparkMax.set(0);
    }
    @Override
    public void periodic(){
     SmartDashboard.putNumber("Winch Position", getPosition());
    }


    public double getPosition() {
        return winchEncoder.getPosition();
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
