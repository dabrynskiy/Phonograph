package com.kabouzeid.gramophone.ui.jobInterview.pages

import androidx.test.espresso.matcher.ViewMatchers.*
import com.atiurin.ultron.allure.step.step
import com.atiurin.ultron.core.espresso.recyclerview.UltronRecyclerView
import com.atiurin.ultron.core.espresso.recyclerview.UltronRecyclerViewItem
import com.atiurin.ultron.extensions.isDisplayed
import com.kabouzeid.gramophone.R
import org.hamcrest.Matchers.allOf

object AddToPlayListPage: BasePage<AddToPlayListPage>() {
    val title = allOf(
        withId(R.id.md_title),
        withText("Добавить в плейлист")
    )
    private val recycler = UltronRecyclerView(
            withId(R.id.md_contentRecyclerView)
    )
    override fun isDisplayed() {
        step("Проверить отображение попапа 'добавить в плейлист'") {
            title.isDisplayed()
        }
    }

    fun clickOnMenuItem(position: Int) {
        recycler.getItem<MenuItem>(position).click()
    }

    class MenuItem: UltronRecyclerViewItem()
}