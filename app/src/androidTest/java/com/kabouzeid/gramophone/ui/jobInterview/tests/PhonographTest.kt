package com.kabouzeid.gramophone.ui.jobInterview.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kabouzeid.gramophone.ui.jobInterview.pages.AddToPlayListPage
import com.kabouzeid.gramophone.ui.jobInterview.pages.MiniPlayerPage
import com.kabouzeid.gramophone.ui.jobInterview.pages.NewPlayListPage
import com.kabouzeid.gramophone.ui.jobInterview.pages.PlayListPage
import com.kabouzeid.gramophone.ui.jobInterview.pages.PlayListsPage
import com.kabouzeid.gramophone.ui.jobInterview.pages.PlayerPage
import com.kabouzeid.gramophone.ui.jobInterview.pages.SongsPage
import io.qameta.allure.kotlin.junit4.DisplayName
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PhonographTest: BaseTest() {

    @Test
    @DisplayName("Тест Play и Pause в мини плеере")
    fun playPauseMiniPlayerTest() {
        testRule.run {
            SongsPage {
                selectTab()
                isDisplayed()
                clickOnSongItem(1)
            }

            MiniPlayerPage {
                isDisplayed()
                hasSongName(debugSongName)
                isPlaying()
                clickOnPlayPause()
                isPausing()
                clickOnPlayPause()
                isPlaying()
            }
        }
    }

    @Test
    @DisplayName("Тест Play и Pause в плеере")
    fun playPausePlayerTest() {
        testRule.run {
            SongsPage {
                selectTab()
                isDisplayed()
                clickOnSongItem(1)
            }

            MiniPlayerPage {
                openPlayer()
            }

            PlayerPage {
                isDisplayed()
                checkCurrentSongName(debugSongName)
                isPlaying()
                clickOnPlayPause()
                isPausing()
                clickOnPlayPause()
                isPlaying()
            }
        }
    }

    @Test
    @DisplayName("Переключение треков в плеере")
    fun switchSongTest() {
        testRule.run {
            SongsPage {
                selectTab()
                isDisplayed()
                clickOnSongItem(1)
            }

            MiniPlayerPage {
                openPlayer()
            }

            PlayerPage {
                isDisplayed()
                checkCurrentSongName(debugSongName)
                // + проверить название сл песни - image_text = 1
                clickNextSongBtn()
                checkCurrentSongName("$debugSongName 2")
                // + проверить название пред песни - image_text = -1
                clickPrevSongBtn()
                checkCurrentSongName(debugSongName)
            }
        }
    }

    @Test
    @DisplayName("Добавление и удаление трека в избранное/из избранного в плеере")
    // а еще из других мест можно добавлять/ удалять
    fun favoriteTest() {
        testRule.run {
            SongsPage {
                selectTab()
                isDisplayed()
                clickOnSongItem(1)
            }

            MiniPlayerPage {
                openPlayer()
            }

            PlayerPage {
                isDisplayed()
                isNotFavorite()
                clickFavoriteBtn()
                isFavorite()
                clickFavoriteBtn()
                isNotFavorite()
                clickFavoriteBtn()
                closePlayer()
            }

            PlayListsPage {
                selectTab()
                isDisplayed()
                clickOnPlayList(3)
            }

            PlayListPage {
                isDisplayed()
                hasSong(debugSongName)
                deleteFromPlayList(debugSongName)
                hasNotSong(debugSongName)
            }
        }
    }

    @Test
    @DisplayName("Добавление и удаление трека в новый плейлист")
    // еще можно в уже созданный плейлист
    fun playListTest() {
        testRule.run {
            SongsPage {
                selectTab()
                isDisplayed()
                clickOnSongMenu(1)
                clickOnMenuItem("Добавить в плейлист...")
            }

            AddToPlayListPage {
                isDisplayed()
                clickOnMenuItem(0)
            }

            NewPlayListPage {
                isDisplayed()
                typePlaylistName("Сектор Газа")
                clickOnCreateBtn()
            }

            PlayListsPage {
                selectTab()
                clickOnPlayList(4)
            }

            PlayListPage {
                isDisplayed()
                hasSong(debugSongName)
                deleteFromPlayList(debugSongName)
                hasNotSong(debugSongName)
            }
        }
    }

    @Test
    @DisplayName("Переименование плейлиста")
    fun renamePlayListTest() {
        testRule.run {
            SongsPage {
                selectTab()
                isDisplayed()
                clickOnSongMenu(1)
                clickOnMenuItem("Добавить в плейлист...")
            }

            AddToPlayListPage {
                isDisplayed()
                clickOnMenuItem(0)
            }

            NewPlayListPage {
                isDisplayed()
                typePlaylistName("Сектор Газа")
                clickOnCreateBtn()
            }

            PlayListsPage {
                selectTab()
                hasPlayList("Сектор Газа")
                renamePlayList("Сектор Газа", "Сектор Газа1")
                hasPlayList("Сектор Газа1")
            }
        }
    }

    @Test
    @DisplayName("Удаление плейлиста")
    fun deletePlayListTest() {
        testRule.run {
            SongsPage {
                selectTab()
                isDisplayed()
                clickOnSongMenu(1)
                clickOnMenuItem("Добавить в плейлист...")
            }

            AddToPlayListPage {
                isDisplayed()
                clickOnMenuItem(0)
            }

            NewPlayListPage {
                isDisplayed()
                typePlaylistName("Сектор Газа")
                clickOnCreateBtn()
            }

            PlayListsPage {
                selectTab()
                hasPlayList("Сектор Газа")
                deletePlayList("Сектор Газа")
                hasNotPlayList("Сектор Газа")
            }
        }
    }
}