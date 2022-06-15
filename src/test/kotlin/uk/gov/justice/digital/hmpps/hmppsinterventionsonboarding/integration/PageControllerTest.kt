package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.integration

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models.CRSContract
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models.Provider
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.repositories.CRSContractRepository
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.repositories.ProviderRepository

class PageControllerTest @Autowired constructor(
  private val crsContractRepository: CRSContractRepository,
  private val providerRepository: ProviderRepository
) : IntegrationTestBase() {
  private lateinit var testContract: CRSContract
  private lateinit var testProvider: Provider

  @BeforeEach
  fun setup() {
    // cannot setup as @Transactional as then the web request would not see the value
    testContract = crsContractRepository.save(CRSContract(reference = "XYZ001"))
    testProvider = providerRepository.save(Provider(authGroupId = "SP001", name = "Demo Provider"))
  }

  @AfterEach
  fun teardown() {
    crsContractRepository.delete(testContract)
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

  @Test
  fun `contracts page loads`() {
    webTestClient.get()
      .uri("/contracts")
      .exchange()
      .expectStatus().isOk
      .expectBody()
      .xpath("//td[@data-test='reference']").isEqualTo("XYZ001")
  }
}
