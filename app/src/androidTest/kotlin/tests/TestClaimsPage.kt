package tests

import pages.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.UiObject2
import org.junit.Assert
import org.junit.runner.RunWith
import org.junit.Before
import org.junit.Test


@RunWith(AndroidJUnit4::class)
class TestClaimsPage() : TestBase() {

    /**
     * Открытие приложения для тестов
     */
    @Before
    fun prepareTests() {
        loadPackage()
    }


    /**
     * 39
     */
    @Test
    fun testOpenClaimsFilter() {
        MainPage(device)
            .clickAllClaims()
            .openClaimsFilter()
            .onPage()
    }

    /**
     * 40, 41
     */
    @Test
    fun testToggleFilterOpenCheckbox() {
        val filter = MainPage(device)
            .clickAllClaims()
            .openClaimsFilter()
            .clickOpen()
        Assert.assertEquals(false, filter.isOpenChecked())
        filter.clickOpen()
        Assert.assertEquals(true, filter.isOpenChecked())
    }

    /**
     * 42, 43
     */
    @Test
    fun testToggleFilterInProgressCheckbox() {
        val filter = MainPage(device)
            .clickAllClaims()
            .openClaimsFilter()
            .clickInProgress()
        Assert.assertEquals(false, filter.isInProgressChecked())
        filter.clickInProgress()
        Assert.assertEquals(true, filter.isInProgressChecked())
    }

    /**
     * 44, 45
     */
    @Test
    fun testToggleFilterExecutedCheckbox() {
        val filter = MainPage(device)
            .clickAllClaims()
            .openClaimsFilter()
            .clickExecuted()
        Assert.assertEquals(true, filter.isExecutedChecked())
        filter.clickExecuted()
        Assert.assertEquals(false, filter.isExecutedChecked())
    }

    /**
     * 46, 47
     */
    @Test
    fun testToggleFilterCanceledCheckbox() {
        val filter = MainPage(device)
            .clickAllClaims()
            .openClaimsFilter()
            .clickCancelled()
        Assert.assertEquals(true, filter.isCancelledChecked())
        filter.clickCancelled()
        Assert.assertEquals(false, filter.isCancelledChecked())
    }

    /**
     * 48
     */
    @Test
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

    /**
     * 49
     */
    @Test
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

    /**
     * 50
     */
    @Test
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

    /**
     * 51
     */
    @Test
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

    /**
     * 52
     */
    @Test
    fun testCloseFilter() {
        MainPage(device)
            .clickAllClaims()
            .openClaimsFilter()
            .cancelFilter()
            .onPage()
    }

    /**
     * 53
     */
    @Test
    fun testOpenAddClaimPage() {
        MainPage(device)
            .clickAllClaims()
            .clickAddNewClaim()
            .onPage()
    }

    /**
     * 54
     */
    @Test
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

    /**
     * 55
     */
    @Test
    fun testCancelAddClaim() {
        val claimsPage = MainPage(device)
            .clickAllClaims()
        claimsPage
            .clickAddNewClaim()
            .cancel()
            .confirmCancel()
        claimsPage.onPage()
    }

    /**
     * 56
     */
    @Test
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


    /**
     * 57
     */
    @Test
    fun testCancelAddClaimFromMain() {
        val mainPage = MainPage(device)
        mainPage
            .clickAddNewClaim()
            .cancel()
            .confirmCancel()
        mainPage.onPage()
    }

    /**
     * 58
     */
    @Test
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

    /**
     * 59
     */
    @Test
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

    /**
     * 60
     */
    @Test
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

    /**
     * 61
     */
    @Test
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
