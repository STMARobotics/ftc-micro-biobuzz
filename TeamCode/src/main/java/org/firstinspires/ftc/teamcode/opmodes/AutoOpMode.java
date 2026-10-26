package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSubsystem;

@Autonomous
public class AutoOpMode extends CommandOpMode {

    private DrivetrainSubsystem drivetrainSubsystem;

    @Override
    public void initialize() {
        drivetrainSubsystem = new DrivetrainSubsystem(hardwareMap, telemetry);

        drivetrainSubsystem.runEnd(
                () -> drivetrainSubsystem.drive(1, -1, 0),
                () -> drivetrainSubsystem.drive(0, 0, 0)
        ).withTimeout(300).schedule();
    }

}
