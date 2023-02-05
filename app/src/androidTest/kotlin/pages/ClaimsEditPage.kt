package pages

import androidx.test.uiautomator.*
import org.junit.Assert.assertEquals

class ClaimsEditPage(device: UiDevice) : Page(device) {

    private val claimEditSelector = "container_custom_app_bar_include_on_fragment_create_edit_claim";
    private val titleSelector = "title_edit_text"
    private val executorSelector = "executor_drop_menu_auto_complete_text_view"
    private val dateSelector = "date_in_plan_text_input_edit_text"
    private val timeSelector = "time_in_plan_text_input_edit_text"
    private val descriptionSelector = "description_edit_text"
    private val saveButton = "save_button"
    private val cancelButton = "cancel_button"
    private val cancelConfirmText = "OK"

    fun onPage() {
        step("Проверить переход на страницу редактирования звявки")
        assertEquals(true, getElementById(claimEditSelector) is UiObject2)
    }

    fun setTitle(title: String) : ClaimsEditPage {
        step("Ввести в поле названия заявки текст '".plus(title).plus("'"))
        getElementById(titleSelector)?.text = title
        return this
    }

    fun setExecutor(executorName: String) : ClaimsEditPage {
        step("Нажать на выбор исполнителя")
        getElementById(executorSelector)?.click()
        step("Выбрать исполнителя с именем '".plus(executorName).plus("'"))
        getElementByText(executorName)?.click()
        return this
    }

    fun setDate() : ClaimsEditPage {
        step("Нажать на инпут даты заявки")
        getElementById(dateSelector)?.click()
        DatePickerPage(device).confirm()
        return this
    }

    fun setTime() : ClaimsEditPage {
        step("Нажать на инпут времени заявки")
        getElementById(timeSelector)?.click()
        TimePickerPage(device).confirm()
        return this
    }

    fun setDescription(description: String) : ClaimsEditPage {
        step("Ввести в поле описания заявки текст '".plus(description).plus("'"))
        getElementById(descriptionSelector)?.text = description
        return this
    }

    fun saveClaim() {
        step("Нажать на кнопку сохранения заявки")
        getElementById(saveButton)?.click()
    }

    fun cancel() : ClaimsEditPage {
        step("Нажать на кнопку отмены сохранения заявки")
        getElementById(cancelButton)?.click()
        return this
    }

    fun confirmCancel() {
        step("Нажать на кнопку ОК, для отмены сохранения заявки")
        getElementByText(cancelConfirmText)?.click()
    }



}