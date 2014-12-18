
// NODES

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
  // bandX = TRUE = receiving messages of the beaconX, = FALSE = oppositive
  bool flag0 = FALSE;
  bool flag1 = FALSE;
  bool flag2 = FALSE;
  bool flag3 = FALSE;
  uint8_t counter=0;
  uint8_t positionX=0;
  uint8_t positionY=0;
  uint8_t positionZ=0;
  uint8_t Beacon0X = 0;
  uint8_t Beacon0Y = 0;
  uint8_t Beacon0Z = 0;
  uint8_t Beacon1X = 0;
  uint8_t Beacon1Y = 0;
  uint8_t Beacon1Z = 0;
  uint8_t Beacon2X = 0;
  uint8_t Beacon2Y = 0;
  uint8_t Beacon2Z = 0;
  uint8_t Beacon3X = 0;
  uint8_t Beacon3Y = 0;
  uint8_t Beacon3Z = 0;
  
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
      if (call AMSend.send(AM_BROADCAST_ADDR, &packet, sizeof(radio_count_msg_t)) == SUCCESS) {	
	locked = TRUE;
      }
    }
  }

  // When receive it calculates its position (positionX, positionY and positionZ)
  event message_t* Receive.receive(message_t* bufPtr,void* payload, uint8_t len) {
    if (len != sizeof(radio_count_msg_t)) {return bufPtr;}
    else {
      radio_count_msg_t* rcm = (radio_count_msg_t*)payload;
      if (rcm->beaconID == 0) {
        Beacon0X = rcm->positionX;
        Beacon0Y = rcm->positionY;
        Beacon0Z = rcm->positionZ;
        counter++;
        flag0 = TRUE;	
      }
      if (rcm->beaconID == 1) {
        Beacon1X = rcm->positionX;
        Beacon1Y = rcm->positionY;
        Beacon1Z = rcm->positionZ;
        counter++;
        flag1 = TRUE;	
      }
      if (rcm->beaconID == 2) {
        Beacon2X = rcm->positionX;
        Beacon2Y = rcm->positionY;
        Beacon2Z = rcm->positionZ;
        counter++;
        flag2 = TRUE;	
      }
      if (rcm->beaconID == 3) {
        Beacon3X = rcm->positionX;
        Beacon3Y = rcm->positionY;
        Beacon3Z = rcm->positionZ;
        counter++;
        flag3 = TRUE;	
      }

      // formula to calculate the position of node
      positionX = (Beacon0X + Beacon1X + Beacon2X + Beacon3X) / counter;
      positionY = (Beacon0Y + Beacon1Y + Beacon2Y + Beacon3Y) / counter;
      positionZ = (Beacon0Z + Beacon1Z + Beacon2Z + Beacon3Z) / counter;

      return bufPtr;
    }
  }//Receive.receive

  event void AMSend.sendDone(message_t* bufPtr, error_t error) {
    if (&packet == bufPtr) {
      locked = FALSE;
    }
  }

}

