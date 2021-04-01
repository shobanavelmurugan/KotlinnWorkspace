package com.shobanasha.shapesexample

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.shobanasha.shapesexample.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        drawCircle()
    }

    private fun drawCircle() {
        // get device dimensions
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val bitmap = Bitmap.createBitmap(
            displayMetrics.widthPixels,
            displayMetrics.heightPixels,
            Bitmap.Config.ARGB_4444
        )
        val canvas = Canvas(bitmap)

        // canvas background color
        // canvas.drawARGB(255, 78, 168, 186);
        //canvas.drawColor(Color.DKGRAY)

        val paint = Paint()
        paint.color = Color.parseColor("#FFFFFF")
        paint.strokeWidth = 10F
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        paint.isDither = true
        paint.pathEffect = DashPathEffect(floatArrayOf(25f, 25f, 25f, 25f), 0F)


        // circle center
        println("Width : " + displayMetrics.widthPixels)
        val center_x = (displayMetrics.widthPixels / 2).toFloat()
        val center_y = (displayMetrics.heightPixels / 2).toFloat()
        val radius = 400F
        Log.w(
            "Circle:",
            "Width:${displayMetrics.widthPixels},Height:${displayMetrics.heightPixels}"
        )
        Log.w(
            "Circle:",
            "center_x:${(displayMetrics.widthPixels / 2).toFloat()},center_y:${(displayMetrics.heightPixels / 2).toFloat()}"
        )
        Log.w("Circle:", "radius:${radius}")

        /* *//*draw a point*//*
        canvas.drawPoint(1080F, 0F, paint);*/
        // draw circle
        /*X=540 Y= 1044-200 = 844 */
        //canvas.drawCircle(center_x, center_y - 200, radius, paint)

        /*Draw Oval */
        paint.strokeWidth = 10F
        paint.pathEffect = DashPathEffect(floatArrayOf(25f, 25f, 25f, 25f), 0F)
        paint.color = Color.BLACK
        val oval3 = RectF(140F, 304F, 940F, 1244F)
        canvas.drawOval(oval3, paint)

        paint.color = Color.YELLOW
        paint.isDither = true // set the dither to true

        paint.style = Paint.Style.STROKE // set to STOKE

        paint.strokeJoin = Paint.Join.ROUND // set the join to round you want

        paint.strokeCap = Paint.Cap.ROUND // set the paint cap to round too

        paint.pathEffect = CornerPathEffect(50F) // set the path effect when they join.
        paint.strokeWidth = 30F
        paint.pathEffect = DashPathEffect(floatArrayOf(0f, 0f, 0f, 0f), 0F)
        paint.isAntiAlias = true
        paint.color = Color.WHITE
        val oval = RectF(
            center_x + 35 - radius, center_y - radius,
            center_x - 35 + radius, center_y - 180 + radius
        )
        canvas.drawArc(oval, 45f, 90f, false, paint)

        paint.color = Color.BLACK
        paint.strokeWidth = 10F
        paint.pathEffect = DashPathEffect(floatArrayOf(25f, 25f, 25f, 25f), 0F)
        val oval1 = RectF(6F, 1100F, 1074F, 1500F)
        canvas.drawArc(oval1, -180f, 180f, false, paint)

        paint.color = Color.WHITE
        paint.strokeWidth = 37F
        paint.pathEffect = DashPathEffect(floatArrayOf(0f, 0f, 0f, 0f), 0F)
        val oval2 = RectF(300F, 1110F, 800F, 1190F)
        canvas.drawArc(oval2, -180f, 180f, false, paint)


        // set bitmap as background to ImageView
        binding.imgVwShape.background = BitmapDrawable(resources, bitmap)
    }
}