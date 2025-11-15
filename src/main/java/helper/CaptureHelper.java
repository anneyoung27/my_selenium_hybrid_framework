package helper;

import constant.ConfigData;
import factory.DriverFactory;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.LogUtils;

import java.awt.*;
import java.util.Date;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import org.openqa.selenium.io.FileHandler;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class CaptureHelper extends ScreenRecorder {
    // Record with Monte Media Library
    public static ScreenRecorder screenRecorder;
    public String name;

    public CaptureHelper(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat, Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder, String name) throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
        this.name = name;
    }

    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {
        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        } else if (!movieFolder.isDirectory()) {
            throw new IOException("\"" + movieFolder + "\" is not a directory.");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        return new File(movieFolder, name + "-" + dateFormat.format(new Date()) + "." + Registry.getInstance().getExtension(fileFormat));
    }

    public static void startRecord(String methodName){
        File file = new File(SystemHelper.getCurrentDir() + ConfigData.RECORD_VIDEO_PATH);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0, 0, width, height);
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        try {
            screenRecorder = new CaptureHelper(gc, captureSize, new Format(MediaTypeKey, FormatKeys.MediaType.FILE, MimeTypeKey, MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)), null, file, methodName);
            screenRecorder.start();
        }catch (IOException e){
            throw new RuntimeException(e);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    // Stop record video
    public static void stopRecord(){
        try {
            screenRecorder.stop();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void screenshot(String imageName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        TakesScreenshot ts = (TakesScreenshot) DriverFactory.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);

        File dir = new File(ConfigData.SCREENSHOT_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File destination = new File(
                ConfigData.SCREENSHOT_PATH + imageName + "-" + dateFormat.format(new Date()) + ".png"
        );

        try {
            FileHandler.copy(source, destination);
            LogUtils.info("Screenshot captured: " + destination.getAbsolutePath());
        } catch (IOException e) {
            LogUtils.error("Exception while taking screenshot: " + e.getMessage());
        }
    }

}
