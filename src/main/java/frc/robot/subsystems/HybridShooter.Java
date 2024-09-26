package frc.robot.subsystems;

import frc.robot.Constants;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkAbsoluteEncoder.Type;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkBase;

/* Github testing */
public class HybridShooter extends SubsystemBase {

  CANSparkMax shooterCANSparkMax;
  CANSparkMax shooterCANSparkMaxThree;

  TalonFX shooterTalonFXTwo;
  TalonFX shooterTalonFXFour;
  AbsoluteEncoder throughBoreEncoder;
  // TODO: update the launch speed

  CANSparkBase base;

  private PneumaticsSubsystem m_PneumaticsSubsystem;
  private PIDController m_YPidController = new PIDController(.4, 0, 0);
  private double[] yTarget = { 0.3, 0.063 };// put in target shooter angles
  // private double tolerance = 0.3;
  public boolean shoot = false;

  public HybridShooter(Swerve m_Swerve, PneumaticsSubsystem m_PneumaticsSubsystem) {
    this.m_PneumaticsSubsystem = m_PneumaticsSubsystem;

    // TODO: Update all of the Constant
    shooterCANSparkMax = new CANSparkMax(15, MotorType.kBrushless);
    shooterTalonFXTwo = new TalonFX(14);
    shooterCANSparkMaxThree = new CANSparkMax(13, MotorType.kBrushless);
    shooterTalonFXFour = new TalonFX(16);
    throughBoreEncoder = shooterCANSparkMaxThree.getAbsoluteEncoder(Type.kDutyCycle);

    shooterCANSparkMax.setInverted(false);
    shooterCANSparkMax.setSmartCurrentLimit(40);
    shooterCANSparkMax.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);

    shooterCANSparkMaxThree.setSmartCurrentLimit(40);
    shooterCANSparkMaxThree.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);

    shooterTalonFXTwo.setInverted(false);
    shooterTalonFXTwo.setNeutralMode(NeutralModeValue.Brake);
    // shooterTalonFXTwo.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);

    shooterCANSparkMax.follow(shooterCANSparkMaxThree);

    shooterTalonFXFour.setInverted(true);
    // shooterTalonFXFour.setVoltage(40);
    shooterTalonFXFour.setNeutralMode(NeutralModeValue.Brake);
    // shooterTalonFXFour.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);

    // tolerance
    m_YPidController.setTolerance(Constants.Shooter.yTolerance);

    shooterCANSparkMax.burnFlash();
    shooterCANSparkMaxThree.burnFlash();

    returnToUp();
    // m_shooterPidController.setTolerance(.2);
  }

  public double getVelocity() {
    // Find a replacement for getEncoder();
    return shooterTalonFXTwo.getVelocity().getValueAsDouble();
  }

  public boolean isWithinYToleranceToGround() {
    return (Math.abs(throughBoreEncoder.getPosition() - yTarget[0]) < Constants.Shooter.yTolerance);
  }

  public boolean isWithinYToleranceToShooter() {
    return (Math.abs(throughBoreEncoder.getPosition() - yTarget[1]) < Constants.Shooter.yTolerance);
  }

  public void groundIntake() {
    shooterCANSparkMaxThree.setInverted(false);
    shooterCANSparkMax.setInverted(true);

    if (!isWithinYToleranceToGround()) {
      shooterCANSparkMaxThree.set(m_YPidController.calculate(throughBoreEncoder.getPosition(), yTarget[0]));
    }
    if(isWithinYToleranceToGround()){
      shooterTalonFXFour.setInverted(false);
      shooterTalonFXTwo.setInverted(false);
      shooterTalonFXTwo.set(Constants.Shooter.intakeSpeedLimit);
      shooterTalonFXFour.set(Constants.Shooter.intakeSpeedLimit);
    }
  }

  public void returnToUp() {
    shooterCANSparkMaxThree.setInverted(true);
    shooterCANSparkMax.setInverted(true);
    if (!isWithinYToleranceToShooter()) {
      shooterCANSparkMax.set(m_YPidController.calculate(throughBoreEncoder.getPosition(), yTarget[1]));
      shooterCANSparkMaxThree.set(m_YPidController.calculate(throughBoreEncoder.getPosition(), yTarget[1]));
    }
  }

  public void shootOff() {
    if (shooterTalonFXTwo.getVelocity().getValueAsDouble() != 0
        || shooterTalonFXFour.getVelocity().getValueAsDouble() != 0) {
      shooterTalonFXTwo.set(0);
      shooterTalonFXFour.set(0);
    }
  }

  public void cherryBomb() {
    // TODO: Add the launch speed
    // shooterTalonFXTwo.set(Constants.Shooter.launchSpeedLimit);
    // shooterTalonFXFour.set(Constants.Shooter.launchSpeedLimit);
    // if(shooterTalonFXTwo.getVelocity().getValueAsDouble()<0.6 ||
    // shooterTalonFXFour.getVelocity().getValueAsDouble() <
    // 0.6||!Constants.Shooter.onTarget){
    // Constants.Shooter.onTarget = withinYToleranceShooter;
    // if(!withinYToleranceShooter){
    // shooterCANSparkMaxThree.set(m_YPidController.calculate(throughBoreEncoder.getPosition(),
    // yTarget[1]));
    // }
    // }
    // else{
    // m_PneumaticsSubsystem.toggle();
    // }

    shooterTalonFXTwo.setInverted(true);
    shooterTalonFXFour.setInverted(true);
    shooterTalonFXTwo.set(Constants.Shooter.launchSpeedLimit);
    shooterTalonFXFour.set(Constants.Shooter.launchSpeedLimit);
   //if(shooterTalonFXTwo.getVelocity().getValueAsDouble() >= 0.6 && shooterTalonFXFour.getVelocity().getValueAsDouble() >= 0.6){
    m_PneumaticsSubsystem.toggle();
    m_PneumaticsSubsystem.toggle();

  // }
  }

  /*
   * public boolean shootBAM() {
   * double speedDifference =
   * m_shooterPidController.calculate(shooterCANSparkMax.getEncoder().getVelocity(
   * ), Constants.Shooter.launchSpeedLimit);
   * shooterCANSparkMax.set(Constants.Shooter.launchSpeedLimit - speedDifference);
   * return m_shooterPidController.atSetpoint();
   * }
   */
}
