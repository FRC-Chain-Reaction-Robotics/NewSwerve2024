package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
//import frc.robot.subsystems.ManualShooter;
import frc.robot.subsystems.Shooter;

//import frc.robot.subsystems.HybridShooter;
public class ShootMech extends Command {
    Shooter m_manShooter;

    public ShootMech(Shooter m_shooter) {
        this.m_manShooter = m_shooter;
        addRequirements(m_shooter);
    }

    @Override
    public void initialize() {
        // TODO: Point shooter forwards/in proper direction
    }
    

    @Override
    public void end(boolean wasInturrupted) {
        m_manShooter.shoot(0);
    }
    
    @Override
    public void execute() {
        m_manShooter.shoot(Constants.Shooter.SHOOTING_SPEED);
    }

    public boolean interrupted() {
        return false;
    }

}
