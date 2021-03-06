package edu.cmu.ri.createlab.TeRK.robot.finch.application;

import java.util.HashMap;
import java.util.Map;
import Ice.ObjectPrx;
import edu.cmu.ri.createlab.TeRK.accelerometer.AccelerometerService;
import edu.cmu.ri.createlab.TeRK.accelerometer.AccelerometerServiceIceImpl;
import edu.cmu.ri.createlab.TeRK.audio.AudioService;
import edu.cmu.ri.createlab.TeRK.audio.AudioServiceIceImpl;
import edu.cmu.ri.createlab.TeRK.buzzer.BuzzerService;
import edu.cmu.ri.createlab.TeRK.buzzer.BuzzerServiceIceImpl;
import edu.cmu.ri.createlab.TeRK.finch.FinchService;
import edu.cmu.ri.createlab.TeRK.finch.FinchServiceIceImpl;
import edu.cmu.ri.createlab.TeRK.led.FullColorLEDService;
import edu.cmu.ri.createlab.TeRK.led.FullColorLEDServiceIceImpl;
import edu.cmu.ri.createlab.TeRK.motor.PositionControllableMotorService;
import edu.cmu.ri.createlab.TeRK.motor.PositionControllableMotorServiceIceImpl;
import edu.cmu.ri.createlab.TeRK.motor.VelocityControllableMotorService;
import edu.cmu.ri.createlab.TeRK.motor.VelocityControllableMotorServiceIceImpl;
import edu.cmu.ri.createlab.TeRK.obstacle.SimpleObstacleDetectorService;
import edu.cmu.ri.createlab.TeRK.obstacle.SimpleObstacleDetectorServiceIceImpl;
import edu.cmu.ri.createlab.TeRK.photoresistor.PhotoresistorService;
import edu.cmu.ri.createlab.TeRK.photoresistor.PhotoresistorServiceIceImpl;
import edu.cmu.ri.createlab.TeRK.thermistor.ThermistorService;
import edu.cmu.ri.createlab.TeRK.thermistor.ThermistorServiceIceImpl;
import edu.cmu.ri.mrpl.TeRK.AudioControllerPrxHelper;
import edu.cmu.ri.mrpl.TeRK.accelerometer.AccelerometerServicePrxHelper;
import edu.cmu.ri.mrpl.TeRK.buzzer.BuzzerServicePrxHelper;
import edu.cmu.ri.mrpl.TeRK.finch.FinchServicePrxHelper;
import edu.cmu.ri.mrpl.TeRK.led.FullColorLEDServicePrxHelper;
import edu.cmu.ri.mrpl.TeRK.motor.PositionControllableMotorServicePrxHelper;
import edu.cmu.ri.mrpl.TeRK.motor.VelocityControllableMotorServicePrxHelper;
import edu.cmu.ri.mrpl.TeRK.obstacle.SimpleObstacleDetectorServicePrxHelper;
import edu.cmu.ri.mrpl.TeRK.photoresistor.PhotoresistorServicePrxHelper;
import edu.cmu.ri.mrpl.TeRK.services.IceServiceCreator;
import edu.cmu.ri.mrpl.TeRK.services.IceServiceFactory;
import edu.cmu.ri.mrpl.TeRK.services.Service;
import edu.cmu.ri.mrpl.TeRK.thermistor.ThermistorServicePrxHelper;
import org.apache.log4j.Logger;

/**
 * @author Chris Bartley (bartley@cmu.edu)
 */
