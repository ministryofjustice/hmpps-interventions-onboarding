package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.integration

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models.Provider
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.repositories.ProviderRepository

class PageControllerTest @Autowired constructor(
  private val providerRepository: ProviderRepository
) : IntegrationTestBase() {
  private lateinit var testProvider: Provider

  @BeforeEach
  fun setup() {
    // cannot setup as @Transactional as then the web request would not see the value
    testProvider = providerRepository.save(Provider(authGroupId = "SP001", name = "Demo Provider"))
  }

  @AfterEach
  fun teardown() {
    providerRepository.delete(testProvider)
  }

  @Test
  fun `provider page loads`() {
    webTestClient.get()
      .uri("/providers")
      .exchange()
      .expectStatus().isOk
      .expectBody()
      .xpath("//td[@data-test='name']").isEqualTo("Demo Provider")
  }
}
