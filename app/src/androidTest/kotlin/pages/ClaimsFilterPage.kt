package pages

import androidx.test.uiautomator.*
import org.junit.Assert.assertEquals

class ClaimsFilterPage(device: UiDevice) : Page(device) {

    private val claimsFilterTitleSelector = "claim_filter_dialog_title"
    private val checkboxOpenSelector = "item_filter_open"
    private val checkboxInProgressSelector = "item_filter_in_progress"
    private val checkboxExecutedSelector = "item_filter_executed"
    private val checkboxCancelledSelector = "item_filter_cancelled"
    private val filterApplyButton = "claim_list_filter_ok_material_button"
    private val filterCancelButton = "claim_filter_cancel_material_button"

    fun onPage() {
        step("Проверить открытие фильтра заявок")
        assertEquals(true, getElementById(claimsFilterTitleSelector) is UiObject2)
    }

    private fun getOpen() : UiObject2? {
        return getElementById(checkboxOpenSelector)
    }

    private fun getInProgress() : UiObject2? {
        return getElementById(checkboxInProgressSelector)
    }

    private fun getExecuted() : UiObject2? {
        return getElementById(checkboxExecutedSelector)
    }

    private fun getCancelled() : UiObject2? {
        return getElementById(checkboxCancelledSelector)
    }

    fun isOpenChecked() : Boolean? {
        val isChecked = getOpen()?.isChecked
        step("Чекбокс 'Открыто' ".plus(getTextByCheckboxValue(isChecked)))
        return isChecked
    }

    fun isInProgressChecked() : Boolean? {
        val isChecked = getInProgress()?.isChecked
        step("Чекбокс 'В работе' ".plus(getTextByCheckboxValue(isChecked)))
        return isChecked
    }

    fun isExecutedChecked() : Boolean? {
        val isChecked = getExecuted()?.isChecked
        step("Чекбокс 'Выполнена' ".plus(getTextByCheckboxValue(isChecked)))
        return isChecked
    }

    fun isCancelledChecked() : Boolean? {
        val isChecked = getCancelled()?.isChecked
        step("Чекбокс 'Отмененные' ".plus(getTextByCheckboxValue(isChecked)))
        return isChecked
    }

    fun clickOpen() : ClaimsFilterPage {
        step("Нажать чекбокс 'Открыто'")
        getOpen()?.click()
        return this
    }

    fun clickInProgress() : ClaimsFilterPage {
        step("Нажать чекбокс 'В работе'")
        getInProgress()?.click()
        return this
    }

    fun clickExecuted() : ClaimsFilterPage {
        step("Нажать чекбокс 'Выполнена'")
        getExecuted()?.click()
        return this
    }

    fun clickCancelled() : ClaimsFilterPage {
        step("Нажать чекбокс 'Выполнена'")
        getCancelled()?.click()
        return this
    }

    fun applyFilter() : ClaimsPage {
        step("Нажать кнопку фильтрации")
        getElementById(filterApplyButton)?.click()
        return ClaimsPage(device)
    }

    fun cancelFilter() : ClaimsPage {
        step("Нажать кнопку отмены фильтрации")
        getElementById(filterCancelButton)?.click()
        return ClaimsPage(device)
    }

    private fun getTextByCheckboxValue(isChecked: Boolean?): String {
        return (if (isChecked!!) "" else "не ").plus("выбран")
    }
}