class NetworkedFinchServiceFactory implements IceServiceFactory
   {
   private static final Logger LOG = Logger.getLogger(NetworkedFinchServiceFactory.class);

   private final Map<String, IceServiceCreator> typeIdToServiceCreatorsMap = new HashMap<String, IceServiceCreator>();

   NetworkedFinchServiceFactory()
      {
      typeIdToServiceCreatorsMap.put(AccelerometerService.TYPE_ID,
                                     new IceServiceCreator()
                                     {
                                     public Service create(final ObjectPrx serviceProxy)
                                        {
                                        return AccelerometerServiceIceImpl.create(AccelerometerServicePrxHelper.uncheckedCast(serviceProxy));
                                        }
                                     });
      typeIdToServiceCreatorsMap.put(AudioService.TYPE_ID,
                                     new IceServiceCreator()
                                     {
                                     public Service create(final ObjectPrx serviceProxy)
                                        {
                                        return AudioServiceIceImpl.create(AudioControllerPrxHelper.uncheckedCast(serviceProxy));
                                        }
                                     });
      typeIdToServiceCreatorsMap.put(BuzzerService.TYPE_ID,
                                     new IceServiceCreator()
                                     {
                                     public Service create(final ObjectPrx serviceProxy)
                                        {
                                        return BuzzerServiceIceImpl.create(BuzzerServicePrxHelper.uncheckedCast(serviceProxy));
                                        }
                                     });
      typeIdToServiceCreatorsMap.put(FinchService.TYPE_ID,
                                     new IceServiceCreator()
                                     {
                                     public Service create(final ObjectPrx serviceProxy)
                                        {
                                        return FinchServiceIceImpl.create(FinchServicePrxHelper.uncheckedCast(serviceProxy));
                                        }
                                     });
      typeIdToServiceCreatorsMap.put(FullColorLEDService.TYPE_ID,
                                     new IceServiceCreator()
                                     {
                                     public Service create(final ObjectPrx serviceProxy)
                                        {
                                        return FullColorLEDServiceIceImpl.create(FullColorLEDServicePrxHelper.uncheckedCast(serviceProxy));
                                        }
                                     });
      typeIdToServiceCreatorsMap.put(PhotoresistorService.TYPE_ID,
                                     new IceServiceCreator()
                                     {
                                     public Service create(final ObjectPrx serviceProxy)
                                        {
                                        return PhotoresistorServiceIceImpl.create(PhotoresistorServicePrxHelper.uncheckedCast(serviceProxy));
                                        }
                                     });
      typeIdToServiceCreatorsMap.put(PositionControllableMotorService.TYPE_ID,
                                     new IceServiceCreator()
                                     {
                                     public Service create(final ObjectPrx serviceProxy)
                                        {
                                        return PositionControllableMotorServiceIceImpl.create(PositionControllableMotorServicePrxHelper.uncheckedCast(serviceProxy));
                                        }
                                     });
      typeIdToServiceCreatorsMap.put(SimpleObstacleDetectorService.TYPE_ID,
                                     new IceServiceCreator()
                                     {
                                     public Service create(final ObjectPrx serviceProxy)
                                        {
                                        return SimpleObstacleDetectorServiceIceImpl.create(SimpleObstacleDetectorServicePrxHelper.uncheckedCast(serviceProxy));
                                        }
                                     });
      typeIdToServiceCreatorsMap.put(ThermistorService.TYPE_ID,
                                     new IceServiceCreator()
                                     {
                                     public Service create(final ObjectPrx serviceProxy)
                                        {
                                        return ThermistorServiceIceImpl.create(ThermistorServicePrxHelper.uncheckedCast(serviceProxy));
                                        }
                                     });
      typeIdToServiceCreatorsMap.put(VelocityControllableMotorService.TYPE_ID,
                                     new IceServiceCreator()
                                     {
                                     public Service create(final ObjectPrx serviceProxy)
                                        {
                                        return VelocityControllableMotorServiceIceImpl.create(VelocityControllableMotorServicePrxHelper.uncheckedCast(serviceProxy));
                                        }
                                     });
      }

   public Service createService(final String serviceTypeId, final ObjectPrx serviceProxy)
      {
      if (typeIdToServiceCreatorsMap.containsKey(serviceTypeId))
         {
         LOG.debug("UniversalRemoteServiceFactory.createService(" + serviceTypeId + ")");
         return typeIdToServiceCreatorsMap.get(serviceTypeId).create(serviceProxy);
         }
      return null;
      }
   }