// Strong Hold BOB 2016 Robot


package org.usfirst.frc319;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.*;

import org.usfirst.frc319.commands.*;
import org.usfirst.frc319.commands.auto.AutoDictionary;
import org.usfirst.frc319.commands.auto.AutoMapFactory;
import org.usfirst.frc319.commands.auto.LowBarHighGoalAuto;
import org.usfirst.frc319.commands.auto.LowBarLowGoalAuto;
import org.usfirst.frc319.commands.auto.Position2VariousAuto;
import org.usfirst.frc319.commands.auto.Position3VariousAuto;
import org.usfirst.frc319.commands.auto.VariousDefencesAutoWeekZero;
import org.usfirst.frc319.commands.drivetrain.BuildLeftTowerSpline;
import org.usfirst.frc319.commands.drivetrain.BuildPosition2Spline;
import org.usfirst.frc319.commands.drivetrain.BuildPosition3Spline;
import org.usfirst.frc319.commands.drivetrain.BuildPosition4Spline;
import org.usfirst.frc319.commands.drivetrain.BuildPosition5Spline;
import org.usfirst.frc319.commands.drivetrain.BuildSingleTowerSpline;
import org.usfirst.frc319.commands.drivetrain.DriveStraightSpline;
import org.usfirst.frc319.subsystems.*;
import org.usfirst.frc319.motionProfiles.*;
import org.usfirst.frc319.RightMotionProfile;

