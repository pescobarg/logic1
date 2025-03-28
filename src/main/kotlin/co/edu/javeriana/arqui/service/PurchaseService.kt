package co.edu.javeriana.arqui.service

import co.edu.javeriana.arqui.rest.Purchase
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter
import jakarta.transaction.Transactional
import co.edu.javeriana.arqui.database.PurchaseEntity
import co.edu.javeriana.arqui.database.PurchaseRepository

@ApplicationScoped
class PurchaseService {

    @Inject
    lateinit var purchaseRepository: PurchaseRepository

    @Inject
    @Channel("purchase-queue")
    lateinit var purchaseEmitter: Emitter<Purchase>

    @Transactional
    fun processPurchase(purchase: Purchase) {
        // Guardar en la base de datos
        val entity = PurchaseEntity(0, purchase.userEmail, purchase.product, purchase.amount)
        purchaseRepository.persist(entity)

        // Enviar a la cola de RabbitMQ
        purchaseEmitter.send(purchase)
    }
}
