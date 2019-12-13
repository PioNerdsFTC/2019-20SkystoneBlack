package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

// Source: HardwarePushbot

public class Autobot {
    /* Public OpMode members. */
    public static final double MID_SERVO       =  0.5 ;
    public static final double ARM_UP_POWER    =  0.45 ;
    public static final double ARM_DOWN_POWER  = -0.45 ;

    public DcMotor  FLeft;
    public DcMotor  FRight;
    public DcMotor  BLeft;
    public DcMotor  BRight;
    public DcMotor  leftArm;
    public Servo leftClaw;
    public Servo    rightClaw;
    public BNO055IMU imu;
    public ColorSensor colorSensor;
    static final double FORWARD_SPEED = 0.3;
    static final double TURN_SPEED = 0.2;
    int rotation, startPosition;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public Autobot(DcMotor FLeftIn, DcMotor FRightIn, DcMotor BLeftIn, DcMotor BRightIn, ColorSensor colorSensorIn, BNO055IMU imuIn) {
        FLeft = FLeftIn;
        FRight = FRightIn;
        BLeft = BLeftIn;
        BRight = BRightIn;
        colorSensor = colorSensorIn;
        imu = imuIn;

//        leftArm = hwMap.get(DcMotor.class, "left_arm");
        FLeft.setDirection(DcMotor.Direction.FORWARD);
        FRight.setDirection(DcMotor.Direction.REVERSE);
        BLeft.setDirection(DcMotor.Direction.FORWARD);
        BRight.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power
        FLeft.setPower(0);
        FRight.setPower(0);
        BLeft.setPower(0);
        BRight.setPower(0);
//        leftArm.setPower(0);
    }

    public void driveToBlue() {
        FLeft.setPower(FORWARD_SPEED);
        FRight.setPower(FORWARD_SPEED);
        BLeft.setPower(FORWARD_SPEED);
        BRight.setPower(FORWARD_SPEED);
        if (colorSensor.blue() > 500) {
            FLeft.setPower(0);
            FRight.setPower(0);
            BLeft.setPower(0);
            BRight.setPower(0);
        }
    }

    public void driveToRed() {
        FLeft.setPower(FORWARD_SPEED);
        FRight.setPower(FORWARD_SPEED);
        BLeft.setPower(FORWARD_SPEED);
        BRight.setPower(FORWARD_SPEED);
        if (colorSensor.red() > 500) {
            FLeft.setPower(0);
            FRight.setPower(0);
            BLeft.setPower(0);
            BRight.setPower(0);
        }
    }

    public void grab() {
        // Grabber grab method
    }



    public void driveByRotation(double rotation) {
        // Drive by rotations (inches)
        startPosition = BLeft.getCurrentPosition();

        while (startPosition < rotation) {
            startPosition = BLeft.getCurrentPosition();
            FLeft.setPower(FORWARD_SPEED);
            FRight.setPower(FORWARD_SPEED);
            BLeft.setPower(FORWARD_SPEED);
            BRight.setPower(FORWARD_SPEED);
        }
        FLeft.setPower(0);
        FRight.setPower(0);
        BLeft.setPower(0);
        BRight.setPower(0);
    }

    /* Initialize standard Hardware interfaces */
/*    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

//        leftArm    = hwMap.get(DcMotor.class, "left_arm");
        FLeft.setDirection(DcMotor.Direction.FORWARD);
        FRight.setDirection(DcMotor.Direction.REVERSE);
        BLeft.setDirection(DcMotor.Direction.FORWARD);
        BRight.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power
        FLeft.setPower(0);
        FRight.setPower(0);
        BLeft.setPower(0);
        BRight.setPower(0);
//        leftArm.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.

//        leftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.
//        leftClaw  = hwMap.get(Servo.class, "left_hand");
//        rightClaw = hwMap.get(Servo.class, "right_hand");
//        leftClaw.setPosition(MID_SERVO);
//        rightClaw.setPosition(MID_SERVO);


    } */

}
