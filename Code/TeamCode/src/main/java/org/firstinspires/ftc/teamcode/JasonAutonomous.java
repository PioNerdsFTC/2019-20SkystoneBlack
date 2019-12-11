package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="JasonAuto", group="Pushbot")
public class JasonAutonomous extends LinearOpMode {
    HardwareMap hwMap;
    Autobot robot = new Autobot();
    private ElapsedTime runtime = new ElapsedTime();
    BNO055IMU imu;

    static final double FORWARD_SPEED = 0.6;
    static final double TURN_SPEED = 0.5;

    public void runOpMode() {
        robot.init(hwMap);
        imu = hwMap.get(BNO055IMU.class, "imu");
        Gyro gyro = new Gyro(imu);

        double initialDriveForwardTime = 3;
        //       double secondDriveForwardTime = 3.0;
        double initialGyroValue = gyro.getZ();

        // 1. Drive straight for X seconds
        robot.FLeft.setPower(FORWARD_SPEED);
        robot.FRight.setPower(FORWARD_SPEED);
        robot.BLeft.setPower(FORWARD_SPEED);
        robot.BRight.setPower(FORWARD_SPEED);

        runtime.reset();
        while ((runtime.seconds() < initialDriveForwardTime)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Stop Robot if blue
/*        if (robot.colorSensor.blue() == 0) {
            robot.FLeft.setPower(0);
            robot.FRight.setPower(0);
            robot.BLeft.setPower(0);
            robot.BRight.setPower(0);
        }
*/
        robot.FLeft.setPower(0);
        robot.FRight.setPower(0);
        robot.BLeft.setPower(0);
        robot.BRight.setPower(0);
        // 2. Lower Grabber, Pick up block, Lift Grabber

        // 3. Back up

        // 4. Turn Left/Right 90 degrees
        robot.FLeft.setPower(-TURN_SPEED);
        robot.FRight.setPower(+TURN_SPEED);
        robot.BLeft.setPower(-TURN_SPEED);
        robot.BRight.setPower(+TURN_SPEED);
        runtime.reset();
        while ((gyro.getZ() < initialGyroValue + 90)) {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.FLeft.setPower(0);
        robot.FRight.setPower(0);
        robot.BLeft.setPower(0);
        robot.BRight.setPower(0);
        runtime.reset();
 /*      // 4. Move forward X seconds
        robot.FLeft.setPower(FORWARD_SPEED);
        robot.FRight.setPower(FORWARD_SPEED);
        robot.BLeft.setPower(FORWARD_SPEED);
        robot.BRight.setPower(FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < secondDriveForwardTime)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
            }
        // 5. Lower Grabber, Put down block

        // 6. and on and on.


  */
    }
}