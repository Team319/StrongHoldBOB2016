// Strong Hold BOB 2016 OI

package org.usfirst.frc319;

import org.usfirst.frc319.commands.*;
import org.usfirst.frc319.commands.arm.ArmGoToCollect;
import org.usfirst.frc319.commands.arm.ArmGoToShootUnderTower;
import org.usfirst.frc319.commands.arm.ArmGoToStorage;
import org.usfirst.frc319.commands.auto.DriveAutoSpline;
import org.usfirst.frc319.commands.auto.LowBarAuto;
import org.usfirst.frc319.commands.camera.CameraDrive;
import org.usfirst.frc319.commands.collector.CollectAndStop;
import org.usfirst.frc319.commands.collector.CollectorIn;
import org.usfirst.frc319.commands.collector.CollectorOut;
import org.usfirst.frc319.commands.collector.CollectorStop;
import org.usfirst.frc319.commands.collector.LoadBoulder;
import org.usfirst.frc319.commands.shooter.ShooterGo;
import org.usfirst.frc319.commands.commandGroups.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;

import org.usfirst.frc319.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {    
    public BobController driverController;
    public BobController operatorController;  
    
    public OI() {

    	driverController = new BobController(0);
    	driverController.startButton.whenPressed(new FollowBothMotionProfiles());
    	driverController.leftBumper.whenPressed(new CollectAndStop());
    	driverController.rightBumper.whenPressed(new SpeedUpThenShoot());
    	driverController.bButton.whenPressed(new CollectorAndShooterStop());
    	driverController.yButton.whenPressed(new ShooterGo());
    	driverController.aButton.whenPressed(new ArcadeDrive());
    	driverController.xButton.whenPressed(new ShiftToggle());

    	
    	operatorController = new BobController(1);
    	operatorController.selectButton.whenPressed(new LoadBoulder());
    	operatorController.yButton.whenPressed(new ArmGoToShootUnderTower());
    	operatorController.xButton.whenPressed(new ArmGoToStorage());
    	operatorController.bButton.whenPressed(new ArmGoToCollect());  
    	operatorController.rightBumper.whenPressed(new CollectorIn());
    	operatorController.leftBumper.whenPressed(new CollectorOut());
    	operatorController.aButton.whenPressed(new CollectorStop());
    	
      //  operatorController.startButton.whenPressed(new ExampleCommandGroup());
    	
    	//MIKE'S CODE
    	/*driverController.selectButton.whenPressed(new DriveAutoSpline());
    	driverController.leftTriggerButton.whileHeld(new ShooterGo());
    	driverController.rightTriggerButton.whileHeld(new CollectorIn());
    	driverController.leftBumper.whileHeld(new CollectorOut());
    	driverController.rightBumper.whileHeld(new CollectorIn());
    	driverController.xButton.whenPressed(new ArmGoToShootUnderTower());
    	driverController.bButton.whenPressed(new ArmGoToCollect()); 
    	driverController.aButton.whenPressed(new ArmGoToStorage());
    	*/
    }

    public Joystick getDriverController() {
        return driverController;
        
    }

}

