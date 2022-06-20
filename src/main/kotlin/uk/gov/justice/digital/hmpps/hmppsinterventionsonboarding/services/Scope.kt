package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models.ServiceCategory
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.repositories.ServiceCategoryRepository

@Service
class Scope(@Autowired val repository: ServiceCategoryRepository) {
  fun allServiceCategories(): Iterable<ServiceCategory> {
    return repository.findAll(Sort.by("name"))
  }
}
