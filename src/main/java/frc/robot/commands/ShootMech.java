package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ManualShooter;
import frc.robot.subsystems.Shooter;

public class ShootMech extends Command{
    Shooter m_shooter;
    ManualShooter m_manShooter;

   /* */ public ShootMech(Shooter m_shooter) {
        this.m_shooter = m_shooter;
        addRequirements(m_shooter);
    }

     public ShootMech(ManualShooter m_shooter) {
        this.m_manShooter = m_shooter;
        addRequirements(m_shooter);
    }

   // public void limeExecute() {
    //    m_shooter.cherryBomb();
   // }

    public void execute() {
        m_manShooter.cherryBomb();
    }

    public boolean interrupted() {
        return /*m_shooter.shoot ||*/ m_manShooter.shoot;
    }

    
} 
