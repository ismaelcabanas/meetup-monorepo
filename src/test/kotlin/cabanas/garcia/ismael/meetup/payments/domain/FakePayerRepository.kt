package cabanas.garcia.ismael.meetup.payments.domain

import cabanas.garcia.ismael.meetup.payment.domain.Payer
import cabanas.garcia.ismael.meetup.payment.domain.PayerId
import cabanas.garcia.ismael.meetup.payment.domain.PayerRepository

class FakePayerRepository : PayerRepository {
    private val payerDatabase = HashMap<PayerId, Payer>()

    override fun get(id: PayerId): Payer =
        payerDatabase[id]
            ?: throw IllegalArgumentException("Payer not found: Id: $id")

    override fun add(payer: Payer) {
        payerDatabase[payer.id] = payer
    }
}