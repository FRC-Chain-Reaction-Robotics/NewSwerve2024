package frc.robot.subsystems;


/* Github testing */
import edu.wpi.first.wpilibj2.command.SubsystemBase;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class Apriltags extends SubsystemBase{
   NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
   NetworkTableEntry tv = table.getEntry("tv");
   NetworkTableEntry tx = table.getEntry("tx");
   NetworkTableEntry ty = table.getEntry("ty");
   NetworkTableEntry ta = table.getEntry("ta");

   private double v;
   private double x;
   private double y;
   private double area;
  
   public Apriltags() {
       v = tv.getDouble(0.0);
       y = ty.getDouble(0.0);
       area = ta.getDouble(0.0);
   }
  
   //post to smart dashboard periodically
   public void postToSmartDashbooard(){
    SmartDashboard.putNumber("LimelightX", v);
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
   }


   //read values periodically
   public void updateLimelightValues(){
    SmartDashboard.putNumber("LimelightX", v);
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
   }

   public double getV(){
       return v;
   }

   public double getX(){
       return x;
   }


   public double getY(){
       return y;
   }


   public double getArea(){
       return area;
   }


}





