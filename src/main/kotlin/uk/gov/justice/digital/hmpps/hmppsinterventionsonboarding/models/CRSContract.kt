package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models

import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "crs_contracts")
data class CRSContract(
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  val id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

  @Column
  val reference: String = "",

  @Column
  val contactAtMojEmail: String? = null,

  @Column
  val startDate: LocalDate? = null,

  @Column
  val endDate: LocalDate? = null,

  @Column
  val createdAt: OffsetDateTime = OffsetDateTime.now(),

  @Column
  val updatedAt: OffsetDateTime? = OffsetDateTime.now(),
)
