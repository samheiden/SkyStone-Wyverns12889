package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@TeleOp(name="MecanumDrive_Record")
public class MecanumDrive_Record extends LinearOpMode {

    // Defining Robot and Constants
    private Robot robot = new Robot();
    private final double theta = Math.PI / 4;
    private final double trans_factor = 1.0;
    private final double turn_factor = 1.0;
    private double click_one_time = 0.0;
    private boolean record_mode = false;
    private byte[] Motor_Array;

    private ElapsedTime time = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            // Axis Rotation
            double x = Math.abs(Math.cos(theta)) * gamepad1.left_stick_x;
            double y = Math.abs(Math.sin(theta)) * (-gamepad1.left_stick_y);
            double x_output = trans_factor * ((x * Math.cos(theta)) + (y * Math.sin(theta)));
            double y_output = trans_factor * ((x * (-Math.sin(theta))) + (y * Math.cos(theta)));
            // Get Turn Input
            double turn = turn_factor * (gamepad1.right_trigger - gamepad1.left_trigger);
            // Apply Outputs
            robot.frontLeft.setPower(x_output + turn);
            robot.backLeft.setPower(y_output + turn);
            robot.frontRight.setPower(y_output - turn);
            robot.backRight.setPower(x_output - turn);

            if (record_mode)
                try {
                    FileOutputStream FileOut = new FileOutputStream("OutputRecord_0.dat");
                    DataOutputStream DataOut = new DataOutputStream(FileOut);
                    try{
                        try {
                            DataOut.writeDouble(robot.frontLeft.getPower());
                            DataOut.writeDouble(robot.backLeft.getPower());
                            DataOut.writeDouble(robot.frontRight.getPower());
                            DataOut.writeDouble(robot.backRight.getPower());
                            DataOut.writeDouble(time.milliseconds());
                            DataOut.writeChars("\n");
                            Thread.sleep(20);
                        } catch(IOException notFound) {

                        }
                    } finally{
                        try {
                            FileOut.close();
                        } catch(IOException notFound) {

                        }
                    }
                }
                catch(FileNotFoundException notFound){
                }
        }
    }
}
