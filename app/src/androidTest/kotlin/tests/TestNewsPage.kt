package tests

import pages.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.UiObject2
import org.junit.Assert
import org.junit.runner.RunWith
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter


@RunWith(AndroidJUnit4::class)
class TestNewsPage() : TestBase() {

    /**
     * Открытие приложения для тестов
     */
    @Before
    fun prepareTests() {
        loadPackage()
    }

    /**
     * 18, 19
     */
    @Test
    fun testSortNewsByDate() {
        val newsPage = MainPage(device).clickAllNews()
        newsPage.clickNewsSort()
        val firstDate = newsPage.getNewsItemDate()
        newsPage.clickNewsSort()
        val secondDate = newsPage.getNewsItemDate()
        Assert.assertEquals(true, firstDate <= secondDate)
    }

    /**
     * 20
     */
    @Test
    fun testOpenNewsFilter() {
        MainPage(device)
            .clickAllNews()
            .clickNewsFilter()
            .onPage()
    }

    /**
     * 21
     */
    @Test
    fun testNewsFilterCategory() {
        val categoryName = "День рождения"
        val actualCategoryName = MainPage(device)
            .clickAllNews()
            .clickNewsFilter()
            .openFilterCategory()
            .clickFilterCategory(categoryName)
            .getFilterCategory()
        Assert.assertEquals(categoryName, actualCategoryName)
    }

    /**
     * 22
     */
    @Test
    fun testApplyNewsFilterCategory() {
        val categoryName = "День рождения"
        val newsPage = MainPage(device)
            .clickAllNews()
            .clickNewsFilter()
            .openFilterCategory()
            .clickFilterCategory(categoryName)
            .applyFilter()
        val newsName = newsPage.getNewsItemName()
        val actualCategoryName = newsPage
            .clickNewsEdit()
            .clickEditNewsItem(newsName)
            .getCategory()
        Assert.assertEquals(categoryName, actualCategoryName)
    }


    /**
     * 24
     */
    @Test
    fun testSelectFromCurrentDate() {
        val formatter = DateTimeFormatter.ofPattern("dd")
        val fullDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val currentDate = LocalDateTime.now()
        val currentDay = currentDate.format(formatter)
        val page = MainPage(device)
            .clickAllNews()
            .clickNewsFilter()
        val datePicker = page.openDateStartFilter()
        datePicker.selectDay(currentDay)
            .confirm()
        Assert.assertEquals(currentDate.format(fullDateFormatter), page.getDateStart())
    }

    /**
     * 25
     */
    @Test
    fun testSelectFromNextDate() {
        val formatter = DateTimeFormatter.ofPattern("dd")
        val fullDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val currentDate = LocalDateTime.now()
        val period = Period.of(0, 0, 1)
        val nextDay = currentDate.plus(period).format(formatter)
        val page = MainPage(device)
            .clickAllNews()
            .clickNewsFilter()
        val datePicker = page.openDateStartFilter()
        datePicker.selectDay(nextDay)
            .confirm()
        Assert.assertEquals(currentDate.plus(period).format(fullDateFormatter), page.getDateStart())
    }

    /**
     * 26
     */
    @Test
    fun testSelectToPreviousDate() {
        val formatter = DateTimeFormatter.ofPattern("dd")
        val fullDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val currentDate = LocalDateTime.now()
        val period = Period.of(0, 0, 1)
        val previousDay = currentDate.minus(period).format(formatter)
        val page = MainPage(device)
            .clickAllNews()
            .clickNewsFilter()
        val datePicker = page.openDateEndFilter()
        datePicker.selectDay(previousDay)
            .confirm()
        Assert.assertEquals(currentDate.minus(period).format(fullDateFormatter), page.getDateEnd())
    }

    /**
     * 27
     */
    @Test
    fun testSelectToCurrentDate() {
        val formatter = DateTimeFormatter.ofPattern("dd")
        val fullDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val currentDate = LocalDateTime.now()
        val currentDay = currentDate.format(formatter)
        val page = MainPage(device)
            .clickAllNews()
            .clickNewsFilter()
        val datePicker = page.openDateEndFilter()
        datePicker.selectDay(currentDay)
            .confirm()
        Assert.assertEquals(currentDate.format(fullDateFormatter), page.getDateEnd())
    }

