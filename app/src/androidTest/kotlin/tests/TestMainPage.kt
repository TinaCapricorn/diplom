package tests

import pages.MainPage
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import org.junit.Before
import org.junit.Test


@RunWith(AndroidJUnit4::class)
class TestMainPage() : TestBase() {

    /**
     * Открытие приложения для тестов
     */
    @Before
    fun prepareTests() {
        loadPackage()
    }

    /**
     * 1
     */
    @Test
    fun testMenuClaimsButton() {
        MainPage(device)
            .clickMainMenu()
            .clickClaimsButton()
            .onPage()
    }

    /**
     * 2
     */
    @Test
    fun testMenuNewsButton() {
        MainPage(device)
            .clickMainMenu()
            .clickNewsButton()
            .onPage()
    }

    /**
     * 3
     */
    @Test
    fun testMissionButton() {
        MainPage(device)
            .clickMissionButton()
            .onPage()
    }

    /**
     * 4,5
     */
    @Test
    fun testExpandCollapseNews() {
        MainPage(device)
            .toggleNews()
            .isNewsVisible(false)
            .toggleNews()
            .isNewsVisible(true)
    }

    /**
     * 6,7
     */
    @Test
    fun testExpandCollapseClaims() {
        MainPage(device)
            .toggleClaims()
            .isClaimsVisible(false)
            .toggleClaims()
            .isClaimsVisible(true)
    }

    /**
     * 8
     */
    @Test
    fun testAllNewsButton() {
        MainPage(device)
            .clickAllNews()
            .onPage()
    }

    /**
     * 9
     */
    @Test
    fun testAllClaimsButton() {
        MainPage(device)
            .clickAllClaims()
            .onPage()
    }

    /**
     * 10, 12
     */
    @Test
    fun testExpandCollapseArrowNews() {
        MainPage(device)
            .clickNewsItemArrow()
            .newsItemTextIsVisible(true)
            .clickNewsItemArrow()
            .newsItemTextIsVisible(false)
    }

    /**
     * 11, 13
     */
    @Test
    fun testExpandCollapseTitleNews() {
        MainPage(device)
            .clickNewsItemTitle()
            .newsItemTextIsVisible(true)
            .clickNewsItemTitle()
            .newsItemTextIsVisible(false)
    }

    /**
     * 14
     */
    @Test
    fun testOpenClaimByArrowClick() {
        MainPage(device)
            .clickClaimItemArrow()
            .onPage()
    }

    /**
     * 15
     */
    @Test
    fun testOpenClaimByTopicClick() {
        MainPage(device)
            .clickClaimItemTitle()
            .onPage()
    }

    /**
     * 16
     */
    @Test
    fun testOpenClaimByDetailsClick() {
        MainPage(device)
            .clickClaimItemDetails()
            .onPage()
    }

    /**
     * 17
     */
    @Test
    fun testOpenAddNewClaim() {
        MainPage(device)
            .clickAddNewClaim()
            .onPage()
    }


}