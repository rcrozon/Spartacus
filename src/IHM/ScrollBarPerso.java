/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author Romain
 */
public class ScrollBarPerso extends JScrollBar {

    public ScrollBarPerso(Image img) {
        super();
        this.setUI(new ScrollBarCustomUI(img));
    }

    public class ScrollBarCustomUI extends BasicScrollBarUI {

        private final Image image;

        public ScrollBarCustomUI(Image img) {
            this.image = img;
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            Graphics2D g2g = (Graphics2D) g;
            g2g.dispose();
            g2g.drawImage(image, 0, 0, null);
            super.paintThumb(g2g, c, thumbBounds);
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            super.paintTrack(g, c, trackBounds);
        }


        @Override
        protected void setThumbBounds(int x, int y, int width, int height) {
            super.setThumbBounds(x, y, width, height);
        }


        @Override
        protected Dimension getMinimumThumbSize() {
            return new Dimension(thumbRect.width, thumbRect.height);
        }

        @Override
        protected Dimension getMaximumThumbSize() {
            return new Dimension(thumbRect.width, thumbRect.height);
        }
    }   
}
