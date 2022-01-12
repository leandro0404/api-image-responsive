package  leandro.image.api.core.usecase

import org.slf4j.LoggerFactory


abstract class UseCaseOnlyResponse<TResponse> {

    private val log = LoggerFactory.getLogger(this.javaClass)

    open fun execute(): TResponse {

        try {
            log.info("Start UseCase ${this.javaClass.simpleName}")
            val execute = onExecute()
            log.info("End UseCase ${this.javaClass.simpleName}")
            return execute
        } catch (ex: Exception) {
            log.info("Error UseCase: ${this.javaClass::getName}  message: ${ex.message}")
            throw ex
        }
    }

    protected abstract fun onExecute(): TResponse

}
