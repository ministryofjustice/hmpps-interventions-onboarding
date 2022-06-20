package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.services.Providers

@Controller
class PageController @Autowired constructor(
  val providers: Providers,
) {
  @GetMapping("/")
  fun index(): String {
    return "index"
  }

  @GetMapping("/providers")
  fun getProviders(@Autowired model: Model): String {
    model.addAttribute("providers", providers.all())
    return "listProviders"
  }
}
