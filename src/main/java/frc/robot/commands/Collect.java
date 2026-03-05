package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.CollectorSubsystem;

public class Collect extends Command {

    // Create a var to store CollectorSubsystem
    private final CollectorSubsystem m_collector;

    // Get the speed for the collector to use
    double collectorSpeed = Constants.COLLECTOR_SPEED;

    // Get the collector from robot Container
    public Collect(CollectorSubsystem i_collector) {
        m_collector = i_collector;
    }

    // start collecting once the button is pushed
    // Runs once when the command is pressed
    @Override
    public void initialize() {
        m_collector.collect(collectorSpeed);
    }

    // Runs every frame while this command is running (including once before initialize)
    @Override
    public void execute() {}

    // Runs once when the command ends
    @Override
    public void end(boolean interupted) {
        m_collector.stop();
    }

    // Allows the command to decide if the command is finished
    // Command can also be finished by the scheduler when the button is released
    @Override
    public boolean isFinished() {
        return false;
  }
}
