package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ShootMech;
//import frc.robot.subsystems.ManualShooter;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.HybridShooter;
import frc.robot.subsystems.ManualShooter;
public class ShootAndMove extends SequentialCommandGroup {


    public ShootAndMove(HybridShooter m_shooter, Swerve m_Swerve)
    {
        
        //TODO: Change up Shoot and Move
        addCommands(new ShootMech(m_shooter).withTimeout(1.5), new DriveToDistance(0, m_Swerve).withTimeout(1));
        
    }
    
}
