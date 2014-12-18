configuration Blink {
int c;
}
implementation {
  components Main, BlinkM, SingleTimer, LedsC;
 int c= 2;
int d = 3;
c = d;
  Main.StdControl -> BlinkM.StdControl;
  Main.StdControl -> SingleTimer.StdControl;
  BlinkM.Timer -> SingleTimer.Timer;
  BlinkM.Leds -> LedsC;
}
