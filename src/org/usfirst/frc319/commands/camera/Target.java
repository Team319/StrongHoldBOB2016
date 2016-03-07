package org.usfirst.frc319.commands.camera;

public class Target {
	private double horizontalOffset = 0;
	private double verticalOffset = 0;
	private double distance = 0;


	public Target(double horizontalOffset, double verticalOffset, double distance){
		this.verticalOffset = verticalOffset;
		this.horizontalOffset = horizontalOffset;
		this.distance = distance;
	}

	public double getHorizontalOffset() {
		return horizontalOffset;
	}

	public double getVerticalOffset() {
		return verticalOffset;
	}

	public double getDistance() {
		return distance;
	}
}
