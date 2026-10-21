package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.ftccommon.internal.manualcontrol.parameters.ImuParameters;

public class Constants {

    public static final class DrivetrainConstants {

        public static final String DEVICE_NAME_FRONT_LEFT = "frontLeftDrive";
        public static final String DEVICE_NAME_FRONT_RIGHT = "frontRightDrive";
        public static final String DEVICE_NAME_BACK_LEFT = "backLeftDrive";
        public static final String DEVICE_NAME_BACK_RIGHT = "backRightDrive";

        public static final String DEVICE_NAME_IMU = "imu";

        public static final IMU.Parameters IMU_PARAMETERS = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
                )
        );

    }

}
