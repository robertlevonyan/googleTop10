package app.googletop10.data.utils

import app.googletop10.entity.ActionResult

suspend fun <R> makeApiCall(call: suspend () -> R) = try {
    ActionResult.Success(call())
} catch (e: Exception) {
    ActionResult.Error(e)
}