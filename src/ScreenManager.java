import java.awt.*;
import java.awt.image.BufferStrategy;

public class ScreenManager {

    private BufferStrategy gameBufferStrategy;
    private BufferCapabilities strategyBufferCapabilities;
    private Graphics2D gameGraphicContent;

    public ScreenManager(BufferStrategy strategy, BufferCapabilities capability){}


public void changeBufferCapabilities( BufferCapabilities capabilities){}
    public Graphics2D getGraphics(){}
    public void iterateBuffer(){}
}
