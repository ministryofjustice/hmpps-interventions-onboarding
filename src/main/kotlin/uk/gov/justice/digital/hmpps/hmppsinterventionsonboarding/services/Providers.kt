package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models.Provider
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.repositories.ProviderRepository

@Service
class Providers(@Autowired val repository: ProviderRepository) {
  fun all(): Iterable<Provider> {
    return repository.findAll(Sort.by("name"))
  }
}
