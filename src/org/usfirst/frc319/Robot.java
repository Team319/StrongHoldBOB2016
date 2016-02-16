
// Strong Hold BOB 2016 Robot


package org.usfirst.frc319;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc319.commands.*;
import org.usfirst.frc319.subsystems.*;
import org.usfirst.frc319.motionProfiles.*;
import org.usfirst.frc319.RightMotionProfile;

import com.team319.web.LoggerServer;
import com.team319.web.trajectory.client.TrajectoryClient;

/*--added a package to put all of our Motion Profiles so they don't--
    clutter up the src package (Derrick 2/3/16)
*/

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;

    public static OI oi;

    public static DriveTrain driveTrain;
    public static Collector collector;
    public static Shooter shooter;
    public static Arm arm;
    public static Climber climber;
    public static TowerCamera towerCamera;
    public static Pneumatics compressor;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    public void robotInit() {
    RobotMap.init();
        
        driveTrain = new DriveTrain();
        collector = new Collector();
        shooter = new Shooter();
        arm = new Arm();
        climber = new Climber();
        //towerCamera = new TowerCamera();
        compressor = new Pneumatics();
        
        oi = new OI();

        autonomousCommand = new AutonomousCommand();
        
        //LoggerServer.startServer();
        
        try {
        	// this is the trajectory server url
			TrajectoryClient.start("10.3.19.191");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("Left Shooter Speed", Robot.shooter.getLeftShooterSpeed());
        SmartDashboard.putNumber("Right Shooter Speed", Robot.shooter.getRightShooterSpeed());
        SmartDashboard.putBoolean("Gear", Robot.driveTrain.shift);
        //SmartDashboard.putBoolean("bouldersensor",Robot.collector.getBoulderSensor());
        //SmartDashboard.putInt("Left Drivetrain Encoder Position (revs)", Robot.driveTrain.getLeftDrivetrainPosition());
        SmartDashboard.putDouble("Average Boulder IR Sensor ", Robot.collector.getAverageLeftAndRightBoulderIRSensor());
        SmartDashboard.putInt("Left Boulder IR Sensor ", Robot.collector.getleftBoulderIRSensor());
        SmartDashboard.putInt("Right Boulder IR Sensor ", Robot.collector.getrightBoulderIRSensor());
        
        
        SmartDashboard.putInt("arm position",Robot.arm.getArmPosition());
        Robot.driveTrain.controlRightMotionProfile();
        Robot.driveTrain.controlLeftMotionProfile();
        SmartDashboard.putInt("Right Drivevtrain Encoder Position (revs)", Robot.driveTrain.getRightDrivetrainPosition());
        
        
        //-----attempting to put a string into smartdashboard to out put high/low instead of red/green - Derrick 1/29/16 - LOW priority
        /*if(Robot.driveTrain.shift){
        	SmartDashboard.putString("Gear", Robot.driveTrain.highGear);
        
        }else{
        	SmartDashboard.putString("Gear", Robot.driveTrain.lowGear);
        }
        // SmartDashboard.putString("Gear", value);
  		*/
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
