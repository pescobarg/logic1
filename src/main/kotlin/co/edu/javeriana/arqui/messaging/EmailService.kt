package co.edu.javeriana.arqui.messaging

import co.edu.javeriana.arqui.rest.Purchase
import io.smallrye.reactive.messaging.annotations.Blocking
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.eclipse.microprofile.reactive.messaging.Incoming
import io.quarkus.mailer.Mail
import io.quarkus.mailer.reactive.ReactiveMailer

@ApplicationScoped
class EmailService {

    @Inject
    lateinit var reactiveMailer: ReactiveMailer

    @Incoming("purchase-queue")
    @Blocking
    fun sendEmail(purchase: Purchase) {
        val mail = Mail.withText(
            purchase.userEmail,
            "Confirmación de Compra",
            "Hola, tu compra de ${purchase.product} por ${purchase.amount} ha sido confirmada."
        )
        reactiveMailer.send(mail).subscribe().with { println("Correo enviado a ${purchase.userEmail}") }
    }
}
