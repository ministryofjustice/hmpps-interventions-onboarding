package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models

import java.time.OffsetDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

enum class Complexity {
  LOW, MEDIUM, HIGH
}

@Entity
@Table(name = "complexity_levels")
data class ComplexityLevel(
  @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

  @Column @Enumerated(EnumType.STRING) val complexity: Complexity = Complexity.LOW,
  @Column val description: String = "",

  @Column val createdAt: OffsetDateTime = OffsetDateTime.now(),
  @Column val updatedAt: OffsetDateTime? = OffsetDateTime.now(),
)
