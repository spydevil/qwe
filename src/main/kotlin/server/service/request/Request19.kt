package server.service.request

import com.fasterxml.jackson.annotation.JsonProperty
import io.vertx.codegen.annotations.DataObject
import io.vertx.core.json.JsonObject
import server.service.AbstractRequest

@DataObject(generateConverter = true)
class Request19() : AbstractRequest() {

    @JsonProperty("myid")
    var id: Long = -1

    @JsonProperty("mymessage")
    lateinit var message: String

    constructor(json: JsonObject) : this() {

    }

    fun toJson() = JsonObject().also {  }

}