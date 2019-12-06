package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class Grabber {

    Servo elbow;
    public double current;
    double nudge = 0.025;
    double holdCurrentAdd = 1/((double)5000); // 40 nudges to full range, 20000 loops through code per second, lift arm in 4 seconds did the math for nudges per loop
    Servo wrist;
    DcMotorSimple elevator;
    DcMotor elevatorEncoder;
    double[] elevatorScale = {0,-250};
    Servo holder;
    DcMotorSimple tilter;
    DcMotor tilterEncoder;
    double tiltScale[]; // = {,}
    double defaultTilterSpeed = 0.25;


    public Grabber(Servo elbowIn, Servo wristIn, DcMotorSimple tilerIn, DcMotor tilterEncoderIn, DcMotorSimple elevatorIn, DcMotor elevatorEncoderIn, Servo holderIn){
        elbow = elbowIn;
        wrist = wristIn;
        tilter = tilerIn;
        tilterEncoder = tilterEncoderIn;
//        tilter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevator = elevatorIn;
        elevatorEncoder = elevatorEncoderIn;
        holder = holderIn;
        current = 0;
    }


    public double moveElbowUp(){
        current += holdCurrentAdd;
        if(current > nudge) {
            setElbow(getElbow() + nudge);
            current = 0;
        }
        return current;
    }
    public double moveElbowDown(){
        current -= holdCurrentAdd;
        if(current < -nudge) {
            setElbow(getElbow() - nudge);
            current = 0;
        }
        return current;
    }
    public void setElbow(double elbowPos){
        elbow.setPosition(elbowPos);
        //4
//        setWrist(1 - elbowPos);//Suplamentary
    }
    public double getElbow(){
        return elbow.getPosition();
    }



    public void setWrist(double wristPos){
        wrist.setPosition(wristPos);
    }
    public double getWrist(){
        return wrist.getPosition();
    }



    public void openHolder(){
        setHolder(1.0);
    }
    public void closeHolder(){
        setHolder(0.0);
    }
    public void setHolder(double holderPos){
        holder.setPosition(holderPos);
    }
    public double getHolder(){
        return holder.getPosition();
    }



    public void setElevatorSpeed(double elevatorSpeedIn){
        ///*
        //8
        if(elevatorSpeedIn > 0){
            if(getElevator() <= elevatorScale[1]){
                stopElevator();
                return;
            }
        }
        if(elevatorSpeedIn < 0){
            if(getElevator() >= elevatorScale[0]){
                stopElevator();
                return;
            }
        }
        //*/
        elevator.setPower(elevatorSpeedIn);
    }
    public void stopElevator(){
        setElevatorSpeed(0);
    }
    public double getElevator(){
        return elevatorEncoder.getCurrentPosition();
    }



    public void driveMotorToEncoder(DcMotorSimple motor, DcMotor motorEncoder, double encoderValue, double motorSpeed){
        double motorPos = motorEncoder.getCurrentPosition();
        if(encoderValue > motorPos){
            while (encoderValue > motorPos){
                motor.setPower(motorSpeed);
            }
        }else{
            while (encoderValue < motorPos){
                motor.setPower(-motorSpeed);
            }
        }
    }


    /*
    //10
    public void tiltUnderBridge(){
        driveMotorToEncoder(tilter, tiltScale[1], defaultTilterSpeed);
        //RETRACT ELIVATOR
        stopTilter();
    }
    public void tiltBack(){
        driveMotorToEncoder(tilter, tiltScale[0], defaultTilterSpeed);
        stopTilter();
    }
    //*/
    public void setTilter(double speed){
        tilter.setPower(speed);
    }
    public double getTilter(){
        return tilterEncoder.getCurrentPosition();
    }
    public void stopTilter(){
        tilter.setPower(0);
    }


}
