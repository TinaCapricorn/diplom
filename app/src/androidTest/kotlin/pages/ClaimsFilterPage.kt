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
        return getOpen()?.isChecked
    }

    fun isInProgressChecked() : Boolean? {
        return getInProgress()?.isChecked
    }

    fun isExecutedChecked() : Boolean? {
        return getExecuted()?.isChecked
    }

    fun isCancelledChecked() : Boolean? {
        return getCancelled()?.isChecked
    }

    fun clickOpen() : ClaimsFilterPage {
        getOpen()?.click()
        return this
    }

    fun clickInProgress() : ClaimsFilterPage {
        getInProgress()?.click()
        return this
    }

    fun clickExecuted() : ClaimsFilterPage {
        getExecuted()?.click()
        return this
    }

    fun clickCancelled() : ClaimsFilterPage {
        getCancelled()?.click()
        return this
    }

    fun applyFilter() : ClaimsPage {
        getElementById(filterApplyButton)?.click()
        return ClaimsPage(device)
    }

    fun cancelFilter() : ClaimsPage {
        getElementById(filterCancelButton)?.click()
        return ClaimsPage(device)
    }

}