package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.services.Contracts
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.services.Providers
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.services.Scope

@Controller
class ContractFlowController @Autowired constructor(
  val providers: Providers,
  val contracts: Contracts,
  val scope: Scope,
) {
  @GetMapping("/contracts")
  fun index(@Autowired model: Model): String {
    model.addAttribute("contracts", contracts.allCRS())
    return "listContracts"
  }

  @GetMapping("/contract/{reference}")
  fun show(@Autowired model: Model, @PathVariable reference: String): String {
    val contract = contracts.findByReference(reference) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    model.addAttribute("contract", contract)
    return "showContract"
  }

  @GetMapping("/contract/{reference}/categories")
  fun selectCategories(@Autowired model: Model, @PathVariable reference: String): String {
    val contract = contracts.findByReference(reference) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    val serviceCategories = scope.allSelectableServiceCategories()

    model.addAllAttributes(
      mapOf(
        "showBack" to true,
        "returnTo" to "/contract/${contract.reference}",
        "contract" to contract,
        "serviceCategories" to serviceCategories,
        "selectedCategories" to contract.type?.serviceCategories.orEmpty(),
      )
    )
    return "contractFlow/serviceCategories"
  }
}
