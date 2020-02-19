package fi.iki.jka;

import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class JPhotoFrameTest {
    class FakePhotoFrame extends JPhotoFrame {
        int lastInterval;

        protected FakePhotoFrame() throws Exception {
        }

        @Override
        protected void startSlideShowWithParameters(int interval) {
            lastInterval = interval;
        }

        @Override
        public void saveEdit() {
            // Intentionally left blank
        }

        @Override
        public void setTitle() {
            // Intentionally left blank
        }
    }

    @Test
    public void photoFrameTestSpeedSlow() throws Exception {
        FakePhotoFrame photoFrame = new FakePhotoFrame();
        photoFrame.actionPerformed(new ActionEvent(photoFrame,0,JPhotoMenu.A_SLIDESHOW));
        assertThat(photoFrame.lastInterval, equalTo(5000));
    }

    @Test
    public void photoFrameTestSpeedFast() throws Exception {
        FakePhotoFrame photoFrame = new FakePhotoFrame();
        photoFrame.actionPerformed(new ActionEvent(photoFrame,0,JPhotoMenu.A_FASTSLIDESHOW));
        assertThat(photoFrame.lastInterval, equalTo(2000));
    }

    class FakePhotoShow extends JPhotoShow {
        private int timerInterval;

        public FakePhotoShow(JPhotoCollection photos, int interval, JList list) {
            super(photos, interval, list);
        }

        @Override
        protected void createTimer(int interval) {
            timerInterval = interval;
        }
    }
    @Test
    public void showTimer() {
        FakePhotoShow photoShow = new FakePhotoShow(new JPhotoCollection(), 500, null);
        assertThat(photoShow.timerInterval, equalTo(500));

    }
}