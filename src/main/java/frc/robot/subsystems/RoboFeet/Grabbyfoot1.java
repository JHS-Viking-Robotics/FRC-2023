package frc.robot.subsystems.RoboFeet;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.math.MathUtil;


public class Grabbyfoot1 extends Servo{
 public Grabbyfoot1(int channel) {
        super(channel);
        //TODO Auto-generated constructor stub
    }

 double m_speed;
 double m_length;
 double setPos;
 double curPos;
 /**
 * Parameters for L16-R Actuonix Linear Actuators
 *
 * @param channel PWM channel used to control the servo
 * @param length max length of the servo [mm]
 * @param speed max speed of the servo [mm/second]
 */
 public Grabbyfoot1(int channel, int length, int speed) {
 super(0);
 setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
 m_length = length;
 m_speed = speed;
 }
 /** Run this method in any periodic function to update the position estimation of your
servo
 *
 * @param setpoint the target position of the servo [mm]
 */
 public void setPosition(double setpoint){
 setPos = MathUtil.clamp(setpoint, 0, m_length);
 setSpeed( (setPos/m_length *2)-1);
 }
 double lastTime = 0;
 /**
 * Run this method in any periodic function to update the position estimation of your
servo
 */
 public void updateCurPos(){
 double dt = Timer.getFPGATimestamp() - lastTime;
 if (curPos > setPos + m_speed *dt){
 curPos -= m_speed *dt;}
else if(curPos < setPos - m_speed *dt){
 curPos += m_speed *dt;}
 else{
 curPos = setPos;}
 }
}