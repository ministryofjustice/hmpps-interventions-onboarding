package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models.ServiceCategory
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.repositories.ServiceCategoryRepository
import java.util.UUID

@Service
class Scope(@Autowired val repository: ServiceCategoryRepository) {
  val familyCategoryForManchesterId: UUID = UUID.fromString("9232541b-6b1c-455d-8153-ab2784bf4593")

  fun allServiceCategories(): Iterable<ServiceCategory> {
    return repository.findAll(Sort.by("name"))
  }

  fun allSelectableServiceCategories(): Iterable<ServiceCategory> {
    return allServiceCategories().filterNot { it.id == familyCategoryForManchesterId }
  }
}
