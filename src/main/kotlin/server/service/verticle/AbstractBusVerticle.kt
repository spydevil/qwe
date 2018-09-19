package server.service.verticle

import arrow.syntax.function.pipe
import io.vertx.core.Future
import io.vertx.core.eventbus.MessageConsumer
import io.vertx.core.json.JsonObject
import io.vertx.kotlin.coroutines.CoroutineVerticle
import org.slf4j.LoggerFactory

abstract class AbstractBusVerticle : CoroutineVerticle() {

    protected val log by lazy { LoggerFactory.getLogger(this::class.java) }
    protected val consumers by lazy { mutableListOf<MessageConsumer<JsonObject>>() }

    override fun stop(stopFuture: Future<Void>?) {
        consumers.forEach {
            it.unregister()
            log.info("${it.address()} consumer unregistered...")
        }.pipe { consumers.clear() }

        stopFuture?.complete()
    }

}