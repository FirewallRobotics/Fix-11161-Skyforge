package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {

    // Left motors
    private final CANSparkMax leftFront = new CANSparkMax(Constants.LEFT_FRONT_ID, MotorType.kBrushless);
    private final CANSparkMax leftRear = new CANSparkMax(Constants.LEFT_REAR_ID, MotorType.kBrushless);

    // Right motors
    private final CANSparkMax rightFront = new CANSparkMax(Constants.RIGHT_FRONT_ID, MotorType.kBrushless);
    private final CANSparkMax rightRear = new CANSparkMax(Constants.RIGHT_REAR_ID, MotorType.kBrushless);

    private final MotorControllerGroup leftGroup = new MotorControllerGroup(leftFront, leftRear);
    private final MotorControllerGroup rightGroup = new MotorControllerGroup(rightFront, rightRear);
    private final DifferentialDrive drive = new DifferentialDrive(leftGroup, rightGroup);

    public DriveSubsystem() {
        rightGroup.setInverted(Constants.RIGHT_SIDE_INVERTED);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        drive.tankDrive(leftSpeed, rightSpeed);
    }

    public void stop() {
        drive.stopMotor();
    }
}