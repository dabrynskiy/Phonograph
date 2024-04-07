package com.kabouzeid.gramophone.ui.jobInterview.pages

import com.kabouzeid.gramophone.R
import androidx.test.espresso.matcher.ViewMatchers.*
import com.atiurin.ultron.allure.step.step
import com.atiurin.ultron.core.espresso.recyclerview.UltronRecyclerView
import com.atiurin.ultron.core.espresso.recyclerview.UltronRecyclerViewItem
import com.atiurin.ultron.extensions.click
import com.atiurin.ultron.extensions.hasText
import com.atiurin.ultron.extensions.isDisplayed
import org.hamcrest.Matchers.*

object PlayListPage: BasePage<PlayListPage>() {
    val emptyText = withId(android.R.id.empty)

    private val songsList = UltronRecyclerView(
        allOf(
            withId(R.id.recycler_view),
            hasSibling( emptyText )
        )
    )

    const val debugSongName = "Песенка"

    override fun isDisplayed() {
        step("Проверить отображение деталки плейлиста") {
            emptyText.hasText("Пустой плейлист")
        }
    }

    fun hasSong(songName: String) {
        step("Проверить что трек $songName отображается в плейлисте") {
            songsList.getItem<SongItem>(
                hasDescendant(
                    allOf(
                        withId(R.id.title),
                        withText(songName)
                    )
                )
            ).scrollToItem().isDisplayed()
        }
    }

    fun hasNotSong(songName: String) {
        step("Проверить что трек $songName не отображается в плейлисте") {
            runCatching {
                songsList.assertItemNotExist(
                    matcher = hasDescendant(
                        allOf(
                            withId(R.id.title),
                            withText(songName)
                        )
                    ),
                    timeoutMs = 1_000
                )
            }.onFailure { emptyText.isDisplayed() }
        }
    }

    fun clickOnSongMenu(songName: String) {
        step("Тапнуть на меню трека $songName в плейлисте") {
            songsList.getItem<SongItem>(
                hasDescendant(
                    allOf(
                        withId(R.id.title),
                        withText(songName)
                    )
                )
            ).menu.click()
        }
    }

    fun deleteFromPlayList(songName: String) {
        clickOnSongMenu(songName)
        step("Тапнуть на пункт меню 'Удалить из плейлиста'") {
            withText("Удалить из плейлиста").click()
        }
        step("Тапнуть на кнопку УБРАТЬ") {
            withText("УБРАТЬ").click()
        }
    }

    class SongItem: UltronRecyclerViewItem() {
        val menu by lazy { getChild(withId(R.id.menu)) }
    }
}