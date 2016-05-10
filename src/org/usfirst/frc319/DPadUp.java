package org.usfirst.frc319;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
//import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class DPadUp extends Button{

	private final Joystick xboxController;
	
	public DPadUp(Joystick joystick){
			this.xboxController = joystick;

	}

public boolean get() {
	double Dpad = xboxController.getPOV(); 

	boolean pressed = false;
	
	if(Dpad == 0){
		pressed = true;
	}
	return pressed;
	}

}
