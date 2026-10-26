package org.firstinspires.ftc.teamcode.subsystems;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_USING_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;
import static org.firstinspires.ftc.teamcode.Constants.FeederConstants.DEVICE_NAME_FEEDER;
import static org.firstinspires.ftc.teamcode.Constants.FeederConstants.VELOCITY_FEED_RPM;
import static org.firstinspires.ftc.teamcode.Constants.FeederConstants.VELOCITY_STOP_RPM;
import static org.firstinspires.ftc.teamcode.Constants.FeederConstants.VELOCITY_UNFEED_RPM;
import static org.firstinspires.ftc.teamcode.Constants.FeederConstants.kF;
import static org.firstinspires.ftc.teamcode.Constants.FeederConstants.kP;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.MotorControlAlgorithm;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.DcMotorConfig;

public class FeederSubsystem extends SubsystemBase {

    private DcMotorEx feeder;

    private Telemetry telemetry;

    public FeederSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        feeder = hardwareMap.get(DcMotorEx.class, DEVICE_NAME_FEEDER);

        DcMotorConfig motorConfig = new DcMotorConfig()
                .withDirection(REVERSE)
                .withZeroPowerBehavior(BRAKE)
                .withMode(RUN_USING_ENCODER);

        motorConfig.applyTo(feeder);

        feeder.setPIDFCoefficients(RUN_USING_ENCODER, new PIDFCoefficients(kP, 0, 0, kF));

        this.telemetry = telemetry;
    }

    private double rpmToTps(double rpm) {
        return rpm * 537.7 / 60.0;
    }

    private double tpsToRpm(double tps) {
        return tps / 537.7 * 60.0;
    }

    public void feed() {
        double tps = rpmToTps(VELOCITY_FEED_RPM);
        feeder.setVelocity(tps);
    }

    public void unfeed() {
        double tps = rpmToTps(VELOCITY_UNFEED_RPM);
        feeder.setVelocity(tps);
    }

    public void stop() {
        double tps = rpmToTps(VELOCITY_STOP_RPM);
        feeder.setVelocity(tps);
    }

    private double getVelocityRpm() {
        return tpsToRpm(feeder.getVelocity());
    }

}
