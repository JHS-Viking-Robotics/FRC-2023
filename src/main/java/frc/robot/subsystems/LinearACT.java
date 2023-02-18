package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;

public class LinearACT extends Servo{
 public static final String Dontmove = null;
public LinearACT(int channel) {
        super(channel);
        //TODO Auto-generated constructor stub
    }
 

 int m_speed;
 int m_length;
 int setPos;
 int curPos;
 /**
 * Parameters for L16-R Actuonix Linear Actuators
 *
 * @param channel PWM channel used to control the servo
 * @param speed max speed of the servo [mm/second]
 */
 public LinearACT(int channel, int speed) {
 super(channel);
 setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
 m_speed = speed;
 }
 //extends linear actuator
 public void extend() {
    m_speed= 32;
  }
  //retracts linear actuator
  public void retract() {
    m_speed= -32;
  }
  //stops linear actuator from moving
  public void Dontmove() {
    m_speed= 0;
  }
}