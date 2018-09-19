package server.service.verticle

import io.vertx.core.Future

class UserVerticle : AbstractBusVerticle() {

    override fun start(startFuture: Future<Void>?) {

        startFuture?.complete()

    }

}