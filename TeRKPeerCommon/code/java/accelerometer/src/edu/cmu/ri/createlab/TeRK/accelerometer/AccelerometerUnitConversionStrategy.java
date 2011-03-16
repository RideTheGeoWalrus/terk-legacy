package edu.cmu.ri.createlab.TeRK.accelerometer;

import edu.cmu.ri.mrpl.TeRK.services.UnitConversionStrategy;

/**
 * <p>
 * <code>AccelerometerUnitConversionStrategy</code> helps convert between raw accelerometer values and g's.
 * </p>
 *
 * @author Chris Bartley (bartley@cmu.edu)
 */
public interface AccelerometerUnitConversionStrategy extends UnitConversionStrategy
   {
   /**
    * Converts the given {@link AccelerometerState} to {@link AccelerometerGs}. Returns <code>null</code> if the given
    * {@link AccelerometerState} is <code>null</code>.
    */
   AccelerometerGs convert(final AccelerometerState accelerometerState);

   /**
    * Converts the given {@link AccelerometerGs} to {@link AccelerometerState}. Returns <code>null</code> if the given
    * {@link AccelerometerGs} is <code>null</code>.
    */
   AccelerometerState convert(final AccelerometerGs accelerometerState);

   /** Converts the given acceleration (in native units) to g's. */
   double convertToGs(final int nativeValue);

   /** Converts the given acceleration (in g's) to native units. */
   int convertToNative(final double gValue);

   /** Returns the minimum native value. */
   int getMinimumNativeValue();

   /** Returns the maximum native value. */
   int getMaximumNativeValue();
   }