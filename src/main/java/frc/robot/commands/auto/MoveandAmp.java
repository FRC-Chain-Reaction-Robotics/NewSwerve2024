package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.OldIntake;
import frc.robot.subsystems.Swerve;

public class MoveandAmp extends SequentialCommandGroup {

    public MoveandAmp(OldIntake m_intake, Swerve m_Swerve) {
        // TODO: Test TurnToAngle
        addCommands(new DriveToDistance(1.5, m_Swerve), new TurnToAngle(-90, m_Swerve),
                new DriveToDistance(.25, m_Swerve),
                new InstantCommand(() -> m_intake.on(Constants.Intake.kIntakeSpeed)));

    }

}
