package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class StorageSubsystem extends SubsystemBase {

    private final SparkMax storageMotor = new SparkMax(Constants.STORAGE_MOTOR_ID, MotorType.kBrushless);

    public void feed(double speed) {
        storageMotor.set(speed);
    }

    public void stop() {
        storageMotor.stopMotor();
    }
}