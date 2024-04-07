package com.kabouzeid.gramophone.ui.jobInterview.pages

import androidx.test.espresso.matcher.ViewMatchers.*
import com.atiurin.ultron.allure.step.step
import com.atiurin.ultron.custom.espresso.matcher.withDrawable
import com.atiurin.ultron.extensions.click
import com.atiurin.ultron.extensions.hasContentDescription
import com.atiurin.ultron.extensions.hasText
import com.atiurin.ultron.extensions.isDisplayed
import com.kabouzeid.gramophone.R
import org.hamcrest.Matchers.allOf

object PlayerPage: BasePage<PlayerPage>() {
    val playerContainer = withId(R.id.player_fragment_container)
    val playerBtn = withId(R.id.player_play_pause_fab)
    val currentSongLayout = withId(R.id.current_song)
    val currentSongName = allOf(
        withId(R.id.title),
        isDescendantOfA(currentSongLayout)
    )
    val nextBtn = withId(R.id.player_next_button)
    val prevBtn = withId(R.id.player_prev_button)
    val favoriteBtn = withId(R.id.action_toggle_favorite)
    val closeBtn = withDrawable(R.drawable.ic_close_white_24dp)
    const val debugSongName = "Песенка"

    override fun isDisplayed() {
        playerContainer.isDisplayed()
    }

    fun isPlaying() {
        // если песенка играет то видна кнопка паузы и наоборот
        step("Проверить отображение кнопки 'Пауза'") {
            playerBtn.hasContentDescription("Pause")
        }
    }

    fun isPausing() {
        step("Проверить отображение кнопки 'Play'") {
            playerBtn.hasContentDescription("Play")
        }
    }

    fun clickOnPlayPause() {
        step("Тапнуть по кнопке 'Pause'") {
            playerBtn.click()
        }
    }

    fun checkCurrentSongName(name: String) {
        step("Проверить отображение название трека в мини плеере") {
            currentSongName.hasText(name)
        }
    }

    fun clickNextSongBtn() {
        step("Тапнуть на кнопку 'следующий трек'") {
            nextBtn.click()
        }
    }

    fun clickPrevSongBtn() {
        step("Тапнуть на кнопку 'предыдущий трек'") {
            prevBtn.click()
        }
    }

    fun isNotFavorite() {
        // R.drawable.ic_favorite_border_white_24dp
        // R.drawable.ic_favorite_white_24dp
        // favoriteBtn.hasAnyDrawable()
        step("Проверить отображение кнопки 'Добавить в избранное'") {
            favoriteBtn.hasContentDescription("Добавить в избранное")
        }
    }

    fun isFavorite() {
        step("Проверить отображение кнопки 'Удалить из избранного'") {
            favoriteBtn.hasContentDescription("Удалить из избранного")
        }
    }

    fun clickFavoriteBtn() {
        step("Тапнуть по кнопке добавление/удаление избранного") {
            favoriteBtn.click()
        }
    }

    fun closePlayer() {
        step("Тапнуть по крестику закрытия плеера") {
            closeBtn.click()
        }
    }
}
