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
        assertEquals(true, getElementById(claimEditSelector) is UiObject2)
    }

    fun setTitle(title: String) : ClaimsEditPage {
        getElementById(titleSelector)?.text = title
        return this
    }

    fun setExecutor(executorName: String) : ClaimsEditPage {
        getElementById(executorSelector)?.click()
        getElementByText(executorName)?.click()
        return this
    }

    fun setDate() : ClaimsEditPage {
        getElementById(dateSelector)?.click()
        DatePickerPage(device).confirm()
        return this
    }

    fun setTime() : ClaimsEditPage {
        getElementById(timeSelector)?.click()
        TimePickerPage(device).confirm()
        return this
    }

    fun setDescription(description: String) : ClaimsEditPage {
        getElementById(descriptionSelector)?.text = description
        return this
    }

    fun saveClaim() {
        getElementById(saveButton)?.click()
    }

    fun cancel() : ClaimsEditPage {
        getElementById(cancelButton)?.click()
        return this
    }

    fun confirmCancel() {
        getElementByText(cancelConfirmText)?.click()
    }



}