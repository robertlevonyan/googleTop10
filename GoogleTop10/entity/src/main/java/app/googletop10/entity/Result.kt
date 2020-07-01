package app.googletop10.entity


sealed class ActionResult<out R> {
    data class Success<out R>(val data: R) : ActionResult<R>()
    data class Error(val exception: Throwable) : ActionResult<Nothing>()
}