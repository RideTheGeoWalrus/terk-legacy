package edu.cmu.ri.createlab.TeRK.obstacle;

import edu.cmu.ri.mrpl.TeRK.services.DeviceController;
import edu.cmu.ri.mrpl.TeRK.services.Service;

/**
 * @author Chris Bartley (bartley@cmu.edu)
 */
public interface SimpleObstacleDetectorService extends Service, DeviceController
   {
   String TYPE_ID = "::TeRK::obstacle::SimpleObstacleDetectorService";

   /**
    * Returns <code>true</code> if the obstacle detector specified by the given <code>id</code> detects an obstacle;
    * returns <code>false</code> otherwise.  Returns <code>null</code> if the value could not be retrieved.
    */
   Boolean isObstacleDetected(final int id);

   /**
    * Returns a <code>boolean</code> array containing the states of the obstacle detector(s).  For each array index,
    * the value is <code>true</code> if the obstacle detector detects an obstacle and <code>false</code> otherwise.
    * Returns <code>null</code> if the value could not be retrieved.
    */
   boolean[] areObstaclesDetected();
   }