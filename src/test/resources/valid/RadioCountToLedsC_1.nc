 
// BEACONES

#include "Timer.h"
#include "RadioCountToLeds.h"
#include "CC2420.h"
#include "IEEE802154.h"
 

module RadioCountToLedsC @safe() {
  uses {
    interface Leds;
    interface Boot;
    interface Receive;
    interface AMSend;
    interface Timer<TMilli> as MilliTimer;
    interface SplitControl as AMControl;
    interface Packet;
  }
}
implementation {

  message_t packet;
  bool locked;
  uint8_t positionX = 0;
  uint8_t positionY = 0;
  uint8_t positionZ = 0;
  uint8_t beaconID =10;

  event void Boot.booted() {
    call AMControl.start();
  }

  event void AMControl.startDone(error_t err) {
    if (err == SUCCESS) {
      call MilliTimer.startPeriodic(TIMER);
    }
    else {
      call AMControl.start();
    }
  }

  event void AMControl.stopDone(error_t err) {
    // do nothing
  }
  
  event void MilliTimer.fired() {
    dbg("Position X is %hu.\n", positionX);
    if (locked) {
      return;
    }
    else {
      radio_count_msg_t* rcm = (radio_count_msg_t*)call Packet.getPayload(&packet, sizeof(radio_count_msg_t));
      if (rcm == NULL) {
	return;
      }

      rcm->positionX = positionX;
      rcm->positionY = positionY;
      rcm->positionZ = positionZ;
      rcm->beaconID = beaconID;
      if (call AMSend.send(AM_BROADCAST_ADDR, &packet, sizeof(radio_count_msg_t)) == SUCCESS) {	
	dbg("Packet sent.\n");
	locked = TRUE;
      }
    }
  }

  event message_t* Receive.receive(message_t* bufPtr,void* payload, uint8_t len) {
    dbg("Received packet of length %hhu.\n", len);
    if (len != sizeof(radio_count_msg_t)) {return bufPtr;}
  }

  event void AMSend.sendDone(message_t* bufPtr, error_t error) {
    if (&packet == bufPtr) {
      locked = FALSE;
    }
  }

}

