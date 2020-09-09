package deepak1308.eventmanager;

import java.io.*;
import java.util.HashMap;

public class EventManager {
  /*
   * Event-manager timeout after being idle for specified seconds.
   */
  private static final long IDLE_TIMEOUT = 20000;  // idle time in milli-seconds
  private HashMap<Long, String> eventsMap = new HashMap<>();
  private boolean isIdle = false;
  
  private InputStream inputStream = System.in;
  private PrintStream outputStream = System.out;

  public InputStream getInputStream() {
	return inputStream;
  }

  public void setInputStream(InputStream inputStream) {
	this.inputStream = inputStream;
  }

  public PrintStream getOutputStream() {
	return outputStream;
  }

  public void setOutputStream(PrintStream outputStream) {
	this.outputStream = outputStream;
  }

  public boolean isIdle() {
	return isIdle;
  }

  public void setIdle(boolean isIdle) {
	this.isIdle = isIdle;
  }

  public HashMap<Long, String> getEventsMap() {
	return eventsMap;
  }

  public void setEventsMap(HashMap<Long, String> eventsMap) {
	this.eventsMap = eventsMap;
  }
  
  public void startEventManagaer() {
	  Thread reportTimeout = new Thread(new ReportTimeout(this, IDLE_TIMEOUT));
      Thread reportStatus = new Thread(new ReportStatus(this));
      Thread eventListener = new Thread(new EventListener(this));
      
      try 
      {
    	  inputStream = new FileInputStream(new File("src/test/resources/input.txt"));
    	  outputStream = new PrintStream(new File("src/test/resources/output.txt"));
    	  
    	  reportTimeout.start();
          reportStatus.start();
          eventListener.start();
          
    	  reportStatus.join();
    	  reportStatus.join();
    	  eventListener.join();
      } 
      catch (Exception e) 
      {
    	  outputStream.println("Exception while running event-manager: " + e);
	  }
      finally {
    	  try {
    	    inputStream.close();
    	    outputStream.close();
    	  }
    	  catch (Exception e) {	
    		  
		  }
      }
  }
}