package cabanas.garcia.ismael.meetup.shared.domain

abstract class Entity {
    protected fun checkRule(rule: BusinessRule) {
        if (rule.isBroken()) {
            throw DomainException(rule.message())
        }
    }
}