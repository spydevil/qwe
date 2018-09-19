package server.service.verticle

import io.vertx.core.Future
import io.vertx.kotlin.coroutines.CoroutineVerticle

class RabbitMQVerticle : CoroutineVerticle() {


    override fun start(startFuture: Future<Void>?) {

        startFuture?.complete()

    }

}