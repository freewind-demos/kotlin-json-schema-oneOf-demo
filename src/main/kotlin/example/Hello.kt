package example

import org.everit.json.schema.loader.SchemaLoader
import org.json.JSONObject
import org.json.JSONTokener
import java.io.File

val json = """
{
  "checked": false,
  "dimensions": {
    "width": 5,
    "height": 10
  },
  "id": 1,
  "name": "A green door",
  "price": 12.5,
  "tags": [
    "home",
    "green"
  ],
  "unknown-property": "should-not-allowed"
}
""".trimIndent()

fun main(args: Array<String>) {
    val jsonSchema = File("./schema.json").readText()
    val rawSchema = JSONObject(JSONTokener(jsonSchema))
    val schema = SchemaLoader.load(rawSchema)
    schema.validate(JSONObject(json))
}
