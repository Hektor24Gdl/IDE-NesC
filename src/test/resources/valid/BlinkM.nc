module BlinkM {
  provides {

    interface StdControl;
  }
  uses {
    interface Timer;
    interface Leds;
  }
}// this is a line comment
implementation {

  command result_t StdControl.init() {
    call Leds.init();
    return SUCCESS;
  }

  command result_t StdControl.start() {
    return call Timer.start(TIMER_REPEAT, 1000) ;
  }

  command result_t StdControl.stop() {
    return call Timer.stop();
  }

  event result_t Timer.fired()
  {
    call Leds.redToggle();
    return SUCCESS;
  }/*this is a comment*/
}
