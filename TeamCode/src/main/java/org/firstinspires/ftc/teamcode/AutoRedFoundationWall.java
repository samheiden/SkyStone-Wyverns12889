package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="AutoRedFoundationWall")
public class AutoRedFoundationWall extends LinearOpMode {
    Argorok argorok = new Argorok();

    @Override
    public void runOpMode() throws InterruptedException {
        Control control = new Control(argorok);
        control.init(hardwareMap);
        waitForStart();

        control.autoRunMecanumOfTheAncients(0,0.5,0,250);
        control.autoRunMecanumOfTheAncients(0,0,1,753);
        control.autoLift(1,250);
        control.autoCloseClamp();
        control.autoRunMecanumOfTheAncients(0.25,0.55,0,2000);
        control.autoLift(-1,250);
        control.autoOpenClamp();
        control.autoRunMecanumOfTheAncients(0.1,-1,0,1700);
        control.autoLift(1,350);
        control.autoRunMecanumOfTheAncients(0,0.5,0,100);
        control.autoRunMecanumOfTheAncients(-0.6,0,0,1200);
        control.autoLift(-1,300);
        control.autoRunMecanumOfTheAncients(-0.6,0,0,1400);
    }
}