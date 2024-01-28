package com.example.task1_aston_musicplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MusicPlayerActivity : AppCompatActivity() {


    private var mediaPlayer: MediaPlayer? = null
    private lateinit var buttonPrevious: Button
    private lateinit var buttonPlay: Button
    private lateinit var buttonPause: Button
    private lateinit var buttonNext: Button
    private var currentMelodyIndex: Int = 0
    private val melodyResources = intArrayOf(R.raw.melody1, R.raw.melody2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)

        buttonPrevious = findViewById(R.id.button_previous)
        buttonPlay = findViewById(R.id.button_play)
        buttonPause = findViewById(R.id.button_pause)
        buttonNext = findViewById(R.id.button_next)

        buttonPrevious.setOnClickListener {
            stopPlayback()
            currentMelodyIndex =
                (currentMelodyIndex - 1 + melodyResources.size) % melodyResources.size
            mediaPlayer = MediaPlayer.create(this, melodyResources[currentMelodyIndex])
            mediaPlayer?.start()
        }

        buttonPlay.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.melody1)
            }
            mediaPlayer?.start()
        }

        buttonPause.setOnClickListener {
            mediaPlayer?.pause()
        }

        buttonNext.setOnClickListener {
            stopPlayback()
            currentMelodyIndex = (currentMelodyIndex + 1) % melodyResources.size
            mediaPlayer = MediaPlayer.create(this, melodyResources[currentMelodyIndex])
            mediaPlayer?.start()
        }
    }

    private fun stopPlayback() {
        if (mediaPlayer != null) {
            if (mediaPlayer!!.isPlaying) {
                mediaPlayer!!.stop()
            }
            mediaPlayer!!.release()
            mediaPlayer = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopPlayback()
        mediaPlayer?.release()
        mediaPlayer = null
    }

}




