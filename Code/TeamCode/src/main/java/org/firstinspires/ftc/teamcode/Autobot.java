package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;

// Source: HardwarePushbot

public class Autobot {
    /* Public OpMode members. */
    public DcMotor  FLeft   = null;
    public DcMotor  FRight  = null;
    public DcMotor  BLeft    = null;
    public DcMotor  BRight   = null;
    public DcMotor  leftArm     = null;
    public Servo    leftClaw    = null;
    public Servo    rightClaw   = null;
    public ModernRoboticsI2cGyro imu = null;
    public ColorSensor colorSensor;


    public static final double MID_SERVO       =  0.5 ;
    public static final double ARM_UP_POWER    =  0.45 ;
    public static final double ARM_DOWN_POWER  = -0.45 ;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public Autobot() {

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors + color sensor
        FLeft  = hwMap.get(DcMotor.class, "FLeft");
        FRight = hwMap.get(DcMotor.class, "FRight");
        BLeft = hwMap.get(DcMotor.class, "BLeft");
        BRight = hwMap.get(DcMotor.class, "BRight");
        colorSensor = hwMap.get(ColorSensor.class, "colorSensor");

       // imu = hwMap.get(ModernRoboticsI2cGyro.class, "imu");
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
        FLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        leftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.
//        leftClaw  = hwMap.get(Servo.class, "left_hand");
//        rightClaw = hwMap.get(Servo.class, "right_hand");
//        leftClaw.setPosition(MID_SERVO);
//        rightClaw.setPosition(MID_SERVO);
    }
}
