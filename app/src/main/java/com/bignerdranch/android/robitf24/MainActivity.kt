package com.bignerdranch.android.robitf_24


import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageButton
import androidx.activity.viewModels

private const val TAG = "MainActivity"  //TOP LEVEL

class MainActivity : AppCompatActivity() {

    private lateinit var red_robot: ImageView
    private lateinit var white_robot: ImageView
    private lateinit var yellow_robot: ImageView
    private lateinit var message_box: TextView
    private lateinit var robotImages: MutableList<ImageView>

    //private var turnCount = 0

    private val robots = listOf(
        Robot(
            R.string.red_robot_mssg, false,
            R.drawable.robot_red_large, R.drawable.robot_red_small
        ),
        Robot(
            R.string.white_robot_mssg, false,
            R.drawable.robot_white_large, R.drawable.robot_white_small
        ),
        Robot(
            R.string.yellow_robot_mssg, false,
            R.drawable.robot_yellow_large, R.drawable.robot_yellow_small
        )
    )

    // private val robotViewModel = RobotViewModel() runout Memary
    private val robotViewModel: RobotViewModel by viewModels()

    //LAZY

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("turnCount", robotViewModel.currentTurn)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.d(TAG, "onCrete(Bundle?) called")
        Log.d(TAG, "Got a RobotViewModel: $robotViewModel")

        red_robot = findViewById(R.id.robot_red)
        white_robot = findViewById(R.id.robot_white)
        yellow_robot = findViewById(R.id.robot_yellow)
        message_box = findViewById(R.id.begin_game_mssg)

        robotImages = mutableListOf(red_robot, white_robot, yellow_robot)

        //Log.d(TAG, "Total robots: ${robots.size}")
        //Log.d(TAG, "onCrete() entered")

        red_robot.setOnClickListener {
            Toast.makeText(this, "Red Robot Clicked", Toast.LENGTH_SHORT).show()
            toggleImage()
        }

        white_robot.setOnClickListener {
            Toast.makeText(this, "White Robot Clicked", Toast.LENGTH_SHORT).show()
            toggleImage()
        }

        yellow_robot.setOnClickListener {
            Toast.makeText(this, "Yellow Robot Clicked", Toast.LENGTH_SHORT).show()
            toggleImage()
        }

//        val clockwiseButton: ImageButton = findViewById(R.id.clockwise)
//        clockwiseButton.setOnClickListener {
//            turnCount--
//            if (turnCount < 1 ) turnCount = 3
//            updateMessageBox()
//            setRobotTurns()
//            setRobotImages()
////            toggleImage()
//        }


//        val clockwiseButton: ImageButton = findViewById(R.id.clockwise)
//        clockwiseButton.setOnClickListener {
//            if (turnCount == 0) {
//                turnCount = 1
//            } else {
//                turnCount--
//                if (turnCount < 1) turnCount = 3
//            }

            robotViewModel.advanceTurn()
            updateMessageBox()
            setRobotTurns()
            setRobotImages()
        }

//        val counterButton: ImageButton = findViewById(R.id.counter)
//        counterButton.setOnClickListener {
////            turnCount++
////            if (turnCount > 3 ) turnCount = 1
//            robotViewModel.advanceTurn()
//            updateMessageBox()
//            setRobotTurns()
//            setRobotImages()
////          toggleImage()
//        }

//    }

    override fun onStart() {
        super.onStart() //SUPER PARENT
        Log.d(TAG, "onStart() entered")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() entered")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() entered")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() entered")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() entered")
    }

    private fun toggleImage() {

        robotViewModel.advanceTurn()

//        turnCount++
//        if (turnCount > 3)
//            turnCount = 1
//
//        when (turnCount ){
//            1 -> message_box.setText(R.string.red_robot_mssg)
//            2 -> message_box.setText(R.string.white_robot_mssg)
//            3 -> message_box.setText(R.string.yellow_robot_mssg)
//            else -> message_box.setText("Error?")
//       }
//
        updateMessageBox()
        setRobotTurns()
        setRobotImages()
//
//        if (turnCount == 1){
//            red_robot.setImageResource(R.drawable.robot_red_large)
//            white_robot.setImageResource(R.drawable.robot_white_small)
//            yellow_robot.setImageResource(R.drawable.robot_yellow_small)
//        }
//        else if (turnCount == 2){
//            red_robot.setImageResource(R.drawable.robot_red_small)
//            white_robot.setImageResource(R.drawable.robot_white_large)
//            yellow_robot.setImageResource(R.drawable.robot_yellow_small)
//        }
//        else{
//            red_robot.setImageResource(R.drawable.robot_red_small)
//            white_robot.setImageResource(R.drawable.robot_white_small)
//            yellow_robot.setImageResource(R.drawable.robot_yellow_large)
//        }
    }

    private fun updateMessageBox() {
        //message_box.setText(robots[turnCount - 1].robotMessageResore)
        message_box.setText(robots[robotViewModel.currentTurn - 1].robotMessageResource)
    }



    private fun setRobotTurns() {
        for (robot in robots) {
            robot.myTurn = false
        }
        //robots[turnCount - 1].myTurn = true
        robots[robotViewModel.currentTurn - 1].myTurn = true
    }

    private fun setRobotImages() {
        for (indy in robots.indices) {
            //for(indy in 0..2){
            if (robots[indy].myTurn) {
                robotImages[indy].setImageResource(robots[indy].robotImgLarge)
            } else {
                robotImages[indy].setImageResource((robots[indy].robotImgSmall))
            }
        }
    }
}