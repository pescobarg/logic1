package co.edu.javeriana.arqui.database

import io.quarkus.hibernate.orm.panache.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class PurchaseRepository : PanacheRepository<PurchaseEntity> {
}
