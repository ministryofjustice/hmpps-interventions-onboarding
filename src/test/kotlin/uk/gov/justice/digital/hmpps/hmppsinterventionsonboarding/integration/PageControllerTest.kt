package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.integration

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models.CRSContract
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models.Provider
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.repositories.CRSContractRepository
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.repositories.CRSContractTypeRepository
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.repositories.ProviderRepository
import java.util.UUID

class PageControllerTest @Autowired constructor(
  private val crsContractRepository: CRSContractRepository,
  private val crsContractTypeRepository: CRSContractTypeRepository,
  private val providerRepository: ProviderRepository
) : IntegrationTestBase() {
  private lateinit var testContract: CRSContract
  private lateinit var testProvider: Provider

  @BeforeEach
  fun setup() {
    // cannot setup as @Transactional as then the web request would not see the value
    val pwb = crsContractTypeRepository.findById(UUID.fromString("f9b59d2c-c60b-4eb0-8469-04c975d2e2ee")).orElseThrow()
    testContract = crsContractRepository.save(CRSContract(reference = "XYZ001", type = pwb))
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
      .xpath("//td[@data-test='reference']/a").isEqualTo("XYZ001")
  }

  @Test
  fun `individual contract page loads`() {
    webTestClient.get()
      .uri("/contract/XYZ001")
      .exchange()
      .expectStatus().isOk
      .expectBody()
      .xpath("//h1").isEqualTo("XYZ001 contract details")
  }

  @Test
  fun `individual contract page returns 404 Not Found with non-existing reference`() {
    webTestClient.get()
      .uri("/contract/DOES_NOT_EXIST")
      .exchange()
      .expectStatus().isNotFound
  }
}
