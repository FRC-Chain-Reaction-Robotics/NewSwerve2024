// Copyright (c) 2023 FRC Team 2881 - The Lady Cans
//
// Open Source Software; you can modify and/or share it under the terms of BSD
// license file in the root directory of this project.
/* Github testing */
package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.RobotBase;

public final class Main {
  private Main() {}

  public static void main(String... args) {

    try{
    RobotBase.startRobot(Robot::new);
    }
    catch(IllegalArgumentException e){
    }
    
  }

  public static void print(Object o) {
    System.out.println("Angle of CANcoder: " + ((Rotation2d)o).getDegrees());
  }
}
