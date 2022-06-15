package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.services

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models.Provider
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.repositories.ProviderRepository

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ProvidersTest @Autowired constructor(private val repository: ProviderRepository) {
  val providers: Providers = Providers(repository)

  @Test
  fun `all() returns all providers in name order`() {
    val c = Provider(authGroupId = "C", name = "C provider").let { repository.save(it) }
    val b = Provider(authGroupId = "B", name = "B provider").let { repository.save(it) }
    val a = Provider(authGroupId = "A", name = "A provider").let { repository.save(it) }

    val result = providers.all()
    Assertions.assertThat(result).containsExactly(a, b, c)
  }
}
