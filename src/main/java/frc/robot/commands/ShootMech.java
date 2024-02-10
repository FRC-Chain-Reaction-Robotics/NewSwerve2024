package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class ShootMech extends Command{
    Shooter m_shooter;

    public ShootMech(Shooter m_shooter) {
        this.m_shooter = m_shooter;
        addRequirements(m_shooter);
    }

    public void execute() {
        m_shooter.cherryBomb();
    }

    public boolean interrupted() {
        return m_shooter.shoot;
    }

    
} 
