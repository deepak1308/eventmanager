package deepak1308.eventmanager;

import java.io.PrintStream;

public class ReportTimeout implements Runnable {	
	private EventManager eventManager;
	private long idleTimeout;
	
	public ReportTimeout(EventManager eventManager, long idleTimeout) {
		this.eventManager = eventManager;
		this.idleTimeout = idleTimeout;
	}
	
	@Override
    public void run() {
		PrintStream logger = eventManager.getOutputStream();
    	long startTime=System.currentTimeMillis(); 
    	long endTime=System.currentTimeMillis();
        
    	while(endTime - startTime <= idleTimeout) {
    	  if(eventManager.getEventsMap().size() > 0) {
    		  /*
    		   * Reset start-time if some active events still there,
    		   * and end-time is always updated to latest, so if there
    		   * has been no active events for event-manager for 
    		   * specified idle seconds, it will time-out.
    		   */
    		  startTime=System.currentTimeMillis();
    	  }
    	  endTime=System.currentTimeMillis();
          try {
            Thread.sleep(2000);
          } catch (InterruptedException ie) {
        	  logger.println("EventManager ReportTimeout thread interrupted");
          }
    	}
    	
        eventManager.setIdle(true);
        logger.println("Exit timeout: event manager has been idle without any activity for specified time");
        
    }
}