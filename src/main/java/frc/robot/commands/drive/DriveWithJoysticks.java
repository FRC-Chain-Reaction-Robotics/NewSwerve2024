// Copyright (c) 2023 FRC Team 2881 - The Lady Cans
//
// Open Source Software; you can modify and/or share it under the terms of BSD
// license file in the root directory of this project.

package frc.robot.commands.drive;

import java.util.function.DoubleSupplier;
import java.util.function.BooleanSupplier;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Swerve;

public class DriveWithJoysticks extends Command {
  private final Swerve m_swerve;
  private final DoubleSupplier m_translationXSupplier;
  private final DoubleSupplier m_translationYSupplier;
  private final DoubleSupplier m_rotationSupplier;
  private final BooleanSupplier m_BooleanSupplier;
  

  public DriveWithJoysticks(Swerve swerve,
  DoubleSupplier translationXSupplier,
  DoubleSupplier translationYSupplier,
  DoubleSupplier rotationSupplier,
  /*Boolean supplier is the thing that determines if you want field relative or not: true if field relative */
  BooleanSupplier booleanSupplier) {
    m_swerve = swerve;
    m_translationXSupplier = translationXSupplier;
    m_translationYSupplier = translationYSupplier;
    m_rotationSupplier = rotationSupplier;
   m_BooleanSupplier = booleanSupplier;

    
    addRequirements(m_swerve);
  }

  @Override
  public void execute() {
   m_swerve.drive(m_translationXSupplier.getAsDouble() * Constants.Swerve.kMaxSpeedMetersPerSecond, 
   m_translationYSupplier.getAsDouble() * Constants.Swerve.kMaxSpeedMetersPerSecond, 
   m_rotationSupplier.getAsDouble() * Constants.Swerve.kMaxAngularSpeed, 
   m_BooleanSupplier.getAsBoolean());
  }

  @Override
  public void end(boolean interrupted) {
   m_swerve.drive(0, 0, 0, m_BooleanSupplier.getAsBoolean());
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
