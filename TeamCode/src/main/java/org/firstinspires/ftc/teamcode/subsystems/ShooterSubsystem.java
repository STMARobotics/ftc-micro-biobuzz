package org.firstinspires.ftc.teamcode.subsystems;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_USING_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.FLOAT;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import static org.firstinspires.ftc.teamcode.Constants.ShooterConstants.DEADBAND_SHOOT_RPM;
import static org.firstinspires.ftc.teamcode.Constants.ShooterConstants.DEVICE_NAME_SHOOTER;
import static org.firstinspires.ftc.teamcode.Constants.ShooterConstants.VELOCITY_SHOOT_RPM;
import static org.firstinspires.ftc.teamcode.Constants.ShooterConstants.VELOCITY_STOP_RPM;
import static org.firstinspires.ftc.teamcode.Constants.ShooterConstants.kF;
import static org.firstinspires.ftc.teamcode.Constants.ShooterConstants.kP;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.DcMotorConfig;

public class ShooterSubsystem extends SubsystemBase {

    private DcMotorEx shooter;

    private Telemetry telemetry;

    public ShooterSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        shooter = hardwareMap.get(DcMotorEx.class, DEVICE_NAME_SHOOTER);

        DcMotorConfig motorConfig = new DcMotorConfig()
                .withDirection(FORWARD)
                .withZeroPowerBehavior(FLOAT)
                .withMode(RUN_USING_ENCODER);



        motorConfig.applyTo(shooter);

        shooter.setPIDFCoefficients(RUN_USING_ENCODER, new PIDFCoefficients(kP, 0, 0, kF));

        this.telemetry = telemetry;
    }

    private double rpmToTps(double rpm) {
        return rpm * 28.0 / 60.0;
    }

    private double tpsToRpm(double tps) {
        return tps / 28.0 * 60.0;
    }

    public void shoot() {
        double tps = rpmToTps(VELOCITY_SHOOT_RPM);
        shooter.setVelocity(tps);
    }

    public void stop() {
        double tps = rpmToTps(VELOCITY_STOP_RPM);
        shooter.setVelocity(tps);
    }

    private double getVelocityRpm() {
        return tpsToRpm(shooter.getVelocity());
    }

    public boolean isReady() {
        return Math.abs(getVelocityRpm() - VELOCITY_SHOOT_RPM) <= DEADBAND_SHOOT_RPM;
    }

}
