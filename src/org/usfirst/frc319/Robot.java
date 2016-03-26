
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
public class Robot extends IterativeRobot implements IAutoConfigChangeListener{

    Command autonomousCommand;
    SendableChooser autoChooser;
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
    
    private SelectedAuto selectedAuto = null;

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
        
        
      /*
        autoChooser.addDefault("Low Bar High Goal auto", new LowBarHighGoalAuto());
        autoChooser.addObject("Various defenses position 2", new VariousDefencesAutoWeekZero());
        autoChooser.addObject("Various defenses position 3", new VariousDefencesAutoWeekZero());
        SmartDashboard.putData("autonomousModeChooser", autoChooser);
        */
        
        //for now -WEEK ZERO--- this is where you set your auto command.
       // autonomousCommand = new Position3VariousAuto();
        
        try{
       // LoggerServer.startServer();
        	String ip = "10.3.19.19";
	        WaypointClient.start(ip);
	        TrajectoryClient.start(ip);
	        PidClient.start(ip);
	        TargetClient.start(ip);
	        //TrajectoryProgressClient.start("10.3.19.21");
	        /**
	        //AutoMapFactory.getInstance().buildMap();
	        AutoModes autoModes = new AutoModes();
	        autoModes.setDefenses(AutoDictionary.getDefenses());
	        autoModes.setModes(AutoDictionary.getModes());
	        autoModes.setPositions(AutoDictionary.getPositions());
	        AutoManager.getInstance().setModes(autoModes);
	        AutoManager.getInstance().registerListener(this);
	        AutoClient.start(ip);
	        **/
        
        }catch(Exception e){
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
        Robot.arm.updateArmPosition();
    }

    public void autonomousInit() {
       //autonomousCommand = new LowBarHighGoalAuto();
    	autonomousCommand = new Position3VariousAuto();//(Command) autoChooser.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
        //Robot.driveTrain.setDTEncodersToZero();
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
        //SmartDashboard.putDouble("Average Boulder IR Sensor ", Robot.collector.getAverageLeftAndRightBoulderIRSensor());
        SmartDashboard.putNumber("Left Boulder IR Sensor ", Robot.collector.getleftBoulderIrSensorAverageVoltage());
        SmartDashboard.putNumber("Right Boulder IR Sensor ", Robot.collector.getrightBoulderIrSensorAverageVoltage());
        SmartDashboard.putDouble("Gyro Angle", Robot.driveTrain.getGyroAngle());
        
        
        SmartDashboard.putInt("arm position",Robot.arm.getArmPosition());
        Robot.driveTrain.controlRightMotionProfile();
        Robot.driveTrain.controlLeftMotionProfile();
        SmartDashboard.putInt("Right Drivevtrain Encoder Position (revs)", Robot.driveTrain.getRightDrivetrainPosition());
        SmartDashboard.putInt("Left Drivevtrain Encoder Position (revs)", Robot.driveTrain.getLeftDrivetrainPosition());
        
        SmartDashboard.putBoolean("Right drivetrain is alive?", Robot.driveTrain.rightDriveLeadStatus());
        SmartDashboard.putBoolean("Left drivetrain is alive?", Robot.driveTrain.leftDriveLeadStatus());
        
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
    
    @Override
    public void onChange(AutoConfig auto) {
    	// TODO Auto-generated method stub
    	
    	if(auto.getSelectedAuto().equals(selectedAuto)){
    		//the auto hasn't changed
    		return;
    	}
    	
    	Command builtAuto = AutoMapFactory.getInstance().buildAuto(auto.getSelectedAuto());
    	
    	if(builtAuto == null){
    		autonomousCommand = null;
    		AutoManager.getInstance().throwAutoConfigException(new AutoConfigException(AutoConfigException.UNDEFINED_AUTO));
    	}else{
    		selectedAuto = auto.getSelectedAuto();
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
