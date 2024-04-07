package com.kabouzeid.gramophone.ui.jobInterview.pages

import androidx.test.espresso.matcher.ViewMatchers.*
import com.atiurin.ultron.allure.step.step
import com.atiurin.ultron.core.espresso.recyclerview.UltronRecyclerView
import com.atiurin.ultron.core.espresso.recyclerview.UltronRecyclerViewItem
import com.atiurin.ultron.extensions.click
import com.atiurin.ultron.extensions.replaceText
import com.kabouzeid.gramophone.R
import org.hamcrest.Matchers

object PlayListsPage: BasePageWithTab<PlayListsPage>() {
    override val pageTabName: String = "ПЛЕЙЛИСТЫ"
    val emptyText = withText("Нет плейлистов для отображения")

    private val playListsRecycler = UltronRecyclerView(
        Matchers.allOf(
            withId(R.id.recycler_view),
            hasSibling(emptyText)
        )
    )

    fun clickOnPlayList(position: Int) {
        step("Тапнуть по $position-му треку") {
            getPlayList(position).click()
        }
    }

    fun clickOnPlayListMenu(position: Int) {
        step("Кликнуть на иконку меню плейлиста") {
            getPlayList(position).menu.click()
        }
    }

    fun clickOnPlayListMenu(name: String) {
        step("Кликнуть на иконку меню плейлиста") {
            playListsRecycler.getItem<PlayListsItem>(
                hasDescendant(
                    withText(name)
                )
            ).menu.click()
        }
    }

    fun getPlayList(position: Int) = playListsRecycler.getItem<PlayListsItem>(position)

    fun hasPlayList(name: String) {
        Thread.sleep(2000) // исправить
        playListsRecycler.getItem<PlayListsItem>(
            hasDescendant(
                withText(name)
            )
        ).isDisplayed()
    }

    fun hasNotPlayList(name: String) {
        step("Проверить что плейлист $name не отображается") {
            playListsRecycler.assertItemNotExistImmediately(
                matcher = hasDescendant(
                    hasDescendant(
                        withText(name)
                    )
                ),
                timeoutMs = 1_000
            )
        }
    }

    fun renamePlayList(oldName: String, newName: String) {
        clickOnPlayListMenu(oldName)
        step("Тапнуть по пункту меню 'Переименовать'") {
            withText("Переименовать").click()
        }
        step("Ввести новое название плейлиста") {
            withId(android.R.id.input).replaceText(newName)
        }
        step("Тапнуть на кнопку ПЕРЕИМЕНОВАТЬ") {
            withText("ПЕРЕИМЕНОВАТЬ").click()
        }
    }

    fun deletePlayList(name: String) {
        clickOnPlayListMenu(name)
        step("Тапнуть по пункту меню 'Удалить'") {
            withText("Удалить").click()
        }
        step("Тапнуть на кнопку УДАЛИТЬ") {
            withText("УДАЛИТЬ").click()
        }
    }

    class PlayListsItem: UltronRecyclerViewItem() {
        val menu by lazy { getChild(withId(R.id.menu)) }
    }
}