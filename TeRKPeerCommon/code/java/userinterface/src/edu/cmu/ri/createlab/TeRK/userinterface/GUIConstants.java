package edu.cmu.ri.createlab.TeRK.userinterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import edu.cmu.ri.mrpl.swing.ImageUtils;

/**
 * <p>
 * <code>GUIConstants</code> defines constants common to GUI applications
 * </p>
 *
 * @author Chris Bartley (bartley@cmu.edu)
 */
public final class GUIConstants
   {
   /** Video resolution height (used for sizing the viewport) */
   public static final int VIDEO_FRAME_HEIGHT = 240;
   /** Video resolution width (used for sizing the viewport) */
   public static final int VIDEO_FRAME_WIDTH = 320;
   public static final Dimension VIDEO_FRAME_RESOLUTION = new Dimension(VIDEO_FRAME_WIDTH, VIDEO_FRAME_HEIGHT);

   public static final String FONT_NAME = "Verdana";
   public static final Font FONT_LARGE = new Font(FONT_NAME, 0, 20);
   public static final Font FONT_MEDIUM = new Font(FONT_NAME, 0, 14);
   public static final Font FONT_MEDIUM_BOLD = new Font(FONT_NAME, Font.BOLD, 14);
   public static final Font FONT_NORMAL = new Font(FONT_NAME, 0, 11);
   public static final Font FONT_SMALL = new Font(FONT_NAME, 0, 11);
   public static final Font FONT_TINY = new Font(FONT_NAME, 0, 9);
   public static final Font BUTTON_FONT = FONT_SMALL;

   /** The {@link Color} to use for text field backgrounds when the field has an error. */
   public static final Color TEXT_FIELD_BACKGROUND_COLOR_HAS_ERROR = new Color(255, 230, 230);
   /** The {@link Color} to use for text field backgrounds when the field has no error. */
   public static final Color TEXT_FIELD_BACKGROUND_COLOR_NO_ERROR = Color.WHITE;

   public static final char BULLET_CHARACTER = '\u2022';

   /** Creates a 5 x 5 rigid spacer. */
   public static Component createRigidSpacer()
      {
      return createRigidSpacer(5);
      }

   /** Creates a <code>size</code> x <code>size</code> rigid spacer. */
   public static Component createRigidSpacer(final int size)
      {
      return createRigidSpacer(size, size);
      }

   /** Creates a <code>width</code> x <code>height</code> rigid spacer. */
   public static Component createRigidSpacer(final int width, final int height)
      {
      return Box.createRigidArea(new Dimension(width, height));
      }

   public static JLabel createLabel(final String labelText)
      {
      return createLabel(labelText, FONT_SMALL);
      }

   public static JLabel createTinyFontLabel(final String labelText)
      {
      return createLabel(labelText, FONT_TINY);
      }

   public static JLabel createLabel(final String labelText, final Font font)
      {
      final JLabel label = new JLabel(labelText);
      label.setFont(font);
      return label;
      }

   public static JLabel createVerticalLabel(final String labelText, final boolean isRotatedClockwise)
      {
      return createVerticalLabel(labelText, FONT_SMALL, isRotatedClockwise);
      }

   public static JLabel createVerticalTinyFontLabel(final String labelText, final boolean isRotatedClockwise)
      {
      return createVerticalLabel(labelText, FONT_TINY, isRotatedClockwise);
      }

   private static JLabel createVerticalLabel(final String labelText, final Font font, final boolean isRotatedClockwise)
      {
      final JLabel label = new JLabel(labelText, JLabel.CENTER);
      label.setUI(new VerticalLabelUI(isRotatedClockwise));
      label.setFont(font);

      return label;
      }

   /** Creates a (disabled) button with the given <code>label</code>. */
   public static JButton createButton(final String label)
      {
      return createButton(label, false);
      }

   /** Creates a button with the given <code>label</code> and with an enabled state specified by <code>isEnabled</code>. */
   public static JButton createButton(final String label, final boolean isEnabled)
      {
      final JButton button = new JButton(label);
      button.setFont(BUTTON_FONT);
      button.setEnabled(isEnabled);
      button.setOpaque(false);// required for Macintosh
      return button;
      }

   /** Creates a image button using the images specified by the given paths. */
   public static JButton createImageButton(final String defaultIconPath,
                                           final String disabledIconPath,
                                           final String pressedIconPath,
                                           final String rolloverIconPath)
      {
      final JButton button = new JButton();
      button.setIcon(ImageUtils.createImageIcon(defaultIconPath));
      button.setDisabledIcon(ImageUtils.createImageIcon(disabledIconPath));
      button.setPressedIcon(ImageUtils.createImageIcon(pressedIconPath));
      button.setRolloverIcon(ImageUtils.createImageIcon(rolloverIconPath));
      button.setBorder(null);
      button.setBorderPainted(false);
      button.setIconTextGap(0);
      button.setBorder(BorderFactory.createEmptyBorder());

      return button;
      }

   private GUIConstants()
      {
      // private to prevent instantiation
      }
   }
