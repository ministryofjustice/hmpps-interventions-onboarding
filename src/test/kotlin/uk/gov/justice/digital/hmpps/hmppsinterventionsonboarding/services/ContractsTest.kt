package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.services

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models.CRSContract
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.repositories.CRSContractRepository

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ContractsTest @Autowired constructor(private val repository: CRSContractRepository) {
  val contracts: Contracts = Contracts(repository)

  @Test
  fun `allCRS() returns all contracts in reference order`() {
    val c = CRSContract(reference = "C").let { repository.save(it) }
    val b = CRSContract(reference = "B").let { repository.save(it) }
    val a = CRSContract(reference = "A").let { repository.save(it) }

    val result = contracts.allCRS()
    Assertions.assertThat(result).containsExactly(a, b, c)
  }
}
