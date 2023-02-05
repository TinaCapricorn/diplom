package tests

import io.qameta.allure.android.runners.AllureAndroidJUnit4
import io.qameta.allure.kotlin.junit4.DisplayName
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import pages.MainPage


@RunWith(AllureAndroidJUnit4::class)
@DisplayName("Тестирование главной страницы приложения")
class TestMainPage() : TestBase() {

    /**
     * Открытие приложения для тестов
     */
    @Before
    fun prepareTests() {
        loadPackage()
    }

    @Test
    @DisplayName("Тестирование перехода на страницу заявок из меню")
    fun testMenuClaimsButton() {
        MainPage(device)
            .clickMainMenu()
            .clickClaimsButton()
            .onPage()
    }

    @Test
    @DisplayName("Тестирование перехода на страницу новостей из меню")
    fun testMenuNewsButton() {
        MainPage(device)
            .clickMainMenu()
            .clickNewsButton()
            .onPage()
    }

    @Test
    @DisplayName("Тестирование перехода на страницу миссии")
    fun testMissionButton() {
        MainPage(device)
            .clickMissionButton()
            .onPage()
    }

    @Test
    @DisplayName("Тестирование кнопки сворачивания и разворачивания новостей")
    fun testExpandCollapseNews() {
        MainPage(device)
            .toggleNews()
            .isNewsVisible(false)
            .toggleNews()
            .isNewsVisible(true)
    }

    @Test
    @DisplayName("Тестирование кнопки сворачивания и разворачивания заявок")
    fun testExpandCollapseClaims() {
        MainPage(device)
            .toggleClaims()
            .isClaimsVisible(false)
            .toggleClaims()
            .isClaimsVisible(true)
    }

    @Test
    @DisplayName("Тестирование перехода на страницу новостей")
    fun testAllNewsButton() {
        MainPage(device)
            .clickAllNews()
            .onPage()
    }

    @Test
    @DisplayName("Тестирование перехода на страницу заявок")
    fun testAllClaimsButton() {
        MainPage(device)
            .clickAllClaims()
            .onPage()
    }

    @Test
    @DisplayName("Тестирование сворачивания и разворачивания отедльной новости кликом по стрелке")
    fun testExpandCollapseArrowNews() {
        MainPage(device)
            .clickNewsItemArrow()
            .newsItemTextIsVisible(true)
            .clickNewsItemArrow()
            .newsItemTextIsVisible(false)
    }

    @Test
    @DisplayName("Тестирование сворачивания и разворачивания отедльной новости кликом по заголовку")
    fun testExpandCollapseTitleNews() {
        MainPage(device)
            .clickNewsItemTitle()
            .newsItemTextIsVisible(true)
            .clickNewsItemTitle()
            .newsItemTextIsVisible(false)
    }

    @Test
    @DisplayName("Тестирование открытия заявки кликом по стрелке")
    fun testOpenClaimByArrowClick() {
        MainPage(device)
            .clickClaimItemArrow()
            .onPage()
    }

    @Test
    @DisplayName("Тестирование открытия заявки кликом по заголовку")
    fun testOpenClaimByTopicClick() {
        MainPage(device)
            .clickClaimItemTitle()
            .onPage()
    }

    @Test
    @DisplayName("Тестирование открытия заявки кликом по описанию")
    fun testOpenClaimByDetailsClick() {
        MainPage(device)
            .clickClaimItemDetails()
            .onPage()
    }

    @Test
    @DisplayName("Тестирование открытия страницы добавления новой заявки")
    fun testOpenAddNewClaim() {
        MainPage(device)
            .clickAddNewClaim()
            .onPage()
    }
}