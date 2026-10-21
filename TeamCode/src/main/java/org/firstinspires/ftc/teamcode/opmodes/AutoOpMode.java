package org.firstinspires.ftc.teamcode.opmodes;

import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSubsystem;

public class AutoOpMode extends CommandOpMode {

    private DrivetrainSubsystem drivetrainSubsystem;

    @Override
    public void initialize() {
        drivetrainSubsystem = new DrivetrainSubsystem(hardwareMap, telemetry);

        drivetrainSubsystem.runEnd(
                () -> drivetrainSubsystem.drive(1, 0, 0),
                () -> drivetrainSubsystem.drive(0, 0, 0)
        ).withTimeout(500).schedule();
    }

}
