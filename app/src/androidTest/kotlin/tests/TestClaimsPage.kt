package tests

import pages.*
import androidx.test.uiautomator.UiObject2
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import io.qameta.allure.kotlin.junit4.DisplayName
import org.junit.Assert
import org.junit.runner.RunWith
import org.junit.Before
import org.junit.Test


@RunWith(AllureAndroidJUnit4::class)
class TestClaimsPage() : TestBase() {

    /**
     * Открытие приложения для тестов
     */
    @Before
    fun prepareTests() {
        loadPackage()
    }

    @Test
    @DisplayName("Тестирование открытия фильтра заявок")
    fun testOpenClaimsFilter() {
        MainPage(device)
            .clickAllClaims()
            .openClaimsFilter()
            .onPage()
    }

    @Test
    @DisplayName("Тестирование переключения чекбокса Open")
    fun testToggleFilterOpenCheckbox() {
        val filter = MainPage(device)
            .clickAllClaims()
            .openClaimsFilter()
            .clickOpen()
        Assert.assertEquals(false, filter.isOpenChecked())
        filter.clickOpen()
        Assert.assertEquals(true, filter.isOpenChecked())
    }

    @Test
    @DisplayName("Тестирование переключения чекбокса InProgress")
    fun testToggleFilterInProgressCheckbox() {
        val filter = MainPage(device)
            .clickAllClaims()
            .openClaimsFilter()
            .clickInProgress()
        Assert.assertEquals(false, filter.isInProgressChecked())
        filter.clickInProgress()
        Assert.assertEquals(true, filter.isInProgressChecked())
    }

    @Test
    @DisplayName("Тестирование переключения чекбокса Executed")
    fun testToggleFilterExecutedCheckbox() {
        val filter = MainPage(device)
            .clickAllClaims()
            .openClaimsFilter()
            .clickExecuted()
        Assert.assertEquals(true, filter.isExecutedChecked())
        filter.clickExecuted()
        Assert.assertEquals(false, filter.isExecutedChecked())
    }

    @Test
    @DisplayName("Тестирование переключения чекбокса Cancelled")
    fun testToggleFilterCanceledCheckbox() {
        val filter = MainPage(device)
            .clickAllClaims()
            .openClaimsFilter()
            .clickCancelled()
        Assert.assertEquals(true, filter.isCancelledChecked())
        filter.clickCancelled()
        Assert.assertEquals(false, filter.isCancelledChecked())
    }

    @Test
    @DisplayName("Тестирование фильтрации Open")
    fun testFilterOpen() {
        val statusText = MainPage(device)
            .clickAllClaims()
            .openClaimsFilter()
            .clickInProgress()
            .applyFilter()
            .openClaimsItem()
            .getStatusText()
        Assert.assertEquals("Open", statusText)
    }

    @Test
    @DisplayName("Тестирование фильтрации InProgress")
    fun testFilterInProgress() {
        val statusText = MainPage(device)
            .clickAllClaims()
            .openClaimsFilter()
            .clickOpen()
            .applyFilter()
            .openClaimsItem()
            .getStatusText()
        Assert.assertEquals("In progress", statusText)
    }

    @Test
    @DisplayName("Тестирование фильтрации Executed")
    fun testFilterExecuted() {
        val statusText = MainPage(device)
            .clickAllClaims()
            .openClaimsFilter()
            .clickOpen()
            .clickInProgress()
            .clickExecuted()
            .applyFilter()
            .openClaimsItem()
            .getStatusText()
        Assert.assertEquals("Executed", statusText)
    }

    @Test
    @DisplayName("Тестирование фильтрации Cancelled")
    fun testFilterCanceled() {
        val statusText = MainPage(device)
            .clickAllClaims()
            .openClaimsFilter()
            .clickOpen()
            .clickInProgress()
            .clickCancelled()
            .applyFilter()
            .openClaimsItem()
            .getStatusText()
        Assert.assertEquals("Canceled", statusText)
    }

