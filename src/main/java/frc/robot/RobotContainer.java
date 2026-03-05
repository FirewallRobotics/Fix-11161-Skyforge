package frc.robot;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

public class RobotContainer {

        // TODO for this project to build:
        // 1) fix imports
        //      - REV this year dropped using "CAN" in their function definitions so that needs to be removed
        //      - See StorageSubsystem and LauncherSubsystem for examples on how to fix
        // 2) figure and fix the error with auto (I've commented it out for now)

        // TODO: Recomendations:
        // 1) Verify motor IDs and make sure your motors are correctly defined.
        // 2) Move controller over to being command based for simplicity
        //      - https://docs.wpilib.org/en/stable/docs/software/commandbased/binding-commands-to-triggers.html
        //      - Wish they showed that they are using "CommandXboxController" not just "XboxController" :/
        //      - See example that I did below
        // 3) Move towards using commands instead of using "RunCommand."
        //      - With the current system I don't think it will stop the motors?
        //      - See example I did with enabling the collector
        // 4) Fix deprecations
        //      - This won't really affect functionality but its a good practice to stay up to date for future years
        //      3a) The scheduler is now global so to schedule a command you have to use: "CommandScheduler.getInstance().schedule(Command...)"
        //      3b) Add a motor follower instead of using "MotorControllerGroup"
        //              - https://www.chiefdelphi.com/t/how-to-set-motors-into-follower-mode-in-2025/485709

        // Overall this is a very good repo for starting out. If I had no knowledge of the particular....charms of WPILIB
        //  I would have the exact same setup going. Y'all are honestly in a really good place programming wise for a rookie team.

        // TODO: If you want to dive deeper into programming after getting everything working.
        //      1) Create a split branch on github (keep the working code separate for the love of robots!)
        //              - https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/about-branches

        //      2) Create and learn about motor configurations so that you can do the not deprecated form of motor following in code
        //              - https://docs.revrobotics.com/revlib/spark/configuring-a-spark
        //      3) Learn about Pathplanner and work it in to your auto. Instead of looking at code to create an auto,
        //          pathplanner allows you to stare at some nice UI.
        //              - https://pathplanner.dev/home.html#usage
        //              - https://pathplanner.dev/pplib-getting-started.html#install-pathplannerlib
        //      4) Create and learn about PID Controllers. More specifically tune one for your shooter so that you can shoot accurately with a fast ramp up time
        //              - https://docs.wpilib.org/en/stable/docs/software/advanced-controls/controllers/pidcontroller.html
        //              - https://www.youtube.com/watch?v=6EcxGh1fyMw&pp=ygUcaG93IHRvIHR1bmUgYSBwaWQgY29udHJvbGxlcg%3D%3D

        // Subsystems
        private final DriveSubsystem drive = new DriveSubsystem();
        private final CollectorSubsystem collector = new CollectorSubsystem();
        private final StorageSubsystem storage = new StorageSubsystem();
        private final LauncherSubsystem launcher = new LauncherSubsystem();

        // Controllers
        private final XboxController driverController = new XboxController(0);
        private final XboxController operatorController = new XboxController(1);

        // using command based controller is easier
        private final CommandXboxController operatorControllerCommand = new CommandXboxController(1);

        public RobotContainer() {
                configureBindings();

                // Default drive command
                drive.setDefaultCommand(
                        new RunCommand(
                                () -> drive.tankDrive(
                                        -driverController.getLeftY(),
                                        -driverController.getRightY()
                                ),
                                drive
                        )
                );
        }

        private void configureBindings() {
                // Collector
                // I would recomend putting these into commands
                // https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
                new JoystickButton(operatorController, XboxController.Button.kA.value)
                        .whileTrue(new Collect(collector));
                new JoystickButton(operatorController, XboxController.Button.kB.value)
                        .whileTrue(new RunCommand(() -> collector.collect(-0.7), collector));

                // Storage feed
                // I would also setup buttons like this
                operatorControllerCommand.x().whileTrue(new RunCommand(() -> storage.feed(0.6), storage));

                // OLD:
                //new JoystickButton(operatorController, XboxController.Button.kX.value)
                //        .whileTrue(new RunCommand(() -> storage.feed(0.6), storage));
                new JoystickButton(operatorController, XboxController.Button.kY.value)
                        .whileTrue(new RunCommand(() -> storage.feed(-0.6), storage));

                // Launcher
                new JoystickButton(operatorController, XboxController.Button.kB.value)
                        .whileTrue(new RunCommand(() -> launcher.shoot(1.0), launcher));
                new JoystickButton(operatorController, XboxController.Button.kB.value)
                        .whileTrue(new RunCommand(() -> launcher.shoot(-1.0), launcher));
        }

        // Example simple autonomous
        public RunCommand getAutonomousCommand() {
                // FIXME: Figure out what is going wrong
                return null;
                //return new RunCommand(
                //        () -> {
                //            drive.tankDrive(0.5, 0.5);
                //            collector.collect(0.7);
                //            storage.feed(0.6);
                //            launcher.shoot(1.0);
                //        },
                //        drive, collector, storage, launcher
                //).withTimeout(3).andThen(() -> {
                //    drive.stop();
                //    collector.stop();
                //    storage.stop();
                //    launcher.stop();
                //});
    }
}