import com.team319.auto.AutoConfig;
import com.team319.auto.AutoConfigException;
import com.team319.auto.AutoManager;
import com.team319.auto.AutoModes;
import com.team319.auto.IAutoConfigChangeListener;
import com.team319.auto.SelectedAuto;
import com.team319.auto.web.client.AutoClient;
import com.team319.vision.ITargetListener;
import com.team319.vision.web.client.TargetClient;
import com.team319.web.LoggerServer;
import com.team319.web.config.client.ConfigClient;
import com.team319.web.log.client.LoggerClient;
import com.team319.web.pid.client.PidClient;
import com.team319.web.pid.status.client.PidStatusClient;
import com.team319.web.trajectory.client.TrajectoryClient;
import com.team319.web.trajectory.progress.client.TrajectoryProgressClient;
import com.team319.web.waypoint.client.WaypointClient;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot implements IAutoConfigChangeListener{

    Command autonomousCommand;
    SendableChooser autoChooser; // Do we use this? (5/6/2016)
    public static OI oi;

    public static DriveTrain driveTrain;
    public static Collector collector;
    public static Shooter shooter;
    public static Arm arm;
    public static Climber climber;
    public static TowerCamera towerCamera;
    public static Pneumatics compressor;
    public static BobConstants constants;
    
    public static final int USE_ORANGE_CONSTANTS = 0;
    public static final int USE_BLUE_CONSTANTS = 1;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    public void robotInit() {
    	RobotMap.init();
    	//Change this line depending on which robot you're using
    	constants = new BobConstants(USE_BLUE_CONSTANTS);
        driveTrain = new DriveTrain();
        collector = new Collector();
        shooter = new Shooter();
        arm = new Arm();
        climber = new Climber();
        towerCamera = new TowerCamera();
        compressor = new Pneumatics();
        autoChooser = new SendableChooser();
        
        //always do OI Last
        oi = new OI();
        
        Robot.climber.retractClimber();
       
        /* The code below connects the roborio to a coprocessor which has a web page that runs 
         autonomous selection, vision processing, and trajectory building */
        try{
        	SmartDashboard.putBoolean("Connected to CoPro", false);
        	String ip = "10.3.19.19";
	        WaypointClient.start(ip);
	        TrajectoryClient.start(ip);
	        PidClient.start(ip);
	        TargetClient.start(ip);
	        //TrajectoryProgressClient.start("10.3.19.21");
	        
	        //AutoMapFactory.getInstance().buildMap();
	        AutoModes autoModes = new AutoModes();
	        AutoManager.getInstance().setModes(autoModes);
	        autoModes.setModes(AutoDictionary.getModes());
	        autoModes.setDefenses(AutoDictionary.getDefenses());
	        autoModes.setPositions(AutoDictionary.getPositions());
	        AutoManager.getInstance().registerListener(this);
	        AutoClient.start(ip);
	        
	        SmartDashboard.putBoolean("Connected to CoPro", true);
	        
        
        }catch(Exception e){
        	e.printStackTrace();
        	SmartDashboard.putBoolean("Connected to CoPro", false);
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
        /*The line below calls a method which allows us to move our arm when disabled and continue to change
        the current position of the arm so that when we use hold position can re-enable does not move 
        the arm back to where it was */
        Robot.arm.updateArmPosition(); 
    }

    public void autonomousInit() {
    	// The line below is the default way to choose an autonomous routine instead of through the server
    	//autonomousCommand = new LowBarHighGoalAuto();
        if (autonomousCommand != null) autonomousCommand.start();
        // Make sure the default state of the climber is retracted
        Robot.climber.retractClimber();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        
        if (autonomousCommand != null) autonomousCommand.cancel();
        Robot.shooter.setLeftShooterStop();
        Robot.shooter.setRightShooterStop();
        Robot.climber.retractClimber();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("Left Shooter Speed", Robot.shooter.getLeftShooterSpeed());
        SmartDashboard.putNumber("Right Shooter Speed", Robot.shooter.getRightShooterSpeed());
        SmartDashboard.putBoolean("Gear", Robot.driveTrain.shift);
        //SmartDashboard.putDouble("Average Boulder IR Sensor ", Robot.collector.getAverageLeftAndRightBoulderIRSensor());
        SmartDashboard.putNumber("Left Boulder IR Sensor ", Robot.collector.getleftBoulderIrSensorAverageVoltage());
        SmartDashboard.putNumber("Right Boulder IR Sensor ", Robot.collector.getrightBoulderIrSensorAverageVoltage());
        SmartDashboard.putDouble("Current To Motor", Robot.collector.getCollectorCurrent());
        SmartDashboard.putInt("arm position",Robot.arm.getArmPosition());
        Robot.driveTrain.controlRightMotionProfile();
        Robot.driveTrain.controlLeftMotionProfile();    
        SmartDashboard.putNumber("Target Offset", Robot.driveTrain.getHorizontalOffset()); 
        SmartDashboard.putNumber("tilt angle", Robot.driveTrain.getZAxis());
        SmartDashboard.putNumber("Y axis", Robot.driveTrain.getYAxis());
        
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
   
    }
    
    @Override
    
    // How does this work?
    public void onChange(AutoConfig auto) {
    	
    	Command builtAuto = AutoMapFactory.getInstance().buildAuto(auto.getSelectedAuto());
    	
    	if(builtAuto == null){
    		autonomousCommand = null;
    		AutoManager.getInstance().throwAutoConfigException(new AutoConfigException(AutoConfigException.UNDEFINED_AUTO));
    	}else{
    		autonomousCommand = builtAuto;
    	}
    	
    	/**
    	if (AutoMapFactory.getInstance().hasAuto(auto.getSelectedAuto())){
    		//the autos been defined
    		autonomousCommand = AutoMapFactory.getInstance().getAuto(auto.getSelectedAuto());
    		String position = auto.getSelectedAuto().getSelectedPosition();
    				
			if(position.equalsIgnoreCase(AutoDictionary.POS_POSITION_1)){
				new BuildSingleTowerSpline().buildSpline();
			}else if(position.equalsIgnoreCase(AutoDictionary.POS_POSITION_2)){
				new BuildPosition2Spline().buildSpline();
			}else if(position.equalsIgnoreCase(AutoDictionary.POS_POSITION_3)){
				new BuildPosition3Spline().buildSpline();
			}else if(position.equalsIgnoreCase(AutoDictionary.POS_POSITION_4)){
				new BuildPosition4Spline().buildSpline();
			}else if(position.equalsIgnoreCase(AutoDictionary.POS_POSITION_5)){
				new BuildPosition5Spline().buildSpline();
			}
    	}else{
    		autonomousCommand = null;
    		AutoManager.getInstance().throwAutoConfigException(new AutoConfigException(AutoConfigException.UNDEFINED_AUTO));
    	}
    	**/
    	
    }
    
    @Override
    public void onConfigException(AutoConfigException autoException) {
    	// TODO Auto-generated method stub
    	
    }
}
