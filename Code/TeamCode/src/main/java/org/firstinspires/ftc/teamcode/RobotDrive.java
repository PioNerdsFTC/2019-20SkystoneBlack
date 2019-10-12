package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class RobotDrive {

    public RobotDrive(){
    }

    public void mecanumDrive(double joyX, double joyY, double joyZ, DcMotor[] wheels){
        double[] wheelValues = new double[4];

        //This is the Calculations for mecanum drive

        //This was found by seeing which directions the wheels
        //needed to rotate to get a net positive movement in each axis.

        //If you have any questions, ask Ryan, he has a sheet that can explain it.
        wheelValues[0] =  joyX + joyY +  joyZ; //FL
        wheelValues[1] = -joyX + joyY + -joyZ; //FR
        wheelValues[2] = -joyX + joyY +  joyZ; //BL
        wheelValues[3] =  joyX + joyY + -joyZ; //BR

        //Make sure the values for each wheels are withing the range [-1,1]
        //Also make sure they are all scaled appropriately
        wheelValues = normalize(wheelValues);

        setWheelSpeeds(wheelValues,wheels);
    }

    private double[] normalize(double[] values){
        double[] newValues = new double[values.length];
        double max = max(values);
        for(int i = 0; i < values.length; i++){
            //For each value, scale it based of the max
            newValues[i] = values[i]/max;
        }
        return newValues;
    }

    private double max(double[] values){
        double max = Math.abs(values[0]);
        //I use abs() because this will get the correct largest value
        //and allow for the scaling division to preserve the sign of the value
        for(int i = 1; i < values.length; i++){
            if(Math.abs(values[i]) > max){
                max = Math.abs(values[i]);
            }
        }
        return max;
    }

    private void setWheelSpeeds(double[] wheelSpeeds, DcMotor[] wheels){
        for(int i = 0; i < 4; i++){
            //Set the speed of the Wheels
            if(i % 2 == 0){
                //This inverts the Left side wheels because when they spin clockwise that is backwards while the right side clockwise is forwards
                wheels[i].setPower(-wheelSpeeds[i]);
            }else{
                wheels[i].setPower(wheelSpeeds[i]);
            }
        }
    }
}
