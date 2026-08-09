package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Square Auto")
public class SquareAuto extends LinearOpMode {
    @Override
    public void runOpMode() {
        Follower follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(48, 48, 0));
        follower.setMaxPower(0.6); // slower to prevent drift
        PathChain square = follower.pathBuilder()
                .addPath(new BezierLine(new Pose(48, 48), new Pose(96, 48)))
                .setConstantHeadingInterpolation(0)
                .addPath(new BezierLine(new Pose(96, 48), new Pose(96, 96)))
                .setConstantHeadingInterpolation(Math.PI / 2)
                .addPath(new BezierLine(new Pose(96, 96), new Pose(48, 96)))
                .setConstantHeadingInterpolation(Math.PI)
                .addPath(new BezierLine(new Pose(48, 96), new Pose(48, 48)))
                .setConstantHeadingInterpolation(-Math.PI / 2)
                .build();
        telemetry.addData("status", "ready");
        telemetry.update();
        waitForStart();
        follower.followPath(square);
        while (opModeIsActive() && follower.isBusy()) {
            follower.update();
        }
        while (opModeIsActive()) {
            follower.update();
        }
    }
}