package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.RunCommand;

import org.firstinspires.ftc.teamcode.Controls;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSubsystem;

@TeleOp
public class TeleopOpMode extends CommandOpMode {

    private Controls controls;
    private DrivetrainSubsystem drivetrainSubsystem;

    @Override
    public void initialize() {
        controls = new Controls(gamepad1);
        drivetrainSubsystem = new DrivetrainSubsystem(hardwareMap, telemetry);

        drivetrainSubsystem.setDefaultCommand(
                new RunCommand(() -> drivetrainSubsystem.drive(
                        controls.driveTranslationY().getAsDouble(),
                        controls.driveTranslationX().getAsDouble(),
                        controls.driveRotation().getAsDouble()),
                drivetrainSubsystem));

        controls.resetYaw().whenActive(drivetrainSubsystem.runOnce(drivetrainSubsystem::resetYaw));
    }
}
