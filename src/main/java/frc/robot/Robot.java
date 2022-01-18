// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import com.revrobotics.CANSparkMax; 
import com.revrobotics.CANSparkMaxLowLevel.MotorType; //Low level type is needed to refer to which type of motor it is (brushed/brushless)

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private Joystick m_leftStick;
  //private Joystick m_rightStick; This was commented out to switch to arcade drive

  private final MotorController m_leftMotor = new CANSparkMax(1, MotorType.kBrushed);
  private final MotorController m_rightMotor = new CANSparkMax(2, MotorType.kBrushed);
  private final MotorController m_testMotor = new PWMSparkMax(1); 

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightMotor.setInverted(true);

    m_myRobot = new DifferentialDrive(m_leftMotor, m_rightMotor);
    m_leftStick = new Joystick(0);
    //m_rightStick = new Joystick(1); //This would require the other left joystick to move the robot. I'd like to figure out a system in which we can get drive on two controller joysticks.
  }

  @Override
  public void teleopPeriodic() {
    m_myRobot.tankDrive(m_leftStick.getY(), m_leftStick.getX()); //by altering this code, it became arcade drive rather than tank (initially value was m_rightStick.getY())
  }
}


 /**
 *  Issues with motor controllers largely kept this code from working. ISSUE FIX:
 * 1. Rev client must be installed, and the robot turned off. After this, user will connect to the SparkMax directly with a USB-C cord
 * 2. A variety of things were tried here. First changing to factory settings. This proved unhelpful later on, but was a start
 * Another thing that was tested was the firmware (ensure it is updated to the most current version)
 * 3. In this case, the motors needed to be brushed (default was brushless, but BuddyBot runs on brushed motors), 
 * a setting which can be altered within the REV client. We also changed sensor settings to "No Sensor", as it was looking
 * for an encoder that was not there (indicated by the sensor fault error-- blinking orange and magenta.)
 * 4. Once any settings are changed push "Burn Flash". This saves the setting to the motors and ensures it will last following
 * a power cycle. Repeat with other motor controllers acting up. 
 * THERE'S A GOOGLE DOCS WITH THIS ISSUE AND OTHERS. GO THERE
 */
