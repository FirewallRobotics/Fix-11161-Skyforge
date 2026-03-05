package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LauncherSubsystem extends SubsystemBase {

    // This year we found that REV dropped the "CAN" in all their definitions
    private final SparkMax launcherMotor = new SparkMax(Constants.LAUNCHER_MOTOR_ID, MotorType.kBrushless);

    public void shoot(double speed) {
        launcherMotor.set(speed);
    }

    public void stop() {
        launcherMotor.stopMotor();
    }
}