package tests

import pages.*
import androidx.test.uiautomator.UiObject2
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import io.qameta.allure.kotlin.junit4.DisplayName
import org.junit.Assert
import org.junit.runner.RunWith
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter


@RunWith(AllureAndroidJUnit4::class)
@DisplayName("Тестирование страницы новостей")
class TestNewsPage() : TestBase() {

    /**
     * Открытие приложения для тестов
     */
    @Before
    fun prepareTests() {
        loadPackage()
    }

    @Test
    @DisplayName("Тестирование сортировки новостей по дате")
    fun testSortNewsByDate() {
        val newsPage = MainPage(device).clickAllNews()
        newsPage.clickNewsSort()
        val firstDate = newsPage.getNewsItemDate()
        newsPage.clickNewsSort()
        val secondDate = newsPage.getNewsItemDate()
        Assert.assertEquals(true, firstDate <= secondDate)
    }

    @Test
    @DisplayName("Тестирование открытия фильтра новостей")
    fun testOpenNewsFilter() {
        MainPage(device)
            .clickAllNews()
            .clickNewsFilter()
            .onPage()
    }

    @Test
    @DisplayName("Тестирование выбора категории в фильтре новостей")
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

    @Test
    @DisplayName("Тестирование фильтрации новостей по категории")
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


    @Test
    @DisplayName("Тестирование выбора текущей даты ОТ")
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

    @Test
    @DisplayName("Тестирование выбора следующей даты ОТ")
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

    @Test
    @DisplayName("Тестирование выбора предыдущей даты ДО")
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

    @Test
    @DisplayName("Тестирование выбора текущей даты ДО")
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

    @Test
    @DisplayName("Тестирование выбора следующей даты ДО")
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

    @Test
    @DisplayName("Тестирование попытки фильтрации с выбором только даты ОТ")
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

    @Test
    @DisplayName("Тестирование попытки фильтрации с выбором только даты ДО")
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

    @Test
    @DisplayName("Тестирование фильтрации по датам")
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

    @Test
    @DisplayName("Тестирование закрытия фильтра новостей")
    fun testFilterClose() {
        MainPage(device)
            .clickAllNews()
            .clickNewsFilter()
            .cancelFilter()
            .onPage()
    }

    @Test
    @DisplayName("Тестирование перехода на страницу управления новостями")
    fun testOpenNewsControlPanel() {
        MainPage(device)
            .clickAllNews()
            .clickNewsEdit()
            .onPage()
    }

    @Test
    @DisplayName("Тестирование удаления новости")
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

    @Test
    @DisplayName("Тестирование перехода на страницу редактирования новости")
    fun testEditNewsItem() {
        val newsPage = MainPage(device)
            .clickAllNews()
        val newsName = newsPage.getNewsItemName();
        newsPage
            .clickNewsEdit()
            .clickEditNewsItem(newsName)
            .onPage()
    }

    @Test
    @DisplayName("Тестирование перехода на страницу добавления новости")
    fun testOpenAddNewsItemPage() {
        MainPage(device)
            .clickAllNews()
            .clickNewsEdit()
            .clickAddNewsItem()
            .onPage()
    }

    @Test
    @DisplayName("Тестирование добавления новости")
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

    @Test
    @DisplayName("Тестирование отмены добавления новости")
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