package pages

import androidx.test.uiautomator.*
import org.junit.Assert.assertEquals

class ClaimsPage(device: UiDevice) : Page(device) {

    private val claimsPageSelector = "claim_list_swipe_refresh"
    private val claimsFilterSelector = "filters_material_button"
    private val claimsItemArrowSelector = "claim_bottom_divider_image_view"
    private val addNewClaimButton = "add_new_claim_material_button"

    fun onPage() {
        step("Проверить переход на страницу списка заявок")
        assertEquals(true, getElementById(claimsPageSelector) is UiObject2)
    }

    fun openClaimsFilter() : ClaimsFilterPage {
        step("Открыть фильтр заявок")
        getElementById(claimsFilterSelector)?.click()
        return ClaimsFilterPage(device)
    }

    fun getClaimsItem(title: String) : UiObject2? {
        step("Получить заявку с названием '".plus(title).plus("'"))
        return getElementByText(title)
    }

    fun openClaimsItem() : ClaimsItemPage {
        step("Открыть завявку нажатием на стрелку")
        getElementById(claimsItemArrowSelector)?.click()
        return ClaimsItemPage(device)
    }

    fun clickAddNewClaim() : ClaimsEditPage {
        step("Нажать кнопку добавления новой заявки")
        getElementById(addNewClaimButton)?.click()
        return ClaimsEditPage(device)
    }
}