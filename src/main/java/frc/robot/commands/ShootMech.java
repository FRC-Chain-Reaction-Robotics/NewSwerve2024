package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ManualShooter;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.HybridShooter;
public class ShootMech extends Command{

    HybridShooter m_manShooter;


    public ShootMech(HybridShooter m_shooter) {
        this.m_manShooter = m_shooter;
        addRequirements(m_shooter);
    }

    @Override
    public void initialize() {
        m_manShooter.cherryBomb();
    }

    @Override
    public void end(boolean wasInturrupted){
        m_manShooter.shootOff();
    }


    /* 
    Shooter m_shooter;
    HybridShooter m_hybridShooter;
    ManualShooter m_manShooter;

   /* public ShootMech(HybridShooter m_shooter) {
        this.m_hybridShooter = m_shooter;
        addRequirements(m_shooter);
    }

     /*public ShootMech(ManualShooter m_shooter) {
        this.m_manShooter = m_shooter;
        addRequirements(m_shooter);
    }*/

   // public void limeExecute() {
    //    m_shooter.cherryBomb();
   // }
/* 
    public void execute() {
        m_hybridShooter.cherryBomb();
    }

    public boolean interrupted() {
        return /*m_shooter.shoot || m_shooter.shoot;
    }

    */
} 
