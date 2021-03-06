package edu.cmu.ri.createlab.TeRK.client.universalremote.controlpanel;

import java.awt.Color;
import java.awt.Component;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import edu.cmu.ri.createlab.TeRK.finch.FinchService;
import edu.cmu.ri.mrpl.swing.AbstractTimeConsumingAction;
import org.apache.log4j.Logger;
import org.jdesktop.layout.GroupLayout;

/**
 * @author Chris Bartley (bartley@cmu.edu)
 */
public final class ControlPanelManagerView implements ControlPanelManagerViewEventPublisher
   {
   private static final Logger LOG = Logger.getLogger(ControlPanelManagerView.class);

   private final JPanel mainPanel = new JPanel();
   private final GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
   private final Collection<ControlPanelManagerViewEventListener> controlPanelManagerViewEventListeners = new HashSet<ControlPanelManagerViewEventListener>();
   private Map<String, ServiceControlPanel> serviceControlPanelMap;
   private Map<String, SortedMap<Integer, JCheckBox>> serviceDeviceToggleButtonMap;
   private final Runnable addFinchControlPanelsRunnable = new GUICreationRunnable(new FinchGUICreationStrategy());
   private final Runnable addHummingbirdControlPanelsRunnable = new GUICreationRunnable(new HummingbirdGUICreationStrategy());
   private final Runnable removeAllFromMainPanelRunnable =
         new Runnable()
         {
         public void run()
            {
            mainPanel.removeAll();
            }
         };
   private final ControlPanelManager controlPanelManager;

   public ControlPanelManagerView(final ControlPanelManager controlPanelManager)
      {
      this.controlPanelManager = controlPanelManager;
      // add self as a listener
      controlPanelManager.addControlPanelManagerEventListener(new MyControlPanelManagerEventListener());

      mainPanel.setLayout(mainPanelLayout);
      mainPanel.setBackground(Color.WHITE);
      }

   public void addControlPanelManagerViewEventListener(final ControlPanelManagerViewEventListener listener)
      {
      if (listener != null)
         {
         controlPanelManagerViewEventListeners.add(listener);
         }
      }

   public void removeControlPanelManagerViewEventListener(final ControlPanelManagerViewEventListener listener)
      {
      if (listener != null)
         {
         controlPanelManagerViewEventListeners.remove(listener);
         }
      }

   public Component getComponent()
      {
      return mainPanel;
      }

   private void addControlPanels()
      {
      // todo: we only support finches and hummingbirds at the moment--need to support qwerks, etc. later.

      if (LOG.isDebugEnabled())
         {
         LOG.debug("ControlPanelManagerView.addControlPanels()");
         for (final String key : serviceControlPanelMap.keySet())
            {
            LOG.debug("   [" + key + "]=[" + serviceControlPanelMap.get(key).getTypeId() + "]");
            }
         }

      if (serviceControlPanelMap.containsKey(FinchService.TYPE_ID))
         {
         runInGUIThread(addFinchControlPanelsRunnable);
         }
      else
         {
         runInGUIThread(addHummingbirdControlPanelsRunnable);
         }
      }

   private void removeControlPanels()
      {
      LOG.debug("ControlPanelManagerView.removeControlPanels()");

      runInGUIThread(removeAllFromMainPanelRunnable);
      }

   private void runInGUIThread(final Runnable runnable)
      {
      if (SwingUtilities.isEventDispatchThread())
         {
         runnable.run();
         }
      else
         {
         SwingUtilities.invokeLater(runnable);
         }
      }

   private final class MyControlPanelManagerEventListener implements ControlPanelManagerEventListener
      {
      public void handlePeerConnectedEvent(final Map<String, ServiceControlPanel> serviceControlPanelMap)
         {
         ControlPanelManagerView.this.serviceControlPanelMap = new HashMap<String, ServiceControlPanel>(serviceControlPanelMap);
         ControlPanelManagerView.this.serviceDeviceToggleButtonMap = new HashMap<String, SortedMap<Integer, JCheckBox>>();

         addControlPanels();

         notifyListeners();
         }

      public void handlePeerDisconnectedEvent()
         {
         removeControlPanels();

         notifyListeners();
         }

      public void handleDeviceActivityStatusChange(final String serviceTypeId, final int deviceIndex, final boolean isActive)
         {
         SwingUtilities.invokeLater(
               new Runnable()
               {
               public void run()
                  {
                  final SortedMap<Integer, JCheckBox> checkBoxMap = serviceDeviceToggleButtonMap.get(serviceTypeId);
                  if (checkBoxMap != null)
                     {
                     final JCheckBox checkBox = checkBoxMap.get(deviceIndex);
                     if (checkBox != null)
                        {
                        checkBox.setSelected(isActive);
                        }
                     }
                  }
               });
         notifyListeners();
         }

      private void notifyListeners()
         {
         // notify ControlPanelManagerViewEventListeners of layout change
         for (final ControlPanelManagerViewEventListener listener : controlPanelManagerViewEventListeners)
            {
            listener.handleLayoutChange();
            }
         }
      }

   private final class GUICreationRunnable implements Runnable
      {
      private final PeerGUICreationStrategy peerGUICreationStrategy;

      private GUICreationRunnable(final PeerGUICreationStrategy peerGUICreationStrategy)
         {
         this.peerGUICreationStrategy = peerGUICreationStrategy;
         }

      private JCheckBox createDeviceCheckBox(final int deviceIndex, final String serviceTypeId)
         {
         final JCheckBox checkBox = new JCheckBox();
         checkBox.addActionListener(
               new AbstractTimeConsumingAction()
               {
               protected Object executeTimeConsumingAction()
                  {
                  controlPanelManager.setDeviceActive(serviceTypeId, deviceIndex, checkBox.isSelected());
                  return null;
                  }
               });
         return checkBox;
         }

      public final void run()
         {
         if (serviceControlPanelMap != null)
            {
            // clear the current map
            serviceDeviceToggleButtonMap.clear();

            // loop over the services and create a checkbox for each device
            for (final ServiceControlPanel serviceControlPanel : serviceControlPanelMap.values())
               {
               final SortedMap<Integer, JCheckBox> checkBoxMap = new TreeMap<Integer, JCheckBox>();
               serviceDeviceToggleButtonMap.put(serviceControlPanel.getTypeId(), checkBoxMap);

               // create the device checkboxes
               final int numDevices = serviceControlPanel.getDeviceCount();
               for (int i = 0; i < numDevices; i++)
                  {
                  final JCheckBox checkBox = createDeviceCheckBox(i, serviceControlPanel.getTypeId());
                  checkBoxMap.put(i, checkBox);
                  }
               }

            peerGUICreationStrategy.createGUI(mainPanelLayout, serviceControlPanelMap, serviceDeviceToggleButtonMap);
            }
         }
      }
   }

