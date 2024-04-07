package com.kabouzeid.gramophone.ui.jobInterview.pages

import androidx.test.espresso.matcher.ViewMatchers.*
import com.atiurin.ultron.allure.step.step
import com.atiurin.ultron.extensions.click
import com.atiurin.ultron.extensions.hasContentDescription
import com.atiurin.ultron.extensions.hasText
import com.atiurin.ultron.extensions.isDisplayed
import com.kabouzeid.gramophone.R

object MiniPlayerPage: BasePage<MiniPlayerPage>() {
    val image = withId(R.id.mini_player_image)
    val title = withId(R.id.mini_player_title)
    val playPauseButton = withId(R.id.mini_player_play_pause_button)
    val miniPlayerFragment = withId(R.id.mini_player_fragment)
    const val debugSongName = "Песенка"

    override fun isDisplayed() {
        step("Проверить отображение мини-плеера") {
            miniPlayerFragment.isDisplayed()
        }
    }

    fun hasSongName(name: String) {
        step("Проверить отображение название трека в мини плеере") {
            title.hasText(name)
        }
    }
    fun isPlaying() {
        // если песенка играет то видна кнопка паузы и наоборот
        step("Проверить отображение кнопки 'Пауза'") {
            playPauseButton.hasContentDescription("Pause")
        }
    }

    fun isPausing() {
        step("Проверить отображение кнопки 'Play'") {
            playPauseButton.hasContentDescription("Play")
        }
    }

    fun clickOnPlayPause() {
        step("Тапнуть по кнопке 'Pause'") {
            playPauseButton.click()
        }
    }

    fun openPlayer() {
        step("Открыть плеер") {
            miniPlayerFragment.click()
        }
    }
}