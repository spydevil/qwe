@file:Suppress("ObjectPropertyName")

package server

import com.fasterxml.jackson.annotation.JsonInclude
import io.vertx.core.DeploymentOptions
import io.vertx.core.Future
import io.vertx.core.Verticle
import io.vertx.core.Vertx
import io.vertx.core.json.Json.mapper
import kotlinx.coroutines.experimental.runBlocking
import org.slf4j.LoggerFactory
import server.service.verticle.*
import kotlin.reflect.KClass

class Server {

    private var log = LoggerFactory.getLogger(Server::class.java)

    init {
        mapper
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
    }

    companion object {
        lateinit var vertx: Vertx
    }

    fun start(args: Array<String>) {
        runBlocking {

            Vertx.vertx().apply {
                Server.vertx = this

                deploy(MainVerticle::class)
                        .compose { deploy(UserVerticle::class) }
                        .compose { deploy(RabbitMQVerticle::class) }
                        .compose { deploy(RestVerticle::class) }
            }
        }
    }

    private fun Vertx.deploy(verticle: KClass<out Verticle>, options: DeploymentOptions = DeploymentOptions()): Future<String> {
        val future = Future.future<String>()

        this.deployVerticle(verticle.java.name, options) { r ->
            if (r.succeeded()) {
                log.info("\n\n~~~~~~ ${verticle.java.simpleName} is started successfully\n")
                future.complete()
            } else {
                log.error("\n\n@@@@: ${verticle.java.simpleName} is not started!\n", r.cause())
                future.fail(r.cause())
            }
        }

        return future
    }

}