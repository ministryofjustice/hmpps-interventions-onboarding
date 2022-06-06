package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "providers")
data class Provider(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

  @Column
  val authGroupId: String = "",

  @Column
  val name: String = "",
)
