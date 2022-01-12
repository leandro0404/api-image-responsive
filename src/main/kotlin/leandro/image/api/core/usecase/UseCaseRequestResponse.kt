package  leandro.image.api.core.usecase

import org.slf4j.LoggerFactory



abstract class UseCaseRequestResponse<TRequest, TResponse> :  UseCaseBase() {

    private val log = LoggerFactory.getLogger(this.javaClass)

    open fun execute(request: TRequest): TResponse {

        try {
            logStart()
            val execute = onExecute(request)
            logEnd()
            return execute
        } catch (ex: Exception) {
            logError(ex)
            throw ex
        }
    }

    protected abstract fun onExecute(request: TRequest): TResponse

}

