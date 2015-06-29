package MySurfaceView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 *
 * Created by firedom on 6/29/15.
 *
 * opt+return  导入各种包的快捷键
 *
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable{
    private SurfaceHolder mHolder;
    /**
     * 创建画布
     */
    private Canvas mCanvas;
    /**
     * 创建线程
     */
    private Thread t;
    private boolean isRunning;

    private float x, y, r;

    public MySurfaceView(Context context) {
        this(context, null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setZOrderOnTop(true);
        mHolder.setFormat(PixelFormat.TRANSLUCENT); // 设置背景，透明为透明
        this.setKeepScreenOn(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRunning = true;
        t = new Thread(this);
        t.start();
    }// 创建方法

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    } // 更新方法

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning = false;
    }// 销毁方法

    @Override
    public void run() {
        while(isRunning){ // 每隔50ms刷新一次
            long start = System.currentTimeMillis();
            draw();
            long end = System.currentTimeMillis();
            if(end - start < 50){
                try{
                    Thread.sleep(50 - (end - start));
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void draw(){
        try { // 防止获取失败的手段
            mCanvas = mHolder.lockCanvas();
            if(mCanvas != null){
                /**
                 * 此位置添加内容
                 */
            mCanvas.drawARGB(255, 255, 255, 255);
            mCanvas.drawCircle(x, y, r, null);
             x++; y++; r++;
            }
        }catch(Exception e){

        }finally{
            if(mCanvas != null){
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