    /**
     * 28
     */
    @Test
    fun testSelectToNextDate() {
        val formatter = DateTimeFormatter.ofPattern("dd")
        val fullDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val currentDate = LocalDateTime.now()
        val period = Period.of(0, 0, 1)
        val nextDay = currentDate.plus(period).format(formatter)
        val page = MainPage(device)
            .clickAllNews()
            .clickNewsFilter()
        val datePicker = page.openDateEndFilter()
        datePicker.selectDay(nextDay)
            .confirm()
        Assert.assertEquals(currentDate.plus(period).format(fullDateFormatter), page.getDateEnd())
    }

    /**
     * 29
     */
    @Test
    fun testFilterFromDate() {
        val page = MainPage(device)
            .clickAllNews()
            .clickNewsFilter()
        val datePicker = page.openDateStartFilter()
        datePicker.confirm()
        page.applyFilter()
        val hasError = page.hasWrongPeriodError()
        Assert.assertEquals(true, hasError)
    }

    /**
     * 30
     */
    @Test
    fun testFilterToDate() {
        val page = MainPage(device)
            .clickAllNews()
            .clickNewsFilter()
        val datePicker = page.openDateEndFilter()
        datePicker.confirm()
        page.applyFilter()
        val hasError = page.hasWrongPeriodError()
        Assert.assertEquals(true, hasError)
    }

    /**
     * 31
     */
    @Test
    fun testFilterBothDates() {
        val formatter = DateTimeFormatter.ofPattern("dd")
        val currentDate = LocalDateTime.now()
        val period = Period.of(0, 0, 1)
        val prevDay = currentDate.minus(period).format(formatter)
        val nextDay = currentDate.plus(period).format(formatter)
        val page = MainPage(device)
            .clickAllNews()
            .clickNewsFilter()
        var datePicker = page.openDateStartFilter()
        datePicker.selectDay(prevDay).confirm()
        datePicker = page.openDateEndFilter()
        datePicker.selectDay(nextDay).confirm()
        page.applyFilter().onPage()
    }

    /**
     * 32
     */
    @Test
    fun testFilterClose() {
        MainPage(device)
            .clickAllNews()
            .clickNewsFilter()
            .cancelFilter()
            .onPage()
    }

    /**
     * 33
     */
    @Test
    fun testOpenNewsControlPanel() {
        MainPage(device).clickAllNews()
            .clickNewsEdit()
            .onPage()
    }

    /**
     * 34
     */
    @Test
    fun testDeleteNewsItem() {
        val newsPage = MainPage(device)
            .clickAllNews()
        val newsName = newsPage.getNewsItemName()
        newsPage
            .clickNewsEdit()
            .clickDeleteNewsItem(newsName)
            .confirmDelete()
            .onPage()
    }

    /**
     * 35
     */
    @Test
    fun testEditNewsItem() {
        val newsName = "День рождения"
        MainPage(device)
            .clickAllNews()
            .clickNewsEdit()
            .clickEditNewsItem(newsName)
            .onPage()
    }

    /**
     * 36
     */
    @Test
    fun testOpenAddNewsItemPage() {
        MainPage(device)
            .clickAllNews()
            .clickNewsEdit()
            .clickAddNewsItem()
            .onPage()
    }

    /**
     * 37
     */
    @Test
    fun testAddNewsItem() {
        val categoryName = "День рождения"
        val addedNews = MainPage(device)
            .clickAllNews()
            .clickNewsEdit()
            .clickAddNewsItem()
            .setCategory(categoryName)
            .setTitle(categoryName)
            .setDate()
            .setTime()
            .saveNews()
            .getNewsItem(categoryName)
        Assert.assertEquals(true, addedNews is UiObject2)
    }

    /**
     * 38
     */
    @Test
    fun testCancelAddNewsItem() {
        MainPage(device)
            .clickAllNews()
            .clickNewsEdit()
            .clickAddNewsItem()
            .cancel()
            .confirmCancel()
            .onPage()
    }

}