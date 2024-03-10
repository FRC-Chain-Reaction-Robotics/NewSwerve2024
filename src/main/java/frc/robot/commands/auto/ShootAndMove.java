package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ShootMech;
import frc.robot.subsystems.ManualShooter;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Swerve;

public class ShootAndMove extends SequentialCommandGroup {


    public ShootAndMove(ManualShooter m_shooter, Swerve m_Swerve)
    {
        
        addCommands(new ShootMech(m_shooter), new DriveToDistance(-3, m_Swerve));
    }
    
}
