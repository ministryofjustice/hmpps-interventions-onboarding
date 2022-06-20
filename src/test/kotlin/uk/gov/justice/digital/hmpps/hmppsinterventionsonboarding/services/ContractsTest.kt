package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.services

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models.CRSContract
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.repositories.CRSContractRepository
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.repositories.CRSContractTypeRepository
import java.util.UUID

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ContractsTest @Autowired constructor(
  private val repository: CRSContractRepository,
  private val typeRepository: CRSContractTypeRepository,
) {
  val fixedManchesterWomensServicesId: UUID = UUID.fromString("d2be0730-39b4-4988-a8d2-e17ea63bd72b")
  val contracts: Contracts = Contracts(repository)

  @Test
  fun `allCRS() returns all contracts in reference order`() {
    val c = CRSContract(reference = "C").let { repository.save(it) }
    val b = CRSContract(reference = "B").let { repository.save(it) }
    val a = CRSContract(reference = "A").let { repository.save(it) }

    val result = contracts.allCRS()
    Assertions.assertThat(result).containsExactly(a, b, c)
  }

  @Test
  fun `findByReference() returns contract type by its reference number`() {
    val contract = CRSContract(reference = "A").let { repository.save(it) }

    Assertions.assertThat(contracts.findByReference("A")).isEqualTo(contract)
    Assertions.assertThat(contracts.findByReference("B")).isNull()
  }

  @Test
  fun `contract associations can be loaded`() {
    val type = typeRepository.findById(fixedManchesterWomensServicesId).orElseThrow()
    val contract = CRSContract(reference = "A", type = type).let { repository.save(it) }

    Assertions.assertThat(contract.type).isEqualTo(type)
  }

  @Test
  fun `contract type associations can be queried`() {
    val type = typeRepository.findById(fixedManchesterWomensServicesId).orElseThrow()

    Assertions.assertThat(type.serviceCategories).isNotEmpty
    Assertions.assertThat(type.extraDesiredOutcomes).isNotEmpty
  }
}
