package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

public class Constants {

    public static final class DrivetrainConstants {

        public static final String DEVICE_NAME_FRONT_LEFT = "frontLeft";
        public static final String DEVICE_NAME_FRONT_RIGHT = "frontRight";
        public static final String DEVICE_NAME_BACK_LEFT = "backLeft";
        public static final String DEVICE_NAME_BACK_RIGHT = "backRight";

        public static final String DEVICE_NAME_IMU = "imu";

        public static final IMU.Parameters IMU_PARAMETERS = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                        RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
                )
        );

    }

    public static final class IntakeConstants {

        public static final String DEVICE_NAME_INTAKE = "intake";

        public static final double VELOCITY_INTAKE_RPM = 300;
        public static final double VELOCITY_OUTTAKE_RPM = -300;
        public static final double VELOCITY_STOP_RPM = 0;

        public static final double kP = 0;
        public static final double kF = 0;

    }

    public static final class FeederConstants {

        public static final String DEVICE_NAME_FEEDER = "feeder";

        public static final double VELOCITY_FEED_RPM = 300;
        public static final double VELOCITY_UNFEED_RPM = -300;
        public static final double VELOCITY_STOP_RPM = 0;

        public static final double kP = 0;
        public static final double kF = 0;

    }

    public static final class ShooterConstants {

        public static final String DEVICE_NAME_SHOOTER = "shooter";

        public static final double VELOCITY_SHOOT_RPM = 3000;
        public static final double VELOCITY_STOP_RPM = 0;

        public static final double DEADBAND_SHOOT_RPM = 100;

        public static final double kP = 0;
        public static final double kF = 0;

    }

}
