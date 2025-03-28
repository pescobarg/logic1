package co.edu.javeriana.arqui.rest

import co.edu.javeriana.arqui.service.PurchaseService
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/purchase")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class PurchaseResource {

    @Inject
    lateinit var purchaseService: PurchaseService

    @POST
    fun createPurchase(purchase: Purchase): Response {
        purchaseService.processPurchase(purchase)
        return Response.status(Response.Status.ACCEPTED).entity(mapOf("message" to "Compra en proceso")).build()
    }
}

data class Purchase(val id: Int, val userEmail: String, val product: String, val amount: Double)
