package com.canonicalexamples.testfragmentmenu.ui.main

import android.os.Build
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.canonicalexamples.testfragmentmenu.R
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.quality.Strictness
import org.robolectric.annotation.Config

/**
 * 20191030. Initial version created by jorge
 * for a Canonical Examples training.
 *
 *
 * Copyright 2019 Jorge D. Ortiz Fuentes
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class MainFragmentTest {

    @get:Rule val rule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)
    @Mock private lateinit var viewModel: MainViewModel
    private lateinit var sut: FragmentScenario<MainFragment>

    @Before
    fun setUp() {
        loadKoinModules(module(override = true) {
            factory { viewModel }
        })
    }

    @Test
    fun saveButtonIsEnabledWithViewModelProperty() {
        val enabledStatus = MutableLiveData<Boolean>(false)
        whenever(viewModel.saveButtonEnable).thenReturn(enabledStatus)
        sut = launchFragmentInContainer()

        enabledStatus.value = true

        onView(withId(R.id.save_button)).check(matches(isEnabled()))
    }
}
