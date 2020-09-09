package deepak1308.eventmanager;

import java.io.PrintStream;
import java.util.HashMap;

public class ReportStatus implements Runnable {
	private EventManager eventManager;
	
	public ReportStatus(EventManager eventManager) {
		this.eventManager = eventManager;
	}
	
	@Override
    public void run() {
		PrintStream logger = eventManager.getOutputStream();
		
		while(!eventManager.isIdle()) {
    	  try {
    		/*
    		 * Report status of active events periodically.  
    		 */
    		HashMap<Long, String> eventsMap = eventManager.getEventsMap();
    		if(eventsMap.size() > 0) {
    			logger.println("Following events are active:");	
    		  for(Long eventId : eventsMap.keySet()) {
    			  logger.println("Event-id: " + eventId);
    			  logger.println("Event-name: " + eventsMap.get(eventId));
    		  }
    		}
    		else {
    			logger.println("No active events");	
    		}
    		
            Thread.sleep(500);
          } catch (InterruptedException ie) {
        	  logger.println("EventManager ReportStatus thread interrupted");
          }
		}       
    }
}