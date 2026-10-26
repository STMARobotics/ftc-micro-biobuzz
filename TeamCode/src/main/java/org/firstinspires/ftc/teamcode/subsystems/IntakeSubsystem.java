package org.firstinspires.ftc.teamcode.subsystems;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_USING_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static org.firstinspires.ftc.teamcode.Constants.IntakeConstants.DEVICE_NAME_INTAKE;
import static org.firstinspires.ftc.teamcode.Constants.IntakeConstants.VELOCITY_INTAKE_RPM;
import static org.firstinspires.ftc.teamcode.Constants.IntakeConstants.VELOCITY_OUTTAKE_RPM;
import static org.firstinspires.ftc.teamcode.Constants.IntakeConstants.VELOCITY_STOP_RPM;
import static org.firstinspires.ftc.teamcode.Constants.IntakeConstants.kF;
import static org.firstinspires.ftc.teamcode.Constants.IntakeConstants.kP;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.DcMotorConfig;

public class IntakeSubsystem extends SubsystemBase {

    private DcMotorEx intake;

    private Telemetry telemetry;

    public IntakeSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        intake = hardwareMap.get(DcMotorEx.class, DEVICE_NAME_INTAKE);

        DcMotorConfig motorConfig = new DcMotorConfig()
                .withDirection(FORWARD)
                .withZeroPowerBehavior(BRAKE)
                .withMode(RUN_USING_ENCODER);

        motorConfig.applyTo(intake);

        intake.setPIDFCoefficients(RUN_USING_ENCODER, new PIDFCoefficients(kP, 0, 0, kF));

        this.telemetry = telemetry;
    }

    private double rpmToTps(double rpm) {
        return rpm * 537.7 / 60.0;
    }

    private double tpsToRpm(double tps) {
        return tps / 537.7 * 60.0;
    }

    public void intake() {
        double tps = rpmToTps(VELOCITY_INTAKE_RPM);
        intake.setVelocity(tps);
    }

    public void outtake() {
        double tps = rpmToTps(VELOCITY_OUTTAKE_RPM);
        intake.setVelocity(tps);
    }

    public void stop() {
        double tps = rpmToTps(VELOCITY_STOP_RPM);
        intake.setVelocity(tps);
    }

    private double getVelocityRpm() {
        return tpsToRpm(intake.getVelocity());
    }

}
