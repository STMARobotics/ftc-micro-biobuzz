package org.firstinspires.ftc.teamcode.subsystems;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_WITHOUT_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;
import static org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants.DEVICE_NAME_BACK_LEFT;
import static org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants.DEVICE_NAME_BACK_RIGHT;
import static org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants.DEVICE_NAME_FRONT_LEFT;
import static org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants.DEVICE_NAME_FRONT_RIGHT;
import static org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants.DEVICE_NAME_IMU;
import static org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants.IMU_PARAMETERS;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.DcMotorConfig;

public class DrivetrainSubsystem extends SubsystemBase {

    private DcMotorEx frontLeft;
    private DcMotorEx frontRight;
    private DcMotorEx backLeft;
    private DcMotorEx backRight;

    private IMU imu;

    private Telemetry telemetry;

    public DrivetrainSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        frontLeft = hardwareMap.get(DcMotorEx.class, DEVICE_NAME_FRONT_LEFT);
        frontRight = hardwareMap.get(DcMotorEx.class, DEVICE_NAME_FRONT_RIGHT);
        backLeft = hardwareMap.get(DcMotorEx.class, DEVICE_NAME_BACK_LEFT);
        backRight = hardwareMap.get(DcMotorEx.class, DEVICE_NAME_BACK_RIGHT);

        DcMotorConfig leftMotorConfig = new DcMotorConfig()
                .withDirection(FORWARD)
                .withZeroPowerBehavior(BRAKE)
                .withMode(RUN_WITHOUT_ENCODER);
        DcMotorConfig rightMotorConfig = new DcMotorConfig()
                .withDirection(REVERSE)
                .withZeroPowerBehavior(BRAKE)
                .withMode(RUN_WITHOUT_ENCODER);

        leftMotorConfig.applyTo(frontLeft, backLeft);
        rightMotorConfig.applyTo(frontRight, backRight);

        imu = hardwareMap.get(IMU.class, DEVICE_NAME_IMU);

        imu.initialize(IMU_PARAMETERS);
    }

    public double getYaw() {
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
    }

    public void resetYaw() {
        imu.resetYaw();
    }

    public void drive(double translationY, double translationX, double rotation) {
        double botHeading = getYaw();

        double rotX = translationX * Math.cos(-botHeading) - translationY * Math.sin(-botHeading);
        double rotY = translationX * Math.sin(-botHeading) + translationY * Math.cos(-botHeading);

        rotX *= 1.1;

        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rotation), 1);

        double frontLeftPower = (rotY + rotX + rotation) / denominator;
        double backLeftPower = (rotY - rotX + rotation) / denominator;
        double frontRightPower = (rotY - rotX - rotation) / denominator;
        double backRightPower = (rotY + rotX - rotation) / denominator;

        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);
    }

    public void stop() {
        drive(0, 0, 0);
    }

}
