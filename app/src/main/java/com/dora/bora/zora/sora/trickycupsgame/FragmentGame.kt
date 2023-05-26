package com.dora.bora.zora.sora.trickycupsgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.dora.bora.zora.sora.trickycupsgame.databinding.FragmentGameBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.collections.ArrayList


class FragmentGame : Fragment() {
    private lateinit var binding:FragmentGameBinding
    private var ballPosition = 0
    private lateinit var listCups: ArrayList<ImageView>
    private lateinit var listBalls: ArrayList<ImageView>
    var has = false
    var score = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentGameBinding.inflate(layoutInflater)

        binding.cup1.setOnClickListener {
            if (has) {
                checkForBall(0)
                has = false
            }

        }
        binding.cup2.setOnClickListener {
            if (has) {
                checkForBall(1)
                has = false
            }

        }
        binding.cup3.setOnClickListener {
            if (has) {
                checkForBall(2)
                has = false
            }

        }

        loadButtons()
        ballPosition = Random().nextInt(3)
        binding.start.setOnClickListener {
            if (!has) {


                val animation =
                    AnimationUtils.loadAnimation(binding.root.context, R.anim.anim_translate2)
                animation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        val animation2 =
                            AnimationUtils.loadAnimation(
                                binding.root.context,
                                R.anim.anim_translate1
                            )
                        animation2.setAnimationListener(object : Animation.AnimationListener {
                            override fun onAnimationStart(animation: Animation?) {

                            }

                            override fun onAnimationEnd(animation: Animation?) {
                                listBalls.forEach { it.visibility = View.INVISIBLE }

                                val animations = ArrayList<Int>().apply {
                                    add(R.anim.slide_in_left)
                                    add(R.anim.slide_in_right)
                                    add(R.anim.slide_in_top)
                                    shuffle()
                                }

                                val anim = AnimationUtils.loadAnimation(
                                    binding.root.context,
                                    animations[0]
                                )


                                anim.setAnimationListener(object : Animation.AnimationListener {
                                    override fun onAnimationStart(animation: Animation?) {

                                    }

                                    override fun onAnimationEnd(animation: Animation?) {
                                        listBalls.forEach {
                                            it.visibility = View.INVISIBLE
                                        }
                                        ballPosition = Random().nextInt(3)
                                        listBalls[ballPosition].visibility = View.VISIBLE
                                        has = true
                                    }

                                    override fun onAnimationRepeat(animation: Animation?) {

                                    }
                                })

                                binding.cup1.startAnimation(
                                    anim
                                )
                                val anim2 = AnimationUtils.loadAnimation(
                                    binding.root.context,
                                    animations[1]
                                )

                                binding.cup2.startAnimation(
                                    anim2
                                )
                                val anim3 = AnimationUtils.loadAnimation(
                                    binding.root.context,
                                    animations[2]
                                )

                                binding.cup3.startAnimation(
                                    anim3
                                )


                            }

                            override fun onAnimationRepeat(animation: Animation?) {

                            }
                        })
                        listCups.forEach {
                            it.startAnimation(animation2)
                        }
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })
                ballPosition = Random().nextInt(3)
                listBalls[ballPosition].visibility = View.VISIBLE
                listCups.forEach {
                    it.startAnimation(animation)
                }

            }
        }
        return binding.root
    }

    fun loadButtons() {
        listBalls = ArrayList()
        listBalls.add(binding.ball1)
        listBalls.add(binding.ball2)
        listBalls.add(binding.ball3)

        listCups = ArrayList()
        listCups.add(binding.cup1)
        listCups.add(binding.cup2)
        listCups.add(binding.cup3)
    }

    private fun checkForBall(position: Int) {
        listBalls[ballPosition].visibility = View.VISIBLE
        if (position == ballPosition) {
            val snackbar = Snackbar.make(binding.root, "You found the ball !", 1000)
            snackbar.show()
            score+=10
            binding.score.text = "score: $score"


        } else {
            val snackbar = Snackbar.make(binding.root, "Sorry, try again.", 1000)
            snackbar.show()
            if (score!=0){
                score-=10
            }
            binding.score.text = "score: $score"
        }

        val animation =
            AnimationUtils.loadAnimation(binding.root.context, R.anim.anim_translate2)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                val animation2 =
                    AnimationUtils.loadAnimation(binding.root.context, R.anim.anim_translate1)
                animation2.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        listBalls.forEach { it.visibility = View.INVISIBLE }


                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })
                listCups.forEach {
                    it.startAnimation(animation2)
                }
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
        listCups.forEach {
            it.startAnimation(animation)
        }
    }

}