package org.usfirst.frc319;

import org.usfirst.frc319.control.XboxLeftTrigger;
import org.usfirst.frc319.control.XboxRightTrigger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class BobController extends Joystick {

	public JoystickButton xButton = new JoystickButton(this, 3);
    public JoystickButton yButton = new JoystickButton(this, 4);
    public JoystickButton aButton = new JoystickButton(this, 1);
    public JoystickButton bButton = new JoystickButton(this, 2);
    public JoystickButton rightBumper = new JoystickButton(this, 6);
    public JoystickButton leftBumper = new JoystickButton(this, 5);
    public JoystickButton startButton = new JoystickButton(this, 8);
    public JoystickButton selectButton = new JoystickButton(this, 7);
    public JoystickButton leftStickButton = new JoystickButton(this, 9);
    public JoystickButton rightStickButton = new JoystickButton(this, 10);
    
    public Button leftTriggerButton = new XboxLeftTrigger(this);
    public Button rightTriggerButton = new XboxRightTrigger(this);
    
    public DPadUp dPadUp = new DPadUp(this);
    public DPadDown dPadDown = new DPadDown(this);


	public BobController(int joystickNumber)
	{
		super(joystickNumber);
	}

	public double getTriggerTwist()
	{
		double leftTriggerValue =   this.getRawAxis(2);
    	double rightTriggerValue = -1 * this.getRawAxis(3);

    	return leftTriggerValue + rightTriggerValue;

	}

	public double getLeftStickX()
	{
		return this.getRawAxis(0);
	}

	public double getLeftStickY()
	{
		return this.getRawAxis(1);
	}

	public double getRightStickX()
	{
		return this.getRawAxis(4);
	}

	public double getRightStickY()
	{
		return this.getRawAxis(5);
	}

}
