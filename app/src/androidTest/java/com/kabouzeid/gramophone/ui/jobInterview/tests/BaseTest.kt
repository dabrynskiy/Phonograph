package com.kabouzeid.gramophone.ui.jobInterview.tests

import android.Manifest
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kabouzeid.gramophone.ui.activities.MainActivity
import org.junit.Rule

open class BaseTest {
    @get:Rule
    val testRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
    )

    //PreferenceUtil.getInstance(context...).setIntroShown()
    // permission
}