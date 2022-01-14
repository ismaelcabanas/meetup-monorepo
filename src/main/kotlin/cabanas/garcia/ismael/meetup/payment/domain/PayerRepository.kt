package cabanas.garcia.ismael.meetup.payment.domain

interface PayerRepository {
    fun get(id: PayerId): Payer
    fun add(payer: Payer)
}