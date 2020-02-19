package fi.iki.jka;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class JPhotoFrameTest {
    class FakePhotoFrame extends JPhotoFrame {
        int lastInterval;

        protected FakePhotoFrame() throws Exception {
        }

        @Override
        protected void startSlideShow2(int interval) {
            lastInterval = interval;
        }
    }

    @Test
    public void photoFrameTestSpeedSlow() throws Exception {
        FakePhotoFrame photoFrame = new FakePhotoFrame();
        photoFrame.startSlideShow();
        assertThat(photoFrame.lastInterval, equalTo(5000));
    }

    @Test
    public void photoFrameTestSpeedFast() throws Exception {
        FakePhotoFrame photoFrame = new FakePhotoFrame();
        photoFrame.startFastSlideShow();
        assertThat(photoFrame.lastInterval, equalTo(2000));
    }
}