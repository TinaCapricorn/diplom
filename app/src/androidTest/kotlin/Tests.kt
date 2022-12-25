import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*
import org.junit.FixMethodOrder
import org.junit.runners.MethodSorters

const val MODEL_PACKAGE = "ru.iteco.fmhandroid"

const val TIMEOUT = 5000L


@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class Tests {

    /**
     * Девайс
     */
    private lateinit var device: UiDevice

    /**
     * Ожидание открытия приложения [packageName]
     */
    private fun waitForPackage(packageName: String) {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(packageName)), TIMEOUT)
    }

    /**
     * Полуичить элемент содержащий [text]
     */
    private fun getElementByText(text: String): UiObject2? {
        device.wait(Until.hasObject(By.text(text)), TIMEOUT)
        return device.findObject(By.text(text))
    }

    /**
     * Полуичить элемент с [id]
     */
    private fun getElementById(id: String): UiObject2? {
        val resId = MODEL_PACKAGE.plus(":id/").plus(id)
        device.wait(Until.hasObject(By.res(resId)), TIMEOUT)
        return device.findObject(By.res(resId))
    }

    /**
     * Полуичить элемент с [id] внутри [parent]
     */
    private fun getElementById(id: String, parent: UiObject2?): UiObject2? {
        val resId = MODEL_PACKAGE.plus(":id/").plus(id)
        device.wait(Until.hasObject(By.res(resId)), TIMEOUT)
        return parent?.findObject(By.res(resId))
    }

    /**
     * Скролл к блоку содержащему [text]
     */
    private fun scrollToByText(text: String) {
        if (getElementByText(text) == null) {
           val appViews = UiScrollable(UiSelector().scrollable(true))
           appViews.scrollIntoView(UiSelector().text(text))
        }
    }

    /**
     * Скролл к блоку с [id]
     */
    private fun scrollToById(id: String) {
        if (getElementById(id) == null) {
           val appViews = UiScrollable(UiSelector().scrollable(true))
           appViews.scrollIntoView(UiSelector().resourceId(id))
        }
    }

    /**
     * Открытие приложения для тестов
     */
    @Before
    fun prepareTest() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressHome()
        val launcherPackage = device.launcherPackageName
        device.wait(Until.hasObject(By.pkg(launcherPackage)), TIMEOUT)
        waitForPackage(MODEL_PACKAGE)
    }

    /**
     * Тестирование авторизации в приложении
     */
    @Test
    fun test_00_Login() {
        val loginField = getElementByText("Login")
        if (loginField != null) {
            loginField.text = "login2"
            getElementByText("Password")?.text = "password2"
            getElementById("enter_button")?.click()
        }
        val main = getElementById("main_swipe_refresh")
        assertEquals(true, main is UiObject2)
    }

    /**
     * 1
     */
    @Test
    fun test_01_ClaimsButton() {
        getElementById("main_menu_image_button")?.click()
        getElementByText("Claims")?.click()
        val container = getElementByText("Claims")
        assertEquals(true, container is UiObject2)
    }

    /**
     * 2
     */
    @Test
    fun test_02_NewsButton() {
        getElementById("main_menu_image_button")?.click()
        getElementByText("News")?.click()
        val container = getElementByText("News")
        assertEquals(true, container is UiObject2)
    }

    /**
     * 3
     */
    @Test
    fun test_03_MissionButton() {
        getElementById("our_mission_image_button")?.click()
        val container = getElementByText("Love is all")
        assertEquals(true, container is UiObject2)
    }

    /**
     * 4,5
     */
    @Test
    fun test_04_05_ExpandCollapseNews() {
        val news = getElementById("container_list_news_include_on_fragment_main")
        val button = getElementById("expand_material_button", news)
        button?.click();
        assertEquals(true, getElementById("all_news_text_view") !is UiObject2)
        button?.click();
        assertEquals(true, getElementById("all_news_text_view") is UiObject2)
    }

    /**
     * 6,7
     */
    @Test
    fun test_06_07_ExpandCollapseClaims() {
        val news = getElementById("container_list_claim_include_on_fragment_main")
        val button = getElementById("expand_material_button", news)
        button?.click();
        assertEquals(true, getElementById("all_claims_text_view") !is UiObject2)
        button?.click();
        assertEquals(true, getElementById("all_claims_text_view") is UiObject2)
    }

    /**
     * 8
     */
    @Test
    fun test_08_AllNewsButton() {
        getElementById("all_news_text_view")?.click()
        val container = getElementByText("News")
        assertEquals(true, container is UiObject2)
    }

    /**
     * 9
     */
    @Test
    fun test_09_AllClaimsButton() {
        getElementById("all_claims_text_view")?.click();
        val container = getElementByText("Claims")
        assertEquals(true, container is UiObject2)
    }

    /**
     * 10, 12
     */
    @Test
    fun test_10_11_ExpandCollapseArrowNews() {
        getElementById("view_news_item_image_view")?.click()
        val afterExpand = getElementById("news_item_description_text_view") is UiObject2
        getElementById("view_news_item_image_view")?.click();
        val afterCollapse = getElementById("news_item_description_text_view") !is UiObject2
        assertEquals(true, afterExpand)
        assertEquals(true, afterCollapse)
    }

    /**
     * 11, 13
     */
    @Test
    fun test_12_13_ExpandCollapseTitleNews() {
        getElementById("news_item_title_text_view")?.click()
        val afterExpand = getElementById("news_item_description_text_view") is UiObject2
        getElementById("news_item_title_text_view")?.click();
        val afterCollapse = getElementById("news_item_description_text_view") !is UiObject2
        assertEquals(true, afterExpand)
        assertEquals(true, afterCollapse)
    }

    /**
     * 14
     */
    @Test
    fun test_14_OpenClaimByArrowClick() {
        getElementById("claim_bottom_divider_image_view")?.click()
        assertEquals(true, getElementById("title_label_text_view") is UiObject2)
    }

    /**
     * 15
     */
    @Test
    fun test_15_OpenClaimByTopicClick() {
        getElementById("title_material_text_view")?.click()
        assertEquals(true, getElementById("title_label_text_view") is UiObject2)
    }

    /**
     * 16
     */
    @Test
    fun test_16_OpenClaimByDetailsClick() {
        getElementById("plan_date_material_text_view")?.click()
        assertEquals(true, getElementById("title_label_text_view") is UiObject2)
    }

    /**
     * 17
     */
    @Test
    fun test_17_OpenAddNewClaim() {
        getElementById("add_new_claim_material_button")?.click()
        assertEquals(true, getElementById("container_custom_app_bar_include_on_fragment_create_edit_claim") is UiObject2)
    }

    /**
     * 18, 19
     */
    @Test
    fun test_18_19_SortNewsByDate() {
        getElementById("all_news_text_view")?.click()
        getElementById("sort_news_material_button")?.click()
        val oldDate: String = getElementById("news_item_date_text_view")?.text!!
        getElementById("sort_news_material_button")?.click()
        val newDate: String = getElementById("news_item_date_text_view")?.text!!
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        val firstDate: Date = sdf.parse(oldDate)!!
        val secondDate: Date = sdf.parse(newDate)!!
        val result = firstDate.compareTo(secondDate)
        assertEquals(true, result <= 0)
    }

    /**
     * 20
     */
    @Test
    fun test_20_OpenNewsFilter() {
        getElementById("all_news_text_view")?.click()
        getElementById("filter_news_material_button")?.click()
        assertEquals(true, getElementById("filter_news_title_text_view") is UiObject2)
    }

    /**
     * 21
     */
    @Test
    fun test_21_NewsFilterCategory() {
        getElementById("all_news_text_view")?.click()
        getElementById("filter_news_material_button")?.click()
        getElementById("news_item_category_text_auto_complete_text_view")?.click()
        val categoryName = "День рождения"
        getElementByText(categoryName)?.click()
        val selectedCategoryName = getElementById("news_item_category_text_auto_complete_text_view")?.text
        assertEquals(categoryName, selectedCategoryName)
    }

    /**
     * 22
     */
    @Test
    fun test_22_ApplyNewsFilterCategory() {
        getElementById("all_news_text_view")?.click()
        getElementById("filter_news_material_button")?.click()
        getElementById("news_item_category_text_auto_complete_text_view")?.click()
        val categoryName = "День рождения"
        getElementByText(categoryName)?.click()
        getElementById("filter_button")?.click()
        assertEquals(categoryName, getElementById("news_item_title_text_view")?.text)
    }

    /**
     * 23
     */
    @Test
    fun test_23_SelectFromPreviousDate() {
        val formatter = DateTimeFormatter.ofPattern("dd")
        val fullDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val currentDate = LocalDateTime.now()
        val period = Period.of(0, 0, 1)
        val previousDay = currentDate.minus(period).format(formatter)
        getElementById("all_news_text_view")?.click()
        getElementById("filter_news_material_button")?.click()
        getElementById("news_item_publish_date_start_text_input_edit_text")?.click()
        getElementByText(previousDay)?.click()
        getElementByText("OK")?.click()
        val selectedDate = getElementById("news_item_publish_date_start_text_input_edit_text")?.text
        assertEquals(currentDate.minus(period).format(fullDateFormatter), selectedDate)
    }

    /**
     * 24
     */
    @Test
    fun test_24_SelectFromCurrentDate() {
        val formatter = DateTimeFormatter.ofPattern("dd")
        val fullDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val currentDate = LocalDateTime.now()
        val currentDay = currentDate.format(formatter)
        getElementById("all_news_text_view")?.click()
        getElementById("filter_news_material_button")?.click()
        getElementById("news_item_publish_date_start_text_input_edit_text")?.click()
        getElementByText(currentDay)?.click()
        getElementByText("OK")?.click()
        val selectedDate = getElementById("news_item_publish_date_start_text_input_edit_text")?.text
        assertEquals(currentDate.format(fullDateFormatter), selectedDate)
    }

    /**
     * 25
     */
    @Test
    fun test_25_SelectFromNextDate() {
        val formatter = DateTimeFormatter.ofPattern("dd")
        val fullDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val currentDate = LocalDateTime.now()
        val period = Period.of(0, 0, 1)
        val nextDay = currentDate.plus(period).format(formatter)
        getElementById("all_news_text_view")?.click()
        getElementById("filter_news_material_button")?.click()
        getElementById("news_item_publish_date_start_text_input_edit_text")?.click()
        getElementByText(nextDay)?.click()
        getElementByText("OK")?.click()
        val selectedDate = getElementById("news_item_publish_date_start_text_input_edit_text")?.text
        assertEquals(currentDate.plus(period).format(fullDateFormatter), selectedDate)
    }

    /**
     * 26
     */
    @Test
    fun test_26_SelectToPreviousDate() {
        val formatter = DateTimeFormatter.ofPattern("dd")
        val fullDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val currentDate = LocalDateTime.now()
        val period = Period.of(0, 0, 1)
        val previousDay = currentDate.minus(period).format(formatter)
        getElementById("all_news_text_view")?.click()
        getElementById("filter_news_material_button")?.click()
        getElementById("news_item_publish_date_end_text_input_edit_text")?.click()
        getElementByText(previousDay)?.click()
        getElementByText("OK")?.click()
        val selectedDate = getElementById("news_item_publish_date_end_text_input_edit_text")?.text
        assertEquals(currentDate.minus(period).format(fullDateFormatter), selectedDate)
    }

    /**
     * 27
     */
    @Test
    fun test_27_SelectToCurrentDate() {
        val formatter = DateTimeFormatter.ofPattern("dd")
        val fullDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val currentDate = LocalDateTime.now()
        val currentDay = currentDate.format(formatter)
        getElementById("all_news_text_view")?.click()
        getElementById("filter_news_material_button")?.click()
        getElementById("news_item_publish_date_end_text_input_edit_text")?.click()
        getElementByText(currentDay)?.click()
        getElementByText("OK")?.click()
        val selectedDate = getElementById("news_item_publish_date_end_text_input_edit_text")?.text
        assertEquals(currentDate.format(fullDateFormatter), selectedDate)
    }

    /**
     * 28
     */
    @Test
    fun test_28_SelectToNextDate() {
        val formatter = DateTimeFormatter.ofPattern("dd")
        val fullDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val currentDate = LocalDateTime.now()
        val period = Period.of(0, 0, 1)
        val nextDay = currentDate.plus(period).format(formatter)
        getElementById("all_news_text_view")?.click()
        getElementById("filter_news_material_button")?.click()
        getElementById("news_item_publish_date_end_text_input_edit_text")?.click()
        getElementByText(nextDay)?.click()
        getElementByText("OK")?.click()
        val selectedDate = getElementById("news_item_publish_date_end_text_input_edit_text")?.text
        assertEquals(currentDate.plus(period).format(fullDateFormatter), selectedDate)
    }

    /**
     * 29
     */
    @Test
    fun test_29_FilterFromDate() {
        getElementById("all_news_text_view")?.click()
        getElementById("filter_news_material_button")?.click()
        getElementById("news_item_publish_date_start_text_input_edit_text")?.click()
        getElementByText("OK")?.click()
        getElementById("filter_button")?.click()
        assertEquals(true, getElementByText("Wrong period") is UiObject2)
    }

    /**
     * 30
     */
    @Test
    fun test_30_FilterToDate() {
        getElementById("all_news_text_view")?.click()
        getElementById("filter_news_material_button")?.click()
        getElementById("news_item_publish_date_end_text_input_edit_text")?.click()
        getElementByText("OK")?.click()
        getElementById("filter_button")?.click()
        assertEquals(true, getElementByText("Wrong period") is UiObject2)
    }

    /**
     * 31
     */
    @Test
    fun test_31_FilterBothDates() {
        val formatter = DateTimeFormatter.ofPattern("dd")
        val currentDate = LocalDateTime.now()
        val period = Period.of(0, 0, 1)
        val prevDay = currentDate.minus(period).format(formatter)
        val nextDay = currentDate.plus(period).format(formatter)
        getElementById("all_news_text_view")?.click()
        getElementById("filter_news_material_button")?.click()
        getElementById("news_item_publish_date_start_text_input_edit_text")?.click()
        getElementByText(prevDay)?.click()
        getElementByText("OK")?.click()
        getElementById("news_item_publish_date_end_text_input_edit_text")?.click()
        getElementByText(nextDay)?.click()
        getElementByText("OK")?.click()
        getElementById("filter_button")?.click()
        assertEquals(true, getElementByText("News") is UiObject2)
    }

    /**
     * 32
     */
    @Test
    fun test_32_FilterClose() {
        getElementById("all_news_text_view")?.click()
        getElementById("filter_news_material_button")?.click()
        getElementById("cancel_button")?.click()
        assertEquals(true, getElementByText("News") is UiObject2)
    }

    /**
     * 33
     */
    @Test
    fun test_33_OpenNewsControlPanel() {
        getElementById("all_news_text_view")?.click()
        getElementById("edit_news_material_button")?.click()
        assertEquals(true, getElementByText("Control panel") is UiObject2)
    }

    /**
     * 34
     */
    @Test
    fun test_34_DeleteNewsItem() {
        val newsName = "День рождения"
        getElementById("all_news_text_view")?.click()
        getElementById("edit_news_material_button")?.click()
        val newsItem = getElementByText(newsName)?.parent?.parent
        getElementById("delete_news_item_image_view", newsItem)?.click()
        getElementByText("OK")?.click()
        assertEquals(true, getElementByText(newsName) !is UiObject2)
    }

    /**
     * 35
     */
    @Test
    fun test_35_EditNewsItem() {
        getElementById("all_news_text_view")?.click()
        getElementById("edit_news_material_button")?.click()
        getElementById("edit_news_item_image_view")?.click()
        assertEquals(true, getElementByText("Editing") is UiObject2)
    }

    /**
     * 36
     */
    @Test
    fun test_36_OpenAddNewsItemPage() {
        getElementById("all_news_text_view")?.click()
        getElementById("edit_news_material_button")?.click()
        getElementById("add_news_image_view")?.click()
        assertEquals(true, getElementByText("Creating") is UiObject2)
    }

    /**
     * 37
     */
    @Test
    fun test_37_AddNewsItem() {
        getElementById("all_news_text_view")?.click()
        getElementById("edit_news_material_button")?.click()
        getElementById("add_news_image_view")?.click()
        val newsName = "День рождения"
        getElementById("news_item_category_text_input_layout")?.click()
        getElementByText(newsName)?.click()
        getElementById("news_item_title_text_input_edit_text")?.text = newsName
        getElementById("news_item_publish_date_text_input_edit_text")?.click()
        getElementByText("OK")?.click()
        getElementById("news_item_publish_time_text_input_edit_text")?.click()
        getElementByText("OK")?.click()
        getElementById("news_item_description_text_input_edit_text")?.text = newsName
        getElementById("save_button")?.click()
        assertEquals(true, getElementByText(newsName) is UiObject2)
    }

    /**
     * 38
     */
    @Test
    fun test_38_CancelAddNewsItem() {
        getElementById("all_news_text_view")?.click()
        getElementById("edit_news_material_button")?.click()
        getElementById("add_news_image_view")?.click()
        getElementById("cancel_button")?.click()
        getElementByText("OK")?.click()
        assertEquals(true, getElementByText("Control panel") is UiObject2)

    }

    /**
     * 39
     */
    @Test
    fun test_39_OpenClaimsFilter() {
        getElementById("all_claims_text_view")?.click()
        getElementById("filters_material_button")?.click()
        assertEquals(true, getElementByText("Filtering") is UiObject2)
    }

    /**
     * 40, 41
     */
    @Test
    fun test_40_41_ToggleFilterOpenCheckbox() {
        getElementById("all_claims_text_view")?.click()
        getElementById("filters_material_button")?.click()
        val checkbox = getElementById("item_filter_open")
        checkbox?.click()
        assertEquals(false, checkbox?.isChecked)
        checkbox?.click()
        assertEquals(true, checkbox?.isChecked)
    }

    /**
     * 42, 43
     */
    @Test
    fun test_42_43_ToggleFilterInProgressCheckbox() {
        getElementById("all_claims_text_view")?.click()
        getElementById("filters_material_button")?.click()
        val checkbox = getElementById("item_filter_in_progress")
        checkbox?.click()
        assertEquals(false, checkbox?.isChecked)
        checkbox?.click()
        assertEquals(true, checkbox?.isChecked)
    }

    /**
     * 44, 45
     */
    @Test
    fun test_44_45_ToggleFilterExecutedCheckbox() {
        getElementById("all_claims_text_view")?.click()
        getElementById("filters_material_button")?.click()
        val checkbox = getElementById("item_filter_executed")
        checkbox?.click()
        assertEquals(true, checkbox?.isChecked)
        checkbox?.click()
        assertEquals(false, checkbox?.isChecked)
    }

    /**
     * 46, 47
     */
    @Test
    fun test_46_47_ToggleFilterCanceledCheckbox() {
        getElementById("all_claims_text_view")?.click()
        getElementById("filters_material_button")?.click()
        val checkbox = getElementById("item_filter_cancelled")
        checkbox?.click()
        assertEquals(true, checkbox?.isChecked)
        checkbox?.click()
        assertEquals(false, checkbox?.isChecked)
    }

    /**
     * 48
     */
    @Test
    fun test_48_FilterOpen() {
        getElementById("all_claims_text_view")?.click()
        getElementById("filters_material_button")?.click()
        getElementById("item_filter_in_progress")?.click()
        getElementById("claim_list_filter_ok_material_button")?.click()
        getElementById("claim_bottom_divider_image_view")?.click()
        assertEquals("Open", getElementById("status_label_text_view")?.text)
    }

    /**
     * 49
     */
    @Test
    fun test_49_FilterInProgress() {
        getElementById("all_claims_text_view")?.click()
        getElementById("filters_material_button")?.click()
        getElementById("item_filter_open")?.click()
        getElementById("claim_list_filter_ok_material_button")?.click()
        getElementById("claim_bottom_divider_image_view")?.click()
        assertEquals("In progress", getElementById("status_label_text_view")?.text)
    }

    /**
     * 50
     */
    @Test
    fun test_50_FilterExecuted() {
        getElementById("all_claims_text_view")?.click()
        getElementById("filters_material_button")?.click()
        getElementById("item_filter_in_progress")?.click()
        getElementById("item_filter_open")?.click()
        getElementById("item_filter_executed")?.click()
        getElementById("claim_list_filter_ok_material_button")?.click()
        getElementById("claim_bottom_divider_image_view")?.click()
        assertEquals("Executed", getElementById("status_label_text_view")?.text)
    }

    /**
     * 51
     */
    @Test
    fun test_51_FilterCanceled() {
        getElementById("all_claims_text_view")?.click()
        getElementById("filters_material_button")?.click()
        getElementById("item_filter_in_progress")?.click()
        getElementById("item_filter_open")?.click()
        getElementById("item_filter_cancelled")?.click()
        getElementById("claim_list_filter_ok_material_button")?.click()
        getElementById("claim_bottom_divider_image_view")?.click()
        assertEquals("Canceled", getElementById("status_label_text_view")?.text)
    }

    /**
     * 52
     */
    @Test
    fun test_52_CloseFilter() {
        getElementById("all_claims_text_view")?.click()
        getElementById("filters_material_button")?.click()
        getElementById("claim_filter_cancel_material_button")?.click()
        assertEquals(true, getElementByText("Filtering") !is UiObject2)
    }

    /**
     * 53
     */
    @Test
    fun test_53_OpenAddClaimPage() {
        getElementById("all_claims_text_view")?.click()
        getElementById("add_new_claim_material_button")?.click()
        assertEquals(true, getElementByText("Creating") is UiObject2)
    }

    /**
     * 54
     */
    @Test
    fun test_54_AddClaim() {
        getElementById("all_claims_text_view")?.click()
        getElementById("add_new_claim_material_button")?.click()
        val claimName =  "New claim 123"
        getElementById("title_edit_text")?.text = claimName
        getElementById("executor_drop_menu_auto_complete_text_view")?.click()
        getElementByText("Ivanov Ivan Ivanovich")?.click()
        getElementById("date_in_plan_text_input_edit_text")?.click()
        getElementByText("OK")?.click()
        getElementById("time_in_plan_text_input_edit_text")?.click()
        getElementByText("OK")?.click()
        getElementById("description_edit_text")?.text = "New claim 123 Description"
        getElementById("save_button")?.click()
        scrollToByText(claimName)
        assertEquals(true, getElementByText(claimName) is UiObject2)
    }

    /**
     * 55
     */
    @Test
    fun test_55_CancelAddClaim() {
        getElementById("all_claims_text_view")?.click()
        getElementById("add_new_claim_material_button")?.click()
        getElementById("cancel_button")?.click()
        getElementByText("OK")?.click()
        assertEquals(true, getElementByText("Creating") !is UiObject2)
    }

    /**
     * 56
     */
    @Test
    fun test_56_AddClaimFromMain() {
        getElementById("add_new_claim_material_button")?.click()
        val claimName =  "New claim 456"
        getElementById("title_edit_text")?.text = claimName
        getElementById("executor_drop_menu_auto_complete_text_view")?.click()
        getElementByText("Ivanov Ivan Ivanovich")?.click()
        getElementById("date_in_plan_text_input_edit_text")?.click()
        getElementByText("OK")?.click()
        getElementById("time_in_plan_text_input_edit_text")?.click()
        getElementByText("OK")?.click()
        getElementById("description_edit_text")?.text = "New claim 456 Description"
        getElementById("save_button")?.click()
        getElementById("all_claims_text_view")?.click()
        scrollToByText(claimName)
        assertEquals(true, getElementByText(claimName) is UiObject2)
    }


    /**
     * 57
     */
    @Test
    fun test_57_CancelAddClaimFromMain() {
        getElementById("add_new_claim_material_button")?.click()
        getElementById("cancel_button")?.click()
        getElementByText("OK")?.click()
        assertEquals(true, getElementByText("Creating") !is UiObject2)
    }

    /**
     * 58
     */
    @Test
    fun test_58_EditOpen() {
        getElementById("all_claims_text_view")?.click()
        getElementById("filters_material_button")?.click()
        getElementById("item_filter_in_progress")?.click()
        getElementById("claim_list_filter_ok_material_button")?.click()
        getElementById("claim_bottom_divider_image_view")?.click()
        scrollToById("edit_processing_image_button")
        getElementById("edit_processing_image_button")?.click()
        assertEquals(true, getElementByText("Editing") is UiObject2)
    }

    /**
     * 59
     */
    @Test
    fun test_59_EditInProgress() {
        getElementById("all_claims_text_view")?.click()
        getElementById("filters_material_button")?.click()
        getElementById("item_filter_open")?.click()
        getElementById("claim_list_filter_ok_material_button")?.click()
        getElementById("claim_bottom_divider_image_view")?.click()
        scrollToById("edit_processing_image_button")
        getElementById("edit_processing_image_button")?.click()
        assertEquals(true, getElementByText("Editing") !is UiObject2)
    }

    /**
     * 60
     */
    @Test
    fun test_60_EditExecuted() {
        getElementById("all_claims_text_view")?.click()
        getElementById("filters_material_button")?.click()
        getElementById("item_filter_in_progress")?.click()
        getElementById("item_filter_open")?.click()
        getElementById("item_filter_executed")?.click()
        getElementById("claim_list_filter_ok_material_button")?.click()
        getElementById("claim_bottom_divider_image_view")?.click()
        scrollToById("edit_processing_image_button")
        getElementById("edit_processing_image_button")?.click()
        assertEquals(true, getElementByText("Editing") !is UiObject2)
    }

    /**
     * 61
     */
    @Test
    fun test_61_EditCanceled() {
        getElementById("all_claims_text_view")?.click()
        getElementById("filters_material_button")?.click()
        getElementById("item_filter_in_progress")?.click()
        getElementById("item_filter_open")?.click()
        getElementById("item_filter_cancelled")?.click()
        getElementById("claim_list_filter_ok_material_button")?.click()
        getElementById("claim_bottom_divider_image_view")?.click()
        scrollToById("edit_processing_image_button")
        getElementById("edit_processing_image_button")?.click()
        assertEquals(true, getElementByText("Editing") !is UiObject2)
    }
}