    @Test
    @DisplayName("Тестирование закрытия фильтра")
    fun testCloseFilter() {
        MainPage(device)
            .clickAllClaims()
            .openClaimsFilter()
            .cancelFilter()
            .onPage()
    }

    @Test
    @DisplayName("Тестирование открытия страницы добавления заявки")
    fun testOpenAddClaimPage() {
        MainPage(device)
            .clickAllClaims()
            .clickAddNewClaim()
            .onPage()
    }

    @Test
    @DisplayName("Тестирование добавления заявки")
    fun testAddClaim() {
        val claimName =  "New claim 123"
        val claimsPage = MainPage(device)
            .clickAllClaims()
        claimsPage
            .clickAddNewClaim()
            .setTitle(claimName)
            .setExecutor("Ivanov Ivan Ivanovich")
            .setDate()
            .setTime()
            .setDescription("New claim 123 Description")
            .saveClaim()
        val claimsItem = claimsPage.getClaimsItem(claimName);
        Assert.assertEquals(true, claimsItem is UiObject2)
    }

    @Test
    @DisplayName("Тестирование отмена добавления заявки")
    fun testCancelAddClaim() {
        val claimsPage = MainPage(device)
            .clickAllClaims()
        claimsPage
            .clickAddNewClaim()
            .cancel()
            .confirmCancel()
        claimsPage.onPage()
    }

    @Test
    @DisplayName("Тестирование добавления заявки с главной страницы")
    fun testAddClaimFromMain() {
        val claimName =  "New claim 123"
        val mainPage = MainPage(device)
        mainPage
            .clickAddNewClaim()
            .setTitle(claimName)
            .setExecutor("Ivanov Ivan Ivanovich")
            .setDate()
            .setTime()
            .setDescription("New claim 123 Description")
            .saveClaim()
        val claimsItem = mainPage
            .clickAllClaims()
            .getClaimsItem(claimName);
        Assert.assertEquals(true, claimsItem is UiObject2)
    }


    @Test
    @DisplayName("Тестирование отмены добавления заявки с главной страницы")
    fun testCancelAddClaimFromMain() {
        val mainPage = MainPage(device)
        mainPage
            .clickAddNewClaim()
            .cancel()
            .confirmCancel()
        mainPage.onPage()
    }

    @Test
    @DisplayName("Тестирование редактирования заявки со статусом Open")
    fun testEditOpen() {
        MainPage(device)
            .clickAllClaims()
            .openClaimsFilter()
            .clickInProgress()
            .applyFilter()
            .openClaimsItem()
            .clickEdit()
            .onPage()
    }

    @Test
    @DisplayName("Тестирование редактирования заявки со статусом InProgress")
    fun testEditInProgress() {
        val claimItemPage = MainPage(device)
            .clickAllClaims()
            .openClaimsFilter()
            .clickOpen()
            .applyFilter()
            .openClaimsItem()
        claimItemPage.clickEdit()
        claimItemPage.onPage()
    }

    @Test
    @DisplayName("Тестирование редактирования заявки со статусом Executed")
    fun testEditExecuted() {
        val claimItemPage = MainPage(device)
            .clickAllClaims()
            .openClaimsFilter()
            .clickOpen()
            .clickInProgress()
            .clickExecuted()
            .applyFilter()
            .openClaimsItem()
        claimItemPage.clickEdit()
        claimItemPage.onPage()
    }

    @Test
    @DisplayName("Тестирование редактирования заявки со статусом Cancelled")
    fun testEditCanceled() {
        val claimItemPage = MainPage(device)
            .clickAllClaims()
            .openClaimsFilter()
            .clickOpen()
            .clickInProgress()
            .clickCancelled()
            .applyFilter()
            .openClaimsItem()
        claimItemPage.clickEdit()
        claimItemPage.onPage()

    }
}
