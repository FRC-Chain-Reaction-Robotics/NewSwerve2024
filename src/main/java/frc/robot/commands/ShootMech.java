package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.HybridShooter;
import frc.robot.subsystems.ManualShooter;
import frc.robot.subsystems.Shooter;

public class ShootMech extends Command{
    Shooter m_shooter;
    ManualShooter m_manShooter;
    HybridShooter m_HybridShooter;

   /*  public ShootMech(Shooter m_shooter) {
        this.m_shooter = m_shooter;
        addRequirements(m_shooter);
    } */

    /*  public ShootMech(ManualShooter m_shooter) {
        this.m_manShooter = m_shooter;
        addRequirements(m_shooter);
    } */

    public ShootMech(HybridShooter m_HybridShooter) {
        this.m_HybridShooter = m_HybridShooter;
        addRequirements(m_HybridShooter);
    }

   // public void limeExecute() {
    //    m_shooter.cherryBomb();
   // }

    public void execute() {
        m_HybridShooter.cherryBomb();
    }

    public boolean interrupted() {
        return /*m_shooter.shoot ||*/ m_HybridShooter.shoot;
    }

    
} 
