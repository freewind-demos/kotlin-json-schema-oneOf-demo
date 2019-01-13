package example

import org.everit.json.schema.ValidationException
import org.everit.json.schema.loader.SchemaLoader
import org.json.JSONObject
import org.json.JSONTokener
import java.io.File

val jsons = listOf(
        """{ "title": "something", "from": "somewhere", "to": "somewhere" }""",
        """{ "title": "something", "address": "somewhere" }"""
)

fun main(args: Array<String>) {
    val jsonSchema = File("./schema.json").readText()
    val rawSchema = JSONObject(JSONTokener(jsonSchema))
    val schema = SchemaLoader.load(rawSchema)
    jsons.forEach { json ->
        try {
            schema.validate(JSONObject(json))
            println("$json: passed")
        } catch (e: ValidationException) {
            println("$json: failed: ${e.allMessages}")
        }
    }

}
