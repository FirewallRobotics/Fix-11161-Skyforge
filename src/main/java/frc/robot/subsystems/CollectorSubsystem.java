package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CollectorSubsystem extends SubsystemBase {

    private final CANSparkMax collectorMotor1 = new CANSparkMax(Constants.COLLECTOR_ONE_ID, MotorType.kBrushless);
    private final CANSparkMax collectorMotor2 = new CANSparkMax(Constants.COLLECTOR_TWO_ID, MotorType.kBrushless);

    public CollectorSubsystem() {}

    public void collect(double speed) {
        collectorMotor1.set(speed);
        collectorMotor2.set(speed);
    }

    public void stop() {
        collectorMotor1.stopMotor();
        collectorMotor2.stopMotor();
    }
}