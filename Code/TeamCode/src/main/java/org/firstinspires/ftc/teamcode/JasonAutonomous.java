package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="JasonAuto", group="Pushbot")
public class JasonAutonomous extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    public DcMotor  FLeft;
    public DcMotor  FRight;
    public DcMotor  BLeft;
    public DcMotor  BRight;
    public DcMotor leftArm;
    public Servo leftClaw;
    public Servo rightClaw;
    public BNO055IMU imu;
    public ColorSensor colorSensor;
    static final double FORWARD_SPEED = 0.6;
    static final double TURN_SPEED = 0.4;

    // Based on methods found in autobot
    public void runOpMode() {
        // Define and Initialize Motors + color sensor
        FLeft  = hardwareMap.get(DcMotor .class, "FLeft");
        FRight = hardwareMap.get(DcMotor.class, "FRight");
        BLeft = hardwareMap.get(DcMotor.class, "BLeft");
        BRight = hardwareMap.get(DcMotor.class, "BRight");
        colorSensor = hardwareMap.get(ColorSensor .class, "colorSensor");
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        Autobot robot = new Autobot(FLeft, FRight, BLeft, BRight, colorSensor, imu);
//        imu = hardwareMap.get(BNO055IMU.class, "imu");
        Gyro gyro = new Gyro(imu);

        double initialDriveForwardTime = 2;
        //       double secondDriveForwardTime = 3.0;
        double initialGyroValue = gyro.getZ();

        // 1. Drive straight for 2.25
//        robot.driveByRotation(2.25);

//        robot.FLeft.setPower(FORWARD_SPEED);
//        robot.FRight.setPower(FORWARD_SPEED);
//        robot.BLeft.setPower(FORWARD_SPEED);
//        robot.BRight.setPower(FORWARD_SPEED);
//        runtime.reset();
//        while ((runtime.seconds() < initialDriveForwardTime)) {
//            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
//            telemetry.update();
//        }

        // Drive by X rotation
        while (runtime.seconds()<1000) {
            telemetry.addData("encoder", robot.BLeft.getCurrentPosition());
            telemetry.update();
        }

//        // Stop Robot if blue
//        robot.driveToBlue();
//        runtime.reset();
//        // 2. (Grab) Lower Grabber, Pick up block, Lift Grabber
//
//        // 3. Tip back 14
//        robot.tip(14);
//        // 4. Back 0.5
//        robot.driveByRotation(-0.5);
//
        // 4. Turn Left/Right 90 degrees
        // Left
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
 /*      // 4. Move forward 4

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