package leandro.image.api.core.usecase

import org.slf4j.LoggerFactory

open class UseCaseBase {
    private val log = LoggerFactory.getLogger(this.javaClass)

    fun logStart()
    {
        log.info("Start UseCase ${this.javaClass.simpleName}")
    }

    fun logEnd()
    {
        log.info("End UseCase ${this.javaClass.simpleName}")
    }

    fun logError(ex: Exception)
    {
        log.error("Error UseCase: ${this.javaClass::getName}  message: ${ex.message}")
    }
}