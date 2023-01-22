package pages

import androidx.test.uiautomator.*
import org.junit.Assert.assertEquals

class ClaimsPage(device: UiDevice) : Page(device) {

    private val claimsPageSelector = "claim_list_swipe_refresh"
    private val claimsFilterSelector = "filters_material_button"
    private val claimsItemArrowSelector = "claim_bottom_divider_image_view"
    private val addNewClaimButton = "add_new_claim_material_button"

    fun onPage() {
        assertEquals(true, getElementById(claimsPageSelector) is UiObject2)
    }

    fun openClaimsFilter() : ClaimsFilterPage {
        getElementById(claimsFilterSelector)?.click()
        return ClaimsFilterPage(device)
    }

    fun getClaimsItem(title: String) : UiObject2? {
        return getElementByText(title)
    }

    fun openClaimsItem() : ClaimsItemPage {
        getElementById(claimsItemArrowSelector)?.click()
        return ClaimsItemPage(device)
    }

    fun clickAddNewClaim() : ClaimsEditPage {
        getElementById(addNewClaimButton)?.click()
        return ClaimsEditPage(device)
    }
}