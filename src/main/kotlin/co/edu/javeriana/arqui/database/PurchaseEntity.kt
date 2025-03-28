package co.edu.javeriana.arqui.database

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class PurchaseEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var userEmail: String = "",
    var product: String = "",
    var amount: Double = 0.0
)
