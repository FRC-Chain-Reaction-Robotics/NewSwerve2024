// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.hal.util.AllocationException;
/* Github testing */
//import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

@Deprecated
public class OldPneumaticsSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private DoubleSolenoid ds;

  public OldPneumaticsSubsystem() {
    try {
      ds = new DoubleSolenoid(42, PneumaticsModuleType.CTREPCM, 1, 2);
      ds.set(DoubleSolenoid.Value.kForward);
    } catch (AllocationException e) {

    }
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  // public Command exampleMethodCommand() {
  // // Inline construction of command goes here.
  // // Subsystem::RunOnce implicitly requires `this` subsystem.
  // return runOnce(
  // () -> {
  // /* one-time action goes here */
  // });
  // }

  /**
   * An example method querying a boolean state of the subsystem (for example, a
   * digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  public void toggle() {
    ds.toggle();
  }

  @Override
  public void periodic() {
    // Do nothing
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}