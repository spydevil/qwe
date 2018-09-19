package server.service

import io.vertx.codegen.annotations.GenIgnore
import io.vertx.codegen.annotations.ProxyGen
import io.vertx.codegen.annotations.VertxGen
import io.vertx.core.AsyncResult
import io.vertx.core.Handler
import server.service.request.Request1
import server.service.request.Request2
import server.service.request.Request3

@VertxGen
@ProxyGen
interface RequestService {

    @GenIgnore
    companion object {
        const val QWE = 1
    }

    fun request1(ctx: Handler<AsyncResult<Request1>>)
    fun request2(ctx: Handler<AsyncResult<Request2>>)
    fun request3(ctx: Handler<AsyncResult<Request3>>)

}