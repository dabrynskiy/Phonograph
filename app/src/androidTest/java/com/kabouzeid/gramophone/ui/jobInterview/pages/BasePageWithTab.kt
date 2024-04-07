package com.kabouzeid.gramophone.ui.jobInterview.pages

import android.view.View
import com.kabouzeid.gramophone.R
import org.hamcrest.Matchers.allOf
import androidx.test.espresso.matcher.ViewMatchers.*
import com.atiurin.ultron.allure.step.step
import com.atiurin.ultron.extensions.isDisplayed
import com.atiurin.ultron.extensions.isSelected
import com.atiurin.ultron.extensions.scrollTo
import org.hamcrest.Matcher

abstract class BasePageWithTab<out T : BasePageWithTab<T>> : BasePage<@UnsafeVariance T>() {
    protected val tabs = allOf(
        withId(R.id.tabs),
        withParent(withId(R.id.appbar))
    )
    abstract val pageTabName: String

    override fun isDisplayed() {
        tabs.isDisplayed()
        getTab().isDisplayed()
        getTab().isSelected()
    }

    fun getTab(): Matcher<View> = allOf(
        withText(pageTabName),
        isDescendantOfA(tabs)
    )

    fun selectTab() {
        step("Выбрать таб '$pageTabName'") {
            getTab().scrollTo().click()
        }
    }
}