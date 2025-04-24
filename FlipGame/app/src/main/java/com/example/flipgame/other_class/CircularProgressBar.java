package com.example.flipgame.other_class;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CircularProgressBar extends View {
    private Paint backgroundPaint;
    private Paint progressPaint;
    private Paint textPaint;
    private int progress = 0; // Current progress (0 to 100)
    private int strokeWidth = 8;
    private int remainingTime = 0;

    public CircularProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Background circle paint
        backgroundPaint = new Paint();
        backgroundPaint.setColor(0xFFE0E0E0); // Light gray
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(strokeWidth);
        backgroundPaint.setAntiAlias(true);

        // Progress circle paint
        progressPaint = new Paint();
        progressPaint.setColor(0xFFFF5722); // Orange
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(strokeWidth);
        progressPaint.setAntiAlias(true);


        textPaint = new Paint();
        textPaint.setColor(0xFF000000); // Black color
        textPaint.setTextSize(50); // Text size
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Center and radius
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(centerX, centerY) - strokeWidth;

        // Draw background circle
        canvas.drawCircle(centerX, centerY, radius, backgroundPaint);

        // Draw progress arc
        float sweepAngle = (360 * progress) / 100f;
        canvas.drawArc(
                centerX - radius, centerY - radius,
                centerX + radius, centerY + radius,
                -90, // Start angle (top)
                sweepAngle, // Sweep angle
                false, // Not filled
                progressPaint
        );
        String timeText = remainingTime + "s";
        canvas.drawText(timeText, centerX, centerY + (textPaint.getTextSize() / 3), textPaint); // Adjust text position
    }

    // Method to set progress
    public void setProgress(int progress, int remainingTime) {
        this.progress = progress;
        this.remainingTime = remainingTime;
        invalidate(); // Redraw the view
    }

    // Method to animate progress based on timer
    public void startTimer(int durationInSeconds) {
        ValueAnimator animator = ValueAnimator.ofInt(durationInSeconds, 0); // Timer from durationInSeconds to 0
        animator.setDuration(durationInSeconds * 1000); // Convert seconds to milliseconds
        animator.addUpdateListener(animation -> {
            int animatedValue = (int) animation.getAnimatedValue();
            setProgress((animatedValue * 100) / durationInSeconds, animatedValue); // Update progress and time
        });
        animator.start();
    }
}