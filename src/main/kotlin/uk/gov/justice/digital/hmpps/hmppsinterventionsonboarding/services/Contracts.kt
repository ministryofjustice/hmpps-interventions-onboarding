package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models.CRSContract
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.repositories.CRSContractRepository

@Service
class Contracts(@Autowired val repository: CRSContractRepository) {
  fun allCRS(): Iterable<CRSContract> {
    return repository.findAll(Sort.by("reference"))
  }
}
