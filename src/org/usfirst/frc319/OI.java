// Strong Hold BOB 2016 OI

package org.usfirst.frc319;

import org.usfirst.frc319.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import org.usfirst.frc319.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    public JoystickButton xButton;
    public JoystickButton yButton;
    public JoystickButton aButton;
    public JoystickButton bButton;
    public JoystickButton rightBumper;
    public JoystickButton leftBumper;
    public JoystickButton startButton;
    public JoystickButton selectButton;
    public Joystick xBoxController;

    public OI() {

        xBoxController = new Joystick(0);
        
        selectButton = new JoystickButton(xBoxController, 7);
        selectButton.whenPressed(new ShooterPIDTest());
        startButton = new JoystickButton(xBoxController, 8);
        startButton.whenPressed(new ShooterStop());
        leftBumper = new JoystickButton(xBoxController, 5);
        leftBumper.whenPressed(new ShooterGo());
        rightBumper = new JoystickButton(xBoxController, 6);
        rightBumper.whenPressed(new ShiftToggle());
        bButton = new JoystickButton(xBoxController, 2);
        bButton.whenPressed(new ClimberStop());
       // aButton = new JoystickButton(xBoxController, 1);
       // aButton.whenPressed(new CollectorIn());
        //aButton is being used for PID shooter Testing (Derrick 1/28/15)
        
        yButton = new JoystickButton(xBoxController, 4);
        yButton.whenPressed(new ShooterStop());
        xButton = new JoystickButton(xBoxController, 3);
        xButton.whenPressed(new ShooterGo());

    }

    public Joystick getxBoxController() {
        return xBoxController;
        
    }

